package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class RequestsPage extends PageObject {


//@FindBy (xpath = "//p[normalize-space()='Available cases'] //parent::div //div")

    @FindBy(xpath = "//button[normalize-space()='Disponibile']")
    private WebElement availableTab;

    @FindBy(xpath = "//button[normalize-space()='Take']")
    private WebElement takeButton;

    @FindBy(xpath = "//button[normalize-space()='Active']")
    private WebElement activeTab;

    @FindBy(xpath = "(//div[@class='MuiCardContent-root css-tr66fw'])[1]")
    private WebElement activeTabCounter;

    @FindBy(xpath = "//button[@aria-label='open drawer']")
    private WebElement burger;

    @FindBy(xpath = "//a[@href='/requests']")
    private WebElement requestsTab;

    private static final Logger LOG = LoggerFactory.getLogger(RequestsPage.class);

    public void navigateToRequestsPage() {
        LOG.info("Navigating to request page...");

        if (burger.isDisplayed()) {
            LOG.info("Burger menu found, clicking it first...");
            waitFor(burger).waitUntilClickable();
            burger.click();
            waitFor(requestsTab).waitUntilVisible();
            requestsTab.click();
        } else {
            LOG.info("No burger menu, clicking requests tab directly...");
            waitFor(requestsTab).waitUntilVisible();
            requestsTab.click();

        }

    }

    public void clickAvailableTab() {
        LOG.info("Clicking Available tab...");
        waitFor(availableTab).waitUntilVisible();
        availableTab.click();

    }


    public void clickTakeButton() {
        LOG.info("Clicking Take button on a case...");
        takeButton.click();
    }

    public void verifyActiveTabContainsCase() {
        LOG.info("Verifying case is in Active tab...");
        activeTab.click();

    }

    public void verifyActiveTabCounterUpdated() {
        LOG.info("Verifying Active tab counter updated...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(activeTabCounter));
        activeTabCounter.getText();
    }

}
