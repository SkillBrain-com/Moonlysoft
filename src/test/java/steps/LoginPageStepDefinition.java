package steps;

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
}
