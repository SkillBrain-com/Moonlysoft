package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.PatientPage;

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

    @Then("User enters all required information to complete the form")
    public void userEntersAllRequiredInformationToCompleteTheForm() {
        patientPage.completeFormAddPatient("Popescu","1234564567812", "test@gmail.com", "65","");

    }
}
