package pages;


import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;



public class PatientPage extends PageObject {

    @FindBy(xpath="//a[@href='/patient']")
    private WebElement patientButton;

    @FindBy(xpath="//button[@aria-label='open drawer']")
    private  WebElement burger;

    @FindBy(xpath = "//h1[normalize-space()='Patients']")
    private WebElement titlePatientPage;

    @FindBy(xpath="//button[normalize-space()='Add Patient']")
    private WebElement addPatientButton;

    @FindBy(xpath = "//input[@id='patient-name']")
    private WebElement fullNamePatient;

    @FindBy(xpath = "//input[@id='patient-cnp']")
    private WebElement CNPPatient;

    @FindBy(xpath = "//input[@id='patient-email']")
    private WebElement emailPatient;

    @FindBy(xpath = "//input[@placeholder='DD.MM.YYYY']")
    private WebElement birthDatePatient;

    @FindBy(xpath = "//div[@id='patient-blood-type']")
    private WebElement bloodTypePatient;

    @FindBy(xpath = "//input[@id='patient-weight']")
    private WebElement weightKGPatient;

    @FindBy(xpath = "//input[@id='patient-conditions']")
    private WebElement conditionsPatient;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement cancelFormPatient;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitPatient;


    private static final Logger LOG = LoggerFactory.getLogger(pages.PatientPage.class);
    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

    public void goToPatientPage(){

        wait.until(ExpectedConditions.visibilityOf(burger));
        if (burger.isDisplayed()) {
            burger.click();
        } else {
            LOG.info("Burger menu is not present and a clicks on patient tab.");
        }
        wait.until(ExpectedConditions.visibilityOf(patientButton));
        patientButton.click();
        Assert.assertEquals("Patients", titlePatientPage.getText());
    }


    public void addPatient(){
        LOG.info("Add patient button was successfully clicked");
        wait.until(ExpectedConditions.visibilityOf(addPatientButton));
        addPatientButton.click();
    }

    public void completeFormAddPatient(String name, String cnp, String email,String weight, String conditions){
        wait.until(ExpectedConditions.visibilityOf(fullNamePatient));
        fullNamePatient.clear();
        fullNamePatient.sendKeys(name);

        CNPPatient.clear();
        CNPPatient.sendKeys(cnp);

        emailPatient.clear();
        emailPatient.sendKeys(email);

        bloodTypePatient.click();
        selectBloodType("B+");

        selectBirthDate("15.11.1998");
        weightKGPatient.clear();
        weightKGPatient.sendKeys(weight);

        conditionsPatient.clear();
        conditionsPatient.sendKeys(conditions);

        submitPatient.click();

    }

    public void selectBloodType(String bloodType){
        String selector = String.format("//li[@data-value='%s']", bloodType);
        getDriver().findElement(By.xpath(selector)).click();
    }

    public void selectBirthDate(String date){
        WebElement birthDateInput = getDriver().findElement(By.xpath("//input[@placeholder='DD.MM.YYYY']"));
        birthDateInput.click();
        birthDateInput.clear();
        birthDateInput.sendKeys(date);
        birthDateInput.sendKeys(Keys.TAB);
//        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        js.executeScript("arguments[0].removeAttribute('readOnly')", birthDateInput);
//        birthDateInput.clear();
//        birthDateInput.sendKeys(birthDate);
//        birthDateInput.sendKeys(Keys.TAB);



//        wait.until(ExpectedConditions.elementToBeClickable(birthDatePatient));
//        Actions actions= new Actions(getDriver());
//        Action build = actions.moveToElement(birthDatePatient).click().sendKeys(birthDate).build();
//
//        build.perform();
}



    @FindBy(xpath = "//button[@aria-label='open drawer']")
    private WebElement burgerMenu;

    @FindBy(xpath = "//a[@href='/patient']")
    private WebElement patientsTab;

    @FindBy(xpath = "//button[normalize-space()='Add Patient']")
    private WebElement addPatient;

    @FindBy(xpath = "//input[@id='patient-name']")
    private WebElement fullNameField;

    @FindBy(xpath = "//input[@id='patient-cnp']")
    private WebElement cnpField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement confirmButton;

    @FindBy(xpath = "//p[@id='patient-cnp-helper-text']")
    private WebElement cnpErrorMessage;

    @FindBy(xpath = "//tbody//tr")
    private WebElement patientRows;

    @FindBy(xpath = "(//button[@aria-label='Delete'])")
    private WebElement deleteButton;

    @FindBy(xpath = "//button[normalize-space()='Delete']")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement toastNotification;



    @FindBy(xpath = "//p[@id='patient-name-helper-text']")
    private WebElement nameErrorMessage;



    public void navigateToPatientPage() {
        LOG.info("Navigating to patient page...");

        if (burgerMenu.isDisplayed()) {
            LOG.info("Burger menu found, clicking it first...");
            waitFor(burgerMenu).waitUntilClickable();
            burgerMenu.click();
            waitFor(patientsTab).waitUntilVisible();
            patientsTab.click();
        } else {
            LOG.info("No burger menu, clicking requests tab directly...");
            waitFor(patientsTab).waitUntilVisible();
            patientsTab.click();
        }


    }


    public void clickAddPatient() {
        LOG.info("Clicking Add Patient button...");
        addPatient.click();
    }


    public void fillInFullName(String fullName) {
        LOG.info("Waiting for fullNameField...");
        waitFor(fullNameField).waitUntilVisible();

        fullNameField.clear();
        fullNameField.sendKeys(fullName);
    }


    public void fillInInvalidCnp() {
        LOG.info("Filling in invalid CNP...");
        cnpField.clear();
        cnpField.sendKeys("123");
    }


    public void verifyCnpErrorMessageIsDisplayed() {
        LOG.info("Verifying CNP error message is displayed...");
        cnpErrorMessage.isDisplayed();
    }


    public void clickConfirmAddPatientButton() {
        LOG.info("Clicking confirm Add Patient button...");
        confirmButton.click();
    }

//    =================HAPPY FLOW==========================

    public void fillCNP(String cnp) {
        LOG.info("Filling CNP: {}", cnp);
        cnpField.clear();
        cnpField.sendKeys(cnp);
    }


    public boolean isPatientInTable(String fullName) {
        try {
            LOG.info("Checking if patient '{}' is in the table", fullName);

            WebElement row = getDriver().findElement(By.xpath("//tr[.//p[contains(text(), '" + fullName + "')]]"));

            LOG.info("Patient '{}' found in the table", fullName);
            return row.isDisplayed();

        } catch (Exception e) {
            LOG.info("Patient '{}' was NOT found in the table", fullName);
            return false;
        }
    }

//    ================DELETE PATIENT========================

    public boolean isAtLeastOnePatientInTable() {
        return !patientRows.getText().isEmpty();
    }

    public void clickDeleteForPatient(String name) {
        find(By.xpath("//tr[contains(.,'" + name + "')]//button[@aria-label='Delete']")).click();
    }


    public void confirmDeletion() {
        waitFor(confirmDeleteButton).click();
    }

    public boolean isToastNotificationVisible() {
        waitFor(toastNotification);
        return toastNotification.isDisplayed();
    }

//    ================ADD PATIENT - EMPTY NAME================

    public void leaveFullNameEmpty() {
        fullNameField.clear();
    }


    public boolean isNameErrorMessageDisplayed() {
        return nameErrorMessage.isDisplayed();
    }

}




