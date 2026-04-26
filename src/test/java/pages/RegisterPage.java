package pages;

import config.EnvironmentConfig;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

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

    // Autocomplete/Combobox - trebuie sendKeys + select din lista
    @FindBy(xpath = "//input[@id='specialty_name']")
    private WebElement specialtyName;

    // Input text normal
    @FindBy(xpath = "//input[@id='cuim']")
    private WebElement codInregistrare;

    // Select dropdown MUI - click pe div, apoi selecteaza din lista
    @FindBy(xpath = "//div[@id='specialty_rank']")
    private WebElement specialtyRank;

    // Input text normal
    @FindBy(xpath = "//input[@id='working_place']")
    private WebElement workingPlace;

    // Autocomplete/Combobox
    @FindBy(xpath = "//input[@id='county']")
    private WebElement judet;

    // Autocomplete/Combobox
    @FindBy(xpath = "//input[@id='location']")
    private WebElement localitate;

    // Input tel (maxlength=10)
    @FindBy(xpath = "//input[@id='phone']")
    private WebElement phoneNumber;

    // ── Butoane ─────────────────────────────────────────────────────
    @FindBy(xpath = "//button[contains(text(),'Creează Cont')]")
    private WebElement createButton;

    @FindBy(xpath = "//a[contains(text(),'Autentifică-te')]")
    private WebElement loginLink;

    // ── Agreement Dialog ────────────────────────────────────────────
    @FindBy(xpath = "(//input[@type='checkbox'])[10]")
    private WebElement checkAccord;

    @FindBy(xpath = "//button[contains(text(),'Accept și Creează Cont')]")
    private WebElement acceptButton;

    private static final Logger LOG = LoggerFactory.getLogger(RegisterPage.class);

    // ================================================================
    // NAVIGARE
    // ================================================================

    public void navigateToRegisterPage() {
        LOG.info("Navigating to register page...");
        String url = EnvironmentConfig.getBaseUrl() + "/register";
        openAt(url);
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

    public void fillInPassword(String password) {
        LOG.info("Filling in password...");
        this.password.sendKeys(password);
    }

    public void clickOnNextButton() {
        LOG.info("Clicking Next button...");
        this.nextButton.click();
    }

    // Metoda completa pentru Pas 1 - toate campurile + Next
    public void completeStep1(String firstName, String lastName, String email, String password) {
        LOG.info("Completing Step 1...");
        fillInFirstName(firstName);
        fillInLastName(lastName);
        fillInEmail(email);
        fillInPassword(password);
        clickOnNextButton();
    }

    // ================================================================
    // PAS 2 - INFORMATII PROFESIONALE
    // ================================================================

    // Specialitate Medicala - AUTOCOMPLETE/COMBOBOX
    // Scrie text, asteapta lista, selecteaza optiunea
    public void fillInSpecialtyName(String specialtyName) {
        LOG.info("Filling in specialty: {}", specialtyName);
        this.specialtyName.sendKeys(specialtyName);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(),'" + specialtyName + "')]")
        ));
        option.click();
    }

    // CUIM - INPUT TEXT NORMAL
    public void fillInCodInregistrare(String codInregistrare) {
        LOG.info("Filling in CUIM: {}", codInregistrare);
        this.codInregistrare.sendKeys(codInregistrare);
    }

    // Grad de Specialitate - SELECT DROPDOWN MUI
    // Optiuni disponibile: "Rezident", "Specialist", "Primar"
    public void selectSpecialtyRank(String rank) {
        LOG.info("Selecting specialty rank: {}", rank);
        this.specialtyRank.click();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[@data-value='" + rank + "']")
        ));
        option.click();
    }

    // Loc de Munca - INPUT TEXT NORMAL
    public void fillInWorkingPlace(String workingPlace) {
        LOG.info("Filling in working place: {}", workingPlace);
        this.workingPlace.sendKeys(workingPlace);
    }

    // Judet - AUTOCOMPLETE/COMBOBOX
    public void fillInJudet(String judet) {
        LOG.info("Filling in judet: {}", judet);
        this.judet.sendKeys(judet);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(),'" + judet + "')]")
        ));
        option.click();
    }

    // Localitate - AUTOCOMPLETE/COMBOBOX
    public void fillInLocalitate(String localitate) {
        LOG.info("Filling in localitate: {}", localitate);
        this.localitate.sendKeys(localitate);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[contains(text(),'" + localitate + "')]")
        ));
        option.click();
    }

    // Nr Telefon - INPUT TEL (optional, maxlength=10)
    public void fillInPhoneNumber(String phone) {
        LOG.info("Filling in phone number: {}", phone);
        this.phoneNumber.sendKeys(phone);
    }

    // Buton Creaza Cont
    public void clickOnCreateButton() {
        LOG.info("Clicking on Create Account button...");
        this.createButton.click();
    }

    // Metoda completa pentru Pas 2 - toate campurile + Creaza Cont
    public void completeStep2(String specialty, String cuim, String rank,
                              String workplace, String county, String city) {
        LOG.info("Completing Step 2...");
        fillInSpecialtyName(specialty);
        fillInCodInregistrare(cuim);
        selectSpecialtyRank(rank);
        fillInWorkingPlace(workplace);
        fillInJudet(county);
        fillInLocalitate(city);
        clickOnCreateButton();
    }

    // Metoda completa Pas 2 CU telefon
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
    // AGREEMENT DIALOG - ASSERT FINAL
    // ================================================================

    // Verifica DOAR ca popup-ul apare dupa click pe Creaza Cont
    public boolean isAgreementDialogDisplayed() {
        LOG.info("Checking if Agreement Dialog is displayed...");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.MuiDialog-root")
        ));
        return dialog.isDisplayed();
    }

    // ================================================================
    // NAVIGARE LINKS
    // ================================================================

    public void clickOnLoginLink() {
        LOG.info("Clicking on 'Autentifica-te' link...");
        this.loginLink.click();
    }
}