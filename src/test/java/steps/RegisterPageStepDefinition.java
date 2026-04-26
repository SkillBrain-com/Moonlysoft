package steps;
import io.cucumber.java.en.When;
import pages.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RegisterPageStepDefinition {
    private RegisterPage registerPage;

    @Given("I navigate to register page")
    public void navigateToRegisterPage() {
        registerPage.navigateToRegisterPage();
    }
    @When("I complete first step {string}, {string}, {string}, {string}")
    public void completeFirstStep(String firstName, String lastName, String email, String password) {
        registerPage.completeStep1(firstName, lastName, email, password);
    }

    @And("I complete second step {string}, {string}, {string}, {string}, {string}, {string}")
    public void completeSecondStep(String specialty, String cuim, String rank,
                                   String workplace, String county, String city) {
        registerPage.completeStep2(specialty, cuim, rank, workplace, county, city);
    }



}
