package pages;

import config.EnvironmentConfig;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RegisterPage extends PageObject {

    // ── Pas 1: Informații Generale ──────────────────────────────────
    @FindBy(xpath = "//input[@id='first_name']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='last_name']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[contains(text(),'Următorul')]")
    private WebElement nextButton;

    // ── Pas 2: Informații Profesionale ──────────────────────────────
    @FindBy(xpath = "//input[@id='specialty_name']")
    private WebElement specialtyName;

    @FindBy(xpath = "//input[@id='cuim']")
    private WebElement codInregistrare;

    @FindBy(xpath = "//div[@id='specialty_rank']")
    private WebElement specialtyRank;

    @FindBy(xpath = "//input[@id='working_place']")
    private WebElement workingPlace;

    @FindBy(xpath = "//input[@id='county']")
    private WebElement judet;

    @FindBy(xpath = "//input[@id='location']")
    private WebElement localitate;

    @FindBy(xpath = "//input[@id='phone']")
    private WebElement phoneNumber;

    // ── Butoane ─────────────────────────────────────────────────────
    @FindBy(xpath = "//button[contains(text(),'Creează Cont')]")
    private WebElement createButton;

    @FindBy(xpath = "//a[contains(text(),'Autentifică-te')]")
    private WebElement loginLink;

    @FindBy(xpath = "//a[contains(text(),'Înregistrează-te')]")
    private WebElement registerButton;

    // ── Agreement Dialog ────────────────────────────────────────────
    @FindBy(xpath = "(//input[@type='checkbox'])[10]")
    private WebElement checkAccord;

    @FindBy(xpath = "//button[contains(text(),'Accept și Creează Cont')]")
    private WebElement acceptButton;

    // ── Pagina Succes ───────────────────────────────────────────────
    @FindBy(xpath = "//*[contains(text(),'Verificați emailul!')]")
    private WebElement successMessage;

    // ── Erori Pas 1 ─────────────────────────────────────────────────
    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'Prenumele este obligatoriu')]")
    private WebElement firstNameError;

    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'Numele este obligatoriu')]")
    private WebElement lastNameError;

    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'Email-ul este obligatoriu')]")
    private WebElement emailError;

    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'Parola este obligatorie')]")
    private WebElement passwordError;

    // ── Erori Pas 2 ─────────────────────────────────────────────────
    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'Specialitatea Medicală este obligatorie')]")
    private WebElement specialtyError;

    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'CUIM este obligatoriu')]")
    private WebElement cuimError;

    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'Gradul de Specialitate este obligatoriu')]")
    private WebElement specialtyRankError;

    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'Locul de Muncă este obligatoriu')]")
    private WebElement workingPlaceError;

    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'Județul este obligatoriu')]")
    private WebElement judetError;

    @FindBy(xpath = "//p[contains(@class,'Mui-error') and contains(text(),'Localitatea este obligatorie')]")
    private WebElement localitateError;

    @FindBy(xpath = "//span[contains(@class,'MuiStepLabel-label') and contains(@class,'Mui-active')]")
    private WebElement activeStepLabel;

    private static final Logger LOG = LoggerFactory.getLogger(RegisterPage.class);

    // ================================================================
    // NAVIGARE
    // ================================================================

    public void navigateToRegisterPage() {
        LOG.info("Navigating to register page...");
        String url = EnvironmentConfig.getBaseUrl();
        openAt(url);
        clickOn(registerButton);
    }

    // ================================================================
    // PAS 1 - INFORMATII GENERALE
    // ================================================================

    public void fillInFirstName(String firstName) {
        LOG.info("Filling in first name: {}", firstName);
        this.firstName.sendKeys(firstName);
    }

    public void fillInLastName(String lastName) {
        LOG.info("Filling in last name: {}", lastName);
        this.lastName.sendKeys(lastName);
    }

    public void fillInEmail(String email) {
        LOG.info("Filling in email: {}", email);
        this.email.sendKeys(email);
    }

    public void fillInEmail() {
        Random random = new Random();
        int randomNumber = random.nextInt(100, 1000);
        String randomEmail = "teststring" + randomNumber + "@gmail.com";
        LOG.info("Generated random email: {}", randomEmail);
        this.email.sendKeys(randomEmail);
    }

    public void fillInPassword(String password) {
        LOG.info("Filling in password...");
        this.password.sendKeys(password);
    }

    public void clickOnNextButton() {
        LOG.info("Clicking Next button...");
        this.nextButton.click();
    }

    public void completeStep1(String firstName, String lastName, String email, String password) {
        LOG.info("Completing Step 1 with random email (provided email ignored)...");
        fillInFirstName(firstName);
        fillInLastName(lastName);
        fillInEmail();
        fillInPassword(password);
        clickOnNextButton();
    }

    // ================================================================
    // PAS 2 - INFORMATII PROFESIONALE
    // ================================================================

    public void fillInSpecialtyName(String specialtyName) {
        LOG.info("Filling in specialty: {}", specialtyName);
        this.specialtyName.sendKeys(specialtyName);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(),'" + specialtyName + "')]")
        ));
        option.click();
    }

    public void fillInCodInregistrare(String codInregistrare) {
        LOG.info("Filling in CUIM: {}", codInregistrare);
        this.codInregistrare.sendKeys(codInregistrare);
    }

    public void fillInCodInregistrareRandom() {
        Random random = new Random();
        int randomCuim = random.nextInt(0, 1_000_000);
        String formattedCuim = String.format("%06d", randomCuim);
        LOG.info("Generated random CUIM: {}", formattedCuim);
        this.codInregistrare.sendKeys(formattedCuim);
    }

    public void selectSpecialtyRank(String rank) {
        LOG.info("Selecting specialty rank: {}", rank);
        this.specialtyRank.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[@data-value='" + rank + "']")
        ));
        option.click();
    }

    public void fillInWorkingPlace(String workingPlace) {
        LOG.info("Filling in working place: {}", workingPlace);
        this.workingPlace.sendKeys(workingPlace);
    }

    public void fillInJudet(String judet) {
        LOG.info("Filling in judet: {}", judet);
        this.judet.sendKeys(judet);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(),'" + judet + "')]")
        ));
        option.click();
    }

    public void fillInLocalitate(String localitate) {
        LOG.info("Filling in localitate: {}", localitate);
        this.localitate.sendKeys(localitate);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(),'" + localitate + "')]")
        ));
        option.click();
    }

    public void fillInPhoneNumber(String phone) {
        LOG.info("Filling in phone number: {}", phone);
        this.phoneNumber.sendKeys(phone);
    }

    public void clickOnCreateButton() {
        LOG.info("Clicking on Create Account button...");
        this.createButton.click();
    }

    public void completeStep2(String specialty, String cuim, String rank,
                              String workplace, String county, String city) {
        LOG.info("Completing Step 2 with random CUIM (provided CUIM ignored)...");
        fillInSpecialtyName(specialty);
        fillInCodInregistrareRandom();
        selectSpecialtyRank(rank);
        fillInWorkingPlace(workplace);
        fillInJudet(county);
        fillInLocalitate(city);
        clickOnCreateButton();
    }

    public void completeStep2WithPhone(String specialty, String cuim, String rank,
                                       String workplace, String county, String city,
                                       String phone) {
        LOG.info("Completing Step 2 with phone...");
        fillInSpecialtyName(specialty);
        fillInCodInregistrare(cuim);
        selectSpecialtyRank(rank);
        fillInWorkingPlace(workplace);
        fillInJudet(county);
        fillInLocalitate(city);
        fillInPhoneNumber(phone);
        clickOnCreateButton();
    }

    // ================================================================
    // AGREEMENT DIALOG
    // ================================================================

    public boolean isAgreementDialogDisplayed() {
        LOG.info("Checking if Agreement Dialog is displayed...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.MuiDialog-root")
        ));
        return dialog.isDisplayed();
    }

    public void scrollToAgreementCheckbox() {
        LOG.info("Scrolling to agreement checkbox...");
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", checkAccord);
    }

    public void clickOnAgreementCheckbox() {
        LOG.info("Clicking on agreement checkbox...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(checkAccord));
        checkAccord.click();
    }

    public void clickOnAcceptAndCreateButton() {
        LOG.info("Clicking on Accept and Create Account button...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(acceptButton));
        acceptButton.click();
    }

    public void completeAgreementDialog() {
        LOG.info("Completing agreement dialog flow...");
        scrollToAgreementCheckbox();
        clickOnAgreementCheckbox();
        clickOnAcceptAndCreateButton();
    }

    // ================================================================
    // PAGINA DE SUCCES
    // ================================================================

    public boolean isSuccessMessageDisplayed() {
        LOG.info("Checking if success message is displayed...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        WebElement message = wait.until(ExpectedConditions.visibilityOf(successMessage));
        return message.isDisplayed();
    }

    // ================================================================
    // NAVIGARE LINKS
    // ================================================================

    public void clickOnLoginLink() {
        LOG.info("Clicking on 'Autentifica-te' link...");
        this.loginLink.click();
    }

    // ================================================================
    // METODE ASSERT PAS 1
    // ================================================================

    public void checkFirstNameError() {
        LOG.info("Checking first name error message...");
        assertEquals("Prenumele este obligatoriu", firstNameError.getText());
    }

    public void checkLastNameError() {
        LOG.info("Checking last name error message...");
        assertEquals("Numele este obligatoriu", lastNameError.getText());
    }

    public void checkEmailError() {
        LOG.info("Checking email error message...");
        assertEquals("Email-ul este obligatoriu", emailError.getText());
    }

    public void checkPasswordError() {
        LOG.info("Checking password error message...");
        assertEquals("Parola este obligatorie", passwordError.getText());
    }

    public void checkAllStep1Errors() {
        LOG.info("Checking all Step 1 error messages...");
        checkFirstNameError();
        checkLastNameError();
        checkEmailError();
        checkPasswordError();
    }

    public void checkStep2IsActive() {
        LOG.info("Checking Step 2 is active...");
        assertEquals("Informații Profesionale", activeStepLabel.getText());
    }

    // ================================================================
    // METODE ASSERT PAS 2
    // ================================================================

    public void checkSpecialtyError() {
        LOG.info("Checking specialty error message...");
        assertEquals("Specialitatea Medicală este obligatorie", specialtyError.getText());
    }

    public void checkCuimError() {
        LOG.info("Checking CUIM error message...");
        assertEquals("CUIM este obligatoriu", cuimError.getText());
    }

    public void checkSpecialtyRankError() {
        LOG.info("Checking specialty rank error message...");
        assertEquals("Gradul de Specialitate este obligatoriu", specialtyRankError.getText());
    }

    public void checkWorkingPlaceError() {
        LOG.info("Checking working place error message...");
        assertEquals("Locul de Muncă este obligatoriu", workingPlaceError.getText());
    }

    public void checkJudetError() {
        LOG.info("Checking judet error message...");
        assertEquals("Județul este obligatoriu", judetError.getText());
    }

    public void checkLocalitateError() {
        LOG.info("Checking localitate error message...");
        assertEquals("Localitatea este obligatorie", localitateError.getText());
    }

    public void checkAllStep2Errors() {
        LOG.info("Checking all Step 2 error messages...");
        checkSpecialtyError();
        checkCuimError();
        checkSpecialtyRankError();
        checkWorkingPlaceError();
        checkJudetError();
        checkLocalitateError();
    }
}