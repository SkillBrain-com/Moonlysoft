package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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

}

