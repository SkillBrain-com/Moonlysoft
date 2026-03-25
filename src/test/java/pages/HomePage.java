package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the application home page.
 *
 * The base URL is resolved from serenity.conf for the active environment,
 * so no hard-coded URLs appear in page objects.
 *
 * Usage in step definitions (injected via @Steps or constructor):
 *   homePage.open();
 *   homePage.getTitle();
 */
public class HomePage extends PageObject {

    @FindBy(tagName = "h1")
    private WebElementFacade heading;

    private WebDriver driver = getDriver();

    /** Navigate to base.url (resolved from serenity.conf). */
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
