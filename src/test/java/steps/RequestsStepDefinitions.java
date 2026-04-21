package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RequestsPage;

public class RequestsStepDefinitions {

    private RequestsPage requestsPage;

    @And("I navigate to requests page")
    public void iNavigateToRequestsPage() {
        requestsPage.navigateToRequestsPage();
    }


    @When("I click on Available tab")
    public void iClickOnAvailableTab() {
        requestsPage.clickAvailableTab();
    }

    @And("I click on Take case button")
    public void iClickOnTakeCaseButton() {
        requestsPage.clickTakeButton();
    }

    @Then("the case should be in Active tab")
    public void theCaseShouldBeInActiveTab() {
        requestsPage.verifyActiveTabContainsCase();
    }

    @And("the Active tab counter should be updated")
    public void theActiveTabCounterShouldBeUpdated() {
        requestsPage.verifyActiveTabCounterUpdated();
    }
}
