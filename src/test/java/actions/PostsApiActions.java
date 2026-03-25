package actions;

import config.EnvironmentConfig;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

/**
 * Serenity @Step actions for the Posts API.
 *
 * Using SerenityRest (wraps REST-assured) so every call is automatically
 * recorded in the Serenity report with request/response details.
 */
public class PostsApiActions {

    @Step("Verify the Posts API is reachable at {0}")
    public void verifyApiIsAvailable(String apiUrl) {
        SerenityRest.given()
                .baseUri(apiUrl)
                .when()
                .get("/posts?_limit=1")
                .then()
                .statusCode(200);
    }

    @Step("GET /posts")
    public Response getAllPosts(String apiUrl) {
        return SerenityRest.given()
                .baseUri(apiUrl)
                .when()
                .get("/posts");
    }

    @Step("GET /posts/{postId}")
    public Response getPostById(String apiUrl, int postId) {
        return SerenityRest.given()
                .baseUri(apiUrl)
                .pathParam("postId", postId)
                .when()
                .get("/posts/{postId}");
    }

    @Step("POST /posts with body {body}")
    public Response createPost(String apiUrl, Map<String, Object> body) {
        return SerenityRest.given()
                .baseUri(apiUrl)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/posts");
    }

    // ── Assertion helpers ────────────────────────────────────────────────────

    @Step("Assert response status is {expectedStatus}")
    public void assertStatusCode(int expectedStatus) {
        restAssuredThat(response -> response.statusCode(expectedStatus));
    }

    @Step("Assert response contains a list of posts")
    public void assertResponseIsNonEmptyList() {
        restAssuredThat(response -> response.body("size()", greaterThan(0)));
    }

    @Step("Assert post title is '{expectedTitle}'")
    public void assertPostTitle(String expectedTitle) {
        restAssuredThat(response -> response.body("title", equalTo(expectedTitle)));
    }

    @Step("Assert response contains a created post id")
    public void assertCreatedPostIdPresent() {
        restAssuredThat(response -> response.body("id", notNullValue()));
    }
}
