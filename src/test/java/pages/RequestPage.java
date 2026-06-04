package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestPage extends PageObject {


    @FindBy(xpath = "//a[@href='/requests']")
    private WebElement requestsButton;

    @FindBy(xpath = "//h1[normalize-space()='Solicitările Mele Alocate']")
    private WebElement titleRequestsPage;

    @FindBy(xpath="//button[@aria-label='open drawer']")
    private  WebElement burger;

    @FindBy(xpath="//a[@href='/patient']")
    private  WebElement patientButton;

    @FindBy(xpath = "(//button[normalize-space()='Disponibile'])[1]")
    private WebElement availableTab;


    private static final Logger LOG = LoggerFactory.getLogger(RequestPage.class);
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));


    public void myRequests() {

        wait.until(ExpectedConditions.visibilityOf(burger));
        if (burger.isDisplayed()) {
            burger.click();
            LOG.info("The request button is clicked");
//            waitFor(requestsButton).waitUntilVisible();
//            requestsButton.click();
        } else {
            LOG.info("Burger menu is not present.");
//            waitFor(requestsButton).waitUntilVisible();
//            requestsButton.click();
        }
        wait.until(ExpectedConditions.elementToBeClickable(requestsButton));
        requestsButton.click();
        LOG.info("Requests button clicked successfully");

        wait.until(ExpectedConditions.visibilityOf(titleRequestsPage));
        assertEquals("Solicitările Mele Alocate", titleRequestsPage.getText());

    }



    public void goToAvailableTab(){
        waitFor(availableTab).waitUntilVisible();
       availableTab.click();
    }

}
