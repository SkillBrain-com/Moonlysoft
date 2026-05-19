package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


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
}
