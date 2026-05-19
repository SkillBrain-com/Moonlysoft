package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AdminPage;



    public class AdminPageStepDefinition {

        AdminPage adminPage;
        @When("I navigate directly to admin page")
        public void iNavigateDirectlyToAdminPage() {
            adminPage.navigateToAdminPage();
        }

        @Then("access should be denied")
        public void accessShouldBeDenied() {
            adminPage.verifyAccessIsDenied();
        }

        @And("the user should be redirected away from admin page")
        public void theUserShouldBeRedirectedAwayFromAdminPage() {
            adminPage.verifyAccessIsDenied();
        }
    }

