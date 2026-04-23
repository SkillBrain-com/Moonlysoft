package pages;

import config.EnvironmentConfig;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class LoginPage extends PageObject {

    private final String EXPECTED_HEADER = "Cazurile mele";

    @FindBy(xpath = "//h1[text()='Cazurile mele']")
    private WebElement header;

    @FindBy(xpath = "//input[@id='email-login']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='password-login']")
    private WebElement password;

    @FindBy(xpath = "//button[text()='Autentifică-te']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[normalize-space()='Parola este obligatorie']")
    private WebElement passwordError;

    private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);


    public void navigateToLoginPage() {
        String url = EnvironmentConfig.getBaseUrl();
        openAt(url);
    }

    public void loginAsUser(String user) {
        LOG.info("Logging in as {} user...", user);
        email.sendKeys(getEmail(user));
        password.sendKeys(getPassword(user));
        clickOnLoginButton();
    }

    public void checkUserIsLoggedIn() {
        LOG.info("Checking user is logged in...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        String text = wait.until(ExpectedConditions.visibilityOf(header)).getText();
        assertEquals(EXPECTED_HEADER, text);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

    private String getEmail(String user) {
        String email = switch (user.toLowerCase()) {
            case "regular" -> getCredentialsProperties().getProperty("user.regular");
            case "expert" -> getCredentialsProperties().getProperty("user.expert");
            case "admin" -> getCredentialsProperties().getProperty("user.admin");
            default -> {
                LOG.error("Selected user {} is not available", user);
                throw new RuntimeException("Selected user is not valid.");
            }
        };
        return email;
    }

    private String getPassword(String user) {
        return switch (user.toLowerCase()) {
            case "regular" -> getCredentialsProperties().getProperty("regular.password");
            case "expert" -> getCredentialsProperties().getProperty("expert.password");
            case "admin" -> getCredentialsProperties().getProperty("admin.password");
            default -> {
                LOG.error("Selected user {} is not available", user);
                throw new RuntimeException("Selected user is not valid.");
            }
        };
    }

    private Properties getCredentialsProperties() {
        Properties properties = new Properties();
        InputStream is = EnvironmentConfig.class
                .getClassLoader()
                .getResourceAsStream("credentials/credentials.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            LOG.error("Failed to load credentials.properties file");
            throw new RuntimeException(e);
        }
        return properties;
    }

    public void fillInUserEmail(String email) {
        LOG.info("Trying to fill in email...");
        this.email.sendKeys(email);
    }

    public void fillInUserPassword(String password) {
        this.password.sendKeys(password);
    }

    public void checkPasswordError() {
        assertEquals("Parola este obligatorie", passwordError.getText());
    }
}

