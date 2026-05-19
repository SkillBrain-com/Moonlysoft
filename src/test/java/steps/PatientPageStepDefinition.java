package steps;

import config.EnvironmentConfig;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import pages.PatientPage;

import static org.junit.Assert.assertTrue;

public class PatientPageStepDefinition {

    PatientPage patientPage;

    private static final String PATIENT_NAME = "Test Patient Happy Flow";

    @And("I navigate to patient page")
    public void iNavigateToPatientPage() {
        patientPage.navigateToPatientPage();
    }

    @When("I click Add Patient button")
    public void iClickAddPatient() {
        patientPage.clickAddPatient();
        Serenity.takeScreenshot();
    }

    @And("I fill in full name {string}")
    public void iFillInFullName(String fullName) {
        patientPage.fillInFullName(fullName);
    }

    @And("I fill in CNP with invalid format")
    public void iFillInCNPWithInvalidFormat() {
        patientPage.fillInInvalidCnp();
    }

    @And("I click confirm Add Patient button")
    public void iClickConfirmAddPatientButton() {
        patientPage.clickConfirmAddPatientButton();
    }

    @Then("an error message should appear on the CNP field")
    public void anErrorMessageShouldAppearOnTheCNPField() {
        patientPage.verifyCnpErrorMessageIsDisplayed();
    }

    @And("I fill in CNP with valid format")
    public void iFillInCNPWithValidFormat() {
        patientPage.fillCNP("1234567891234");
    }

    @Then("the new patient should appear in the patients table")
    public void theNewPatientShouldAppearInThePatientsTable() {
        String patientName = "Test Patient Happy Flow";
        boolean isPresent = patientPage.isPatientInTable(patientName);
        assertTrue("Patient '" + patientName + "' is not in the table.", isPresent);
    }

    @When("there is at least one patient in the table")
    public void thereIsAtLeastOnePatientInTheTable() {
        patientPage.open(EnvironmentConfig.getBaseUrl() + "/patient");
        assertTrue(patientPage.isAtLeastOnePatientInTable());
    }

    @And("the user clicks the Delete button on that patient")
    public void theUserClicksTheDeleteButtonOnThatPatient() {
        patientPage.clickDeleteForPatient(PATIENT_NAME);
    }

    @And("confirms the deletion in the confirmation dialog")
    public void confirmsTheDeletionInTheConfirmationDialog() {
        patientPage.confirmDeletion();
    }

    @Then("a toast notification should appear")
    public void aToastNotificationShouldAppear() {
        assertTrue(patientPage.isToastNotificationVisible());
    }

//    @And("the user clicks the edit button for patient {string}")
//    public void theUserClicksTheEditButtonForPatient(String name) {
//        patientPage.clickEditForPatient(PATIENT_NAME);
//    }

//    @Then("the user clears the {string} field")
//    public void theUserClearsTheField(String arg0) {
//        patientPage.clearFullNameField();
//    }
//
//
//    @And("the user clicks the Update Patient button")
//    public void theUserClicksTheUpdatePatientButton() {
//        patientPage.clickUpdatePatient();
//    }
//

//    @Then("the error message is displayed")
//    public void theErrorMessageIsDisplayed() {
//        assertTrue(patientPage.isErrorMessageDisplayed());
//    }
//
//    @And("the dialog remains open")
//    public void theDialogRemainsOpen() {
//        assertTrue(patientPage.isDialogOpen());
//    }

    @And("I leave the Full Name field empty")
    public void iLeaveTheFullNameFieldEmpty() {
    patientPage.leaveFullNameEmpty();
    }


    @Then("an error message {string} should appear under the Full Name field")
    public void anErrorMessageShouldAppearUnderTheFullNameField(String arg0) {
       assertTrue(patientPage.isNameErrorMessageDisplayed());
    }

}

