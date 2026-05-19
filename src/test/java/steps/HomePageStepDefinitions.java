package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class HomePageStepDefinitions {

    private HomePage homePage;

    @Given("the REGULAR user is on the dashboard")
    public void theREGULARUserIsOnTheDashboard() {
    }

    @And("the user clicks on {string}")
    public void theUserClicksOn(String arg0) {
    }

    @When("the user fills all required fields in the new case form")
    public void theUserFillsAllRequiredFieldsInTheNewCaseForm() {
    }

    @And("selects experts")
    public void selectsExperts() {
    }

    @And("uploads valid files")
    public void uploadsValidFiles() {
    }

    @Then("the case should be created successfully")
    public void theCaseShouldBeCreatedSuccessfully() {
    }

    @Given("the REGULAR user is creating a case")
    public void theREGULARUserIsCreatingACase() {
    }

    @When("required fields are missing")
    public void requiredFieldsAreMissing() {
    }

    @Then("validation errors should be displayed")
    public void validationErrorsShouldBeDisplayed() {
    }

    @And("the case should not be created")
    public void theCaseShouldNotBeCreated() {
    }

    @And("user completes log out process")
    public void userCompletesLogOutProcess() {
        homePage.clickLogout();
    }


    @And("I change language to {string}")
    public void iChangeLanguageTo(String language) {
        homePage.switchLanguage(language);
    }

    @And("I check user is logged out")
    public void iCheckUserIsLoggedOut() {


    }
}


