package steps;

import actions.PostsApiActions;
import config.EnvironmentConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostsApiStepDefinitions {

    @Steps
    private PostsApiActions postsApi;

    // resolved once per scenario via hooks or lazy-loaded here
    private String apiUrl;

    private String resolvedApiUrl() {
        if (apiUrl == null) {
            apiUrl = EnvironmentConfig.getApiUrl();
        }
        return apiUrl;
    }

    @Given("the Posts API is available")
    public void thePostsApiIsAvailable() {
        postsApi.verifyApiIsAvailable(resolvedApiUrl());
    }

    @When("I request all posts")
    public void iRequestAllPosts() {
        postsApi.getAllPosts(resolvedApiUrl());
    }

    @When("I request post with id {int}")
    public void iRequestPostWithId(int postId) {
        postsApi.getPostById(resolvedApiUrl(), postId);
    }

    @When("I create a post with the following details:")
    public void iCreateAPostWithTheFollowingDetails(DataTable dataTable) {
        // Each row is: | fieldName | fieldValue |
        // DataTable header row is used as column names, so we get a list of { "title" -> value, ... } maps.
        // However the feature uses a two-column table without a header, so we read it as a raw list of lists.
        List<List<String>> rows = dataTable.asLists(String.class);
        Map<String, Object> body = new HashMap<>();
        for (List<String> row : rows) {
            if (row.size() == 2) {
                body.put(row.get(0), row.get(1));
            }
        }
        postsApi.createPost(resolvedApiUrl(), body);
    }

    @Then("the response status is {int}")
    public void theResponseStatusIs(int statusCode) {
        postsApi.assertStatusCode(statusCode);
    }

    @And("the response contains a list of posts")
    public void theResponseContainsAListOfPosts() {
        postsApi.assertResponseIsNonEmptyList();
    }

    @And("the post has title {string}")
    public void thePostHasTitle(String expectedTitle) {
        postsApi.assertPostTitle(expectedTitle);
    }

    @And("the response contains the created post id")
    public void theResponseContainsTheCreatedPostId() {
        postsApi.assertCreatedPostIdPresent();
    }
}
