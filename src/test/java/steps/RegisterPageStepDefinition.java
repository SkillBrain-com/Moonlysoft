package steps;
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
    @And("I complete first step")
    public void completeFirstStep(String firstName, String lastName, String email, String password) {
        registerPage.completeStep1(firstName, lastName, email, password);

    }
    @And("I complete second step")
    public void completeSecondStep(String specialty, String cuim, String rank,
                                   String workplace, String county, String city){
        registerPage.completeStep2(specialty, cuim, rank, workplace, county, city);
    }
    @And("I check to see if the Terms and Conditions are displayed")
    public void checkTermsAndConditions() {
        registerPage.isAgreementDialogDisplayed();
    }
    @Then ("I scroll down and create account")
       public void clickOnLoginLink() {
        registerPage.clickOnLoginLink();
    }


}
