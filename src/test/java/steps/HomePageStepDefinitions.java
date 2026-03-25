package steps;

import config.EnvironmentConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageStepDefinitions {

    private HomePage homePage;

    @Given("I open the application home page")
    public void iOpenTheApplicationHomePage() {
        String baseUrl = EnvironmentConfig.getBaseUrl();
        homePage.openHomePage(baseUrl);
    }

    @Then("the page title is not empty")
    public void thePageTitleIsNotEmpty() {
        String title = homePage.getDriver().getTitle();
        assertThat(title)
                .as("Expected page title to be non-empty for env: %s", EnvironmentConfig.getActiveEnvironment())
                .isNotBlank();
    }
}
