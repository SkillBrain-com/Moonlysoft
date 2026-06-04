package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.Duration;


import static org.junit.Assert.assertEquals;


/**
 * Page Object for the application home page.
 * <p>
 * The base URL is resolved from serenity.conf for the active environment,
 * so no hard-coded URLs appear in page objects.
 * <p>
 * Usage in step definitions (injected via @Steps or constructor):
 * homePage.open();
 * homePage.getTitle();
 */
public class HomePage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);

    @FindBy(xpath = "(//button[@aria-label='open profile'])[2]")
    private WebElement profileIcon;

    @FindBy(xpath = "//button[@aria-label='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath = "//button[@aria-label='open localization']")
    private WebElement localizationButton;

    @FindBy(xpath = "//p[normalize-space()='English']")
    private WebElement englishButton;

    public void clickLogout() {
        profileIcon.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }

    public void switchLanguage(String language) {
        LOG.info(String.format("Switching to language %s", language));
        localizationButton.click();
        if (language.equalsIgnoreCase("English")) {
            englishButton.click();
        }

    }

    @FindBy(tagName = "h1")
    private WebElementFacade heading;

    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body2 css-gxq69q']")
    private WebElement filtersButton;

    @FindBy(xpath = "//input[@id='_r_17_']")
    private WebElement statusButton;

    @FindBy(xpath = "tbody tr:nth-child(1) td:nth-child(5) div:nth-child(1) span:nth-child(1)")
    private  WebElement chekedStatus;

    private WebDriver driver = getDriver();


    /**
     * Navigate to base.url (resolved from serenity.conf).
     */
    public void openHomePage(String url) {
        openAt(url);
    }

    public String getHeadingText() {
        return heading.isVisible() ? heading.getText() : "";
    }

    public String getPageTitle() {
        return getDriver().getTitle();
    }

    public void openFilters(){
        filtersButton.click();
    }

    public void getStatus (String status) {
        openFilters();
       switch (status.toLowerCase()) {
            case "deschis":
                driver.findElement(By.xpath("//span[text()='Deschis'][1]")).click();
                break;
            case "alocat":
                driver.findElement(By.xpath("(//span[text()='Alocat'])")).click();
                break;
            case "finalizat":
                driver.findElement(By.xpath("//span[text()='Finalizat']")).click();
                break;
            case "anulat":
                driver.findElement(By.xpath("//span[text()='Anulat']")).click();
                break;
            default: {
                LOG.error("Selected status {} is not available", status);
                throw new RuntimeException("Selected status is not valid.");
            }
        }
    }

    public void checkStatus(){
        try {
            assertEquals("Deschis", chekedStatus);
        }
       catch (Exception e){
           LOG.error("The status choice is not displayed");
       }

    }

}
