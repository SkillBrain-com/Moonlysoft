package steps;
import io.cucumber.java.en.When;
import pages.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertTrue;

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
    @When("user fills in first name with {string}")
    public void userFillsInFirstName(String firstName) {
        registerPage.fillInFirstName(firstName);
    }

    @When("user fills in last name with {string}")
    public void userFillsInLastName(String lastName) {
        registerPage.fillInLastName(lastName);
    }

    @When("user fills in email with {string}")
    public void userFillsInEmail(String email) {
        registerPage.fillInEmail(email);
    }

    @When("user fills in password with {string}")
    public void userFillsInPassword(String password) {
        registerPage.fillInPassword(password);
    }

    @When("user clicks on Next button")
    public void userClicksOnNextButton() {
        registerPage.clickOnNextButton();
    }
    @When("user fills in specialty with {string}")
    public void userFillsInSpecialty(String specialty) {
        registerPage.fillInSpecialtyName(specialty);
    }

    @When("user fills in CUIM with {string}")
    public void userFillsInCuim(String cuim) {
        registerPage.fillInCodInregistrare(cuim);
    }

    @When("user selects specialty rank {string}")
    public void userSelectsSpecialtyRank(String rank) {
        registerPage.selectSpecialtyRank(rank);
    }

    @When("user fills in working place with {string}")
    public void userFillsInWorkingPlace(String workplace) {
        registerPage.fillInWorkingPlace(workplace);
    }

    @When("user fills in judet with {string}")
    public void userFillsInJudet(String judet) {
        registerPage.fillInJudet(judet);
    }

    @When("user fills in localitate with {string}")
    public void userFillsInLocalitate(String localitate) {
        registerPage.fillInLocalitate(localitate);
    }

    @When("user fills in phone number with {string}")
    public void userFillsInPhoneNumber(String phone) {
        registerPage.fillInPhoneNumber(phone);
    }

    @When("user clicks on Create Account button")
    public void userClicksOnCreateAccountButton() {
        registerPage.clickOnCreateButton();
    }
    @Then("first name error message is displayed")
    public void firstNameErrorIsDisplayed() {
        registerPage.checkFirstNameError();
    }

    @Then("last name error message is displayed")
    public void lastNameErrorIsDisplayed() {
        registerPage.checkLastNameError();
    }

    @Then("email error message is displayed")
    public void emailErrorIsDisplayed() {
        registerPage.checkEmailError();
    }

    @Then("password error message is displayed")
    public void passwordErrorIsDisplayed() {
        registerPage.checkPasswordError();
    }

    @Then("all step 1 error messages are displayed")
    public void allStep1ErrorsAreDisplayed() {
        registerPage.checkAllStep1Errors();
    }
    @Then("user is on step 2")
    public void userIsOnStep2() {
        registerPage.checkStep2IsActive();
    }

    @Then("specialty error message is displayed")
    public void specialtyErrorIsDisplayed() {
        registerPage.checkSpecialtyError();
    }

    @Then("CUIM error message is displayed")
    public void cuimErrorIsDisplayed() {
        registerPage.checkCuimError();
    }

    @Then("specialty rank error message is displayed")
    public void specialtyRankErrorIsDisplayed() {
        registerPage.checkSpecialtyRankError();
    }

    @Then("working place error message is displayed")
    public void workingPlaceErrorIsDisplayed() {
        registerPage.checkWorkingPlaceError();
    }

    @Then("judet error message is displayed")
    public void judetErrorIsDisplayed() {
        registerPage.checkJudetError();
    }

    @Then("localitate error message is displayed")
    public void localitateErrorIsDisplayed() {
        registerPage.checkLocalitateError();
    }

    @Then("all step 2 error messages are displayed")
    public void allStep2ErrorsAreDisplayed() {
        registerPage.checkAllStep2Errors();}
    @Then("agreement dialog is displayed")
    public void agreementDialogIsDisplayed() {
        assertTrue("Agreement dialog should be visible",
                registerPage.isAgreementDialogDisplayed());}








}
