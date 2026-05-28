package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class RequestsPage extends PageObject {


//@FindBy (xpath = "//p[normalize-space()='Available cases'] //parent::div //div")

    @FindBy(xpath = "//button[normalize-space()='Available']")
    private WebElement availableTab;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Take Request'])[1]")
    private WebElement takeButton;

    @FindBy(xpath = "//button[normalize-space()='Active']")
    private WebElement activeTab;

    @FindBy(xpath = "(//div[@class='MuiCardContent-root css-tr66fw'])[1]")
    private WebElement activeTabCounter;

    @FindBy(xpath = "//button[@aria-label='open drawer']")
    private WebElement burger;

    @FindBy(xpath = "//a[@href='/requests']")
    private WebElement requestsTab;

    @FindBy(xpath = "(//button[@type='button'][normalize-space()='Decline'])[1]")
    private WebElement declineButton;

    @FindBy(xpath = "//p[text()='Available cases']/preceding-sibling::div")
    private WebElement availableTabCounter;

    @FindBy(xpath = "(//div[contains(@class,'MuiStack-root')]//button[@tabindex='0'])[1]")
    private WebElement listViewButton;

    @FindBy(xpath = "(//div[contains(@class,'MuiStack-root')]//button[@tabindex='0'])[2]")
    private WebElement compactViewButton;

    // Comfortable - al treilea
    @FindBy(xpath = "(//div[contains(@class,'MuiStack-root')]//button[@tabindex='0'])[3]")
    private WebElement comfortableViewButton;

    @FindBy(xpath = "(//button[normalize-space()='View Request'])[1]")
    private WebElement firstCaseCard;


    @FindBy(xpath = "(//button[normalize-space()='View Request'])[1]")
    private WebElement firstCompactCard;

    @FindBy(xpath = "(//button[normalize-space()='View Request'])[1]")
    private WebElement firstComfortableCard;

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

    public int getActiveTabCounterValue() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        String text = wait.until(ExpectedConditions.visibilityOf(activeTabCounter)).getText();
        return Integer.parseInt(text.split("\n")[0].trim());
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

    public void clickDeclineButton() {
        LOG.info("Clicking Decline button on a case...");
        waitFor(declineButton).waitUntilVisible();
        declineButton.click();
    }

    public int getAvailableTabCounterValue() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        String text = wait.until(ExpectedConditions
                .visibilityOf(availableTabCounter)).getText();
        return Integer.parseInt(text.split("\n")[0].trim());
    }

    public boolean isListViewDefault() {
        waitFor(firstCaseCard).waitUntilVisible();
        return firstCaseCard.isDisplayed();
    }

    public void clickCompactViewButton() {
        LOG.info("Clicking Compact view...");
        waitFor(compactViewButton).waitUntilVisible();
        compactViewButton.click();
    }

    public void clickComfortableViewButton() {
        LOG.info("Clicking Comfortable view...");
        waitFor(comfortableViewButton).waitUntilVisible();
        comfortableViewButton.click();
    }

    public boolean isCompactViewActive() {
        waitFor(firstCompactCard).waitUntilVisible();
        return firstCompactCard.isDisplayed();
    }

    public boolean isComfortableViewActive() {
        waitFor(firstComfortableCard).waitUntilVisible();
        return firstComfortableCard.isDisplayed();
    }
}
