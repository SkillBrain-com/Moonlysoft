package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.RequestsPage;

public class RequestsStepDefinitions {

    private RequestsPage requestsPage;
    private int counterBefore;
    private int casesTaken = 0;
    private int availableCounterBefore;
    private int casesDeclined = 0;

    @And("I navigate to requests page")
    public void iNavigateToRequestsPage() {
        requestsPage.navigateToRequestsPage();
    }

    @When("I click on Available tab")
    public void iClickOnAvailableTab() {
        counterBefore = requestsPage.getActiveTabCounterValue();
        requestsPage.clickAvailableTab();
        availableCounterBefore = requestsPage.getAvailableTabCounterValue();
    }

    @And("I click on Take case button")
    public void iClickOnTakeCaseButton() {
        requestsPage.clickTakeButton();
        casesTaken++;
    }

    @Then("the case should be in Active tab")
    public void theCaseShouldBeInActiveTab() {
        requestsPage.verifyActiveTabContainsCase();
    }

    @And("the Active tab counter should be updated")
    public void theActiveTabCounterShouldBeUpdated() {
        int counterAfter = requestsPage.getActiveTabCounterValue();
        Assert.assertEquals("Counter was not incremented correctly!", counterBefore + casesTaken, counterAfter);
    }

    @And("I click the Decline button on a case")
    public void iClickTheDeclineButtonOnACase() {
        requestsPage.clickDeclineButton();
        casesDeclined++;
    }

    @Then("the case should be removed from the Available tab")
    public void theCaseShouldBeRemovedFromTheAvailableTab() {
        int availableCounterAfter = requestsPage.getAvailableTabCounterValue();
        Assert.assertTrue(
                "Case was not removed from Available tab!",
                availableCounterAfter < availableCounterBefore
        );
    }

    @And("the tab counter should update correctly")
    public void theTabCounterShouldUpdateCorrectly() {
        int availableCounterAfter = requestsPage.getAvailableTabCounterValue();
        Assert.assertEquals(
                "Available tab counter was not decremented correctly!",
                availableCounterBefore - casesDeclined,
                availableCounterAfter
        );
    }

    @Then("the list view mode should be active by default")
    public void theListViewModeShouldBeActiveByDefault() {
        Assert.assertTrue(
                "List view is not active by default!",
                requestsPage.isListViewDefault()
        );
    }

    @When("I click on Compact view button")
    public void iClickOnCompactViewButton() {
        requestsPage.clickCompactViewButton();
    }

    @Then("the view should switch to Compact mode")
    public void theViewShouldSwitchToCompactMode() {
        Assert.assertTrue(
                "View did not switch to Compact!",
                requestsPage.isCompactViewActive()
        );
    }

    @When("I click on Comfortable view button")
    public void iClickOnComfortableViewButton() {
        requestsPage.clickComfortableViewButton();
    }

    @Then("the view should switch to Comfortable mode")
    public void theViewShouldSwitchToComfortableMode() {
        Assert.assertTrue(
                "View did not switch to Comfortable!",
                requestsPage.isComfortableViewActive()
        );
    }
}
