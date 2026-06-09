package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import pages.PatientPage;
import pages.RequestsPage;

public class PatientPageStepDefinitions {

    //    Dependency injection
    private PatientPage patientPage;


    @And("I click on patient tab")
    public void iClickOnPatientTab() {
        patientPage.goToPatientPage();

    }


    @And("I click on add patient button")
    public void iClickOnAddPatientButton() {
        patientPage.addPatient();
    }

    @Then("User enters all required information to complete the form with {string},{string},{string},{string},{string}")
    public void userEntersAllRequiredInformationToCompleteTheForm(String name, String cnp, String email,String weight, String conditions) {
        patientPage.completeFormAddPatient(name,cnp,email,weight,conditions);

    }

    @And("The request has been successfully created")
    public void theRequestHasBeenSuccessfullyCreated() {
       patientPage.patientCreated();
    }

    @And("I click on submit without filling in te required CNP field")
    public void iClickOnSubmitWithoutFillingInTeRequiredCNPField() {
        patientPage.formWithoutCNP("Popa Maria");
    }

    @Then("A warning message should be appears")
    public void aWarningMessageShouldBeAppears() {
        patientPage.warningMessageCNP();
    }

    @And("I fill date with {dyn}")
    public void iFillDateWith(String date) {
    patientPage.selectBirthDate(date);
    }

    @Then("I click on new request")
    public void iClickOnNewRequest() {
        patientPage.addingRequest();
    }

    @And("I fill in the general info with {string},{string},{string}")
    public void iFillInTheGeneralInfo(String title, String description, String patient) {
        patientPage.generalInfoForm(title, description, patient);
    }

    @And("I go to the next page expert assignment")
    public void iGoToTheNextPageExpertAssignment() {
       patientPage.assignmentExpertTAB();
    }

    @And("I choose the expert to assignment to request")
    public void iChooseTheExpertToAssignmentToRequest() {
        patientPage.chooseExpert();
    }

    @Then("I click on create request button")
    public void iClickOnCreateRequestButton() {
        patientPage.createRequest();
    }

    @And("I check if the last request was added")
    public void iCheckTheLastID() {
        patientPage.chackingCaseID();
    }



}
