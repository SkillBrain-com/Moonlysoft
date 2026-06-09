package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import pages.RequestPage;

import static org.junit.Assert.assertEquals;

public class RequestsPageStepDefinitions {

;
    private RequestPage requestPage;


    @And("I check user is on requests page")
    public void iCheckUserIsOnRequestsPage() {
       requestPage.myRequests();

    }

    @When("I clicks on Available tab")
    public void iClicksOnAvailableTab() {
      requestPage.goToAvailableTab();
    }
}
