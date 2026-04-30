package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginPageStepDefinition {

    //    Dependency injection
    private LoginPage loginPage;

    @Given("I login as {string} user")
    public void iLoginAsUser(String user) {
        loginPage.loginAsUser(user);
    }

    @Given("I navigate to login page")
    public void iNavigateToLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @Then("I check user is logged in")
    public void iCheckUserIsLoggedIn() {
        loginPage.checkUserIsLoggedIn();
    }

    @And("I click on login button")
    public void iClickOnLoginButton() {
        loginPage.clickOnLoginButton();
    }


    @And("I fill in email {string}")
    public void iFillInEmail(String email) {
        loginPage.fillInUserEmail(email);
    }

    @And("I fill in password {string}")
    public void iFillInPassword(String password) {
        loginPage.fillInUserPassword(password);
    }

    @Then("I check error message is displayed")
    public void iCheckErrorMessageIsDisplayed() {
        loginPage.checkPasswordError();
    }

    @Given("the user is logged in as REGULAR")
    public void theUserIsLoggedInAsREGULAR() {

    }

    @When("the dashboard page is loaded")
    public void theDashboardPageIsLoaded() {
    }

    @Then("only Dashboard, Patient and Profile menu items should be visible")
    public void onlyDashboardPatientAndProfileMenuItemsShouldBeVisible() {
    }

    @And("Requests, Admin and Analytics menu items should not be visible")
    public void requestsAdminAndAnalyticsMenuItemsShouldNotBeVisible() {
    }

    @When("the user navigates to the admin page")
    public void theUserNavigatesToTheAdminPage() {
    }

    @Then("access should be denied")
    public void accessShouldBeDenied() {
    }

    @And("the user should not see the admin panel content")
    public void theUserShouldNotSeeTheAdminPanelContent() {
    }

    @And("the user role is changed to EXPERT in the system")
    public void theUserRoleIsChangedToEXPERTInTheSystem() {
    }

    @When("the user refreshes the application")
    public void theUserRefreshesTheApplication() {
    }

    @Then("the user should see EXPERT-specific menu items")
    public void theUserShouldSeeEXPERTSpecificMenuItems() {
    }
}

