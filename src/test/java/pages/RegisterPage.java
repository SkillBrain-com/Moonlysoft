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

public class RegisterPage extends PageObject {
    @FindBy(xpath= "//input[@id='first_name']")
        private WebElement firstName;
    @FindBy(xpath = "//input[@id='last_name']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;
    @FindBy(xpath = "//button[contains(text(),'Următorul')]")
    private WebElement nextButton;
    @FindBy(xpath = "//input[@id='specialty_name']")
    private WebElement specialtyName;
    @FindBy (xpath = "//input[@id='cuim']")
    private WebElement codInregistrare;
    @FindBy (xpath = "//div[@id='specialty_rank']")
    private WebElement specialtyRank;
    @FindBy (xpath = "//input[@id='working_place']")
    private WebElement workingPlace;
    @FindBy (xpath = "//input[@id='county']")
    private WebElement judet;
    @FindBy (xpath = "//input[@id='location']")
    private WebElement localitate;
    @FindBy (xpath = "//button[contains(text(),'Creează Cont')]")
    private WebElement createButton;
    @FindBy (xpath = "(//input[@type='checkbox'])[10]")
    private WebElement checkAccord;
    @FindBy (xpath = "//button[contains(text(),'Accept și Creează Cont')]")
    private WebElement acceptButton;
    @FindBy (xpath = "//a[contains(text(),'Înregistrează-te')]")
    private WebElement registerLink;

    private static final Logger LOG = LoggerFactory.getLogger(RegisterPage.class);

    public void navigateToRegisterPage() {
        String url = EnvironmentConfig.getBaseUrl();
        openAt(url);
        clickOn(registerLink);
    }
    public void fillInFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }
    public void fillInLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }
    public void fillInEmail(String email) {
        this.email.sendKeys(email);
    }
    public void fillInPassword(String password) {
        this.password.sendKeys(password);
    }
    public void clickOnNextButton() {
        this.nextButton.click();
    }
    public void fillInSpecialtyName(String specialtyName) {
        this.specialtyName.sendKeys(specialtyName);

    }
    public void fillInCodInregistrare(String codInregistrare) {
        this.codInregistrare.sendKeys(codInregistrare);
    }
    public void fillInWorkingPlace(String workingPlace) {
        this.workingPlace.sendKeys(workingPlace);
    }



}
