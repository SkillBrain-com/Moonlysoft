package pages;


import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.junit.Assert;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class PatientPage extends PageObject {

    @FindBy(xpath = "//a[@href='/patient']")
    private WebElement patientButton;

    @FindBy(xpath = "//button[@aria-label='open drawer']")
    private WebElement burger;

    @FindBy(xpath = "//h1[normalize-space()='Patients']")
    private WebElement titlePatientPage;

    @FindBy(xpath = "//button[normalize-space()='Add Patient']")
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

    @FindBy(xpath = "//div[@class='notistack-CollapseWrapper']")
    private WebElement notistack;

    @FindBy(xpath = "//p[text()='CNP is required']")
    private WebElement CNP_required;

    @FindBy(xpath = "//label[text()='Birth Date'] //following-sibling::div //div //button")
    private WebElement dateBitrth;

    @FindBy(xpath = "//button[normalize-space()='OK']")
    private WebElement oKDate;

    @FindBy(xpath = "//button[normalize-space()='New Request']")
    private WebElement requestButton;

    @FindBy(xpath = "//input[@placeholder='Enter request title']")
    private WebElement titleRequest;

    @FindBy(xpath = "//textarea[@placeholder='Describe the medical consultation or service needed']")
    private WebElement descriptionRequest;

    @FindBy(xpath = "//input[@placeholder='Search for a patient or add a new one']")
    private WebElement patientRequest;

    @FindBy(xpath = "//div[@aria-owns='_r_n6_-listbox']")
    private WebElement listBoxPatient;

    @FindBy(xpath = "//button[normalize-space()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//span[text()='Expert Assignment']")
    private WebElement textExpertAssignment;

    @FindBy(xpath = "//p[normalize-space()='Laurentiu Bucur'] // parent::div")
    private WebElement selectExpert;

    @FindBy(xpath = "//button[normalize-space()='Create Request']")
    private WebElement createRequestButton;

    @FindBy(xpath = "//svg[text()='Decline']")
    private WebElement declineButton;

    @FindBy(xpath = "//p[text()='Available cases']/preceding-sibling::div")
    private WebElement availableTabCounter;


    private static final Logger LOG = LoggerFactory.getLogger(pages.PatientPage.class);
    private int availableCounterBefore;


    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));

    public void goToPatientPage() {

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


    public void addPatient() {
        LOG.info("Add patient button was successfully clicked");
        wait.until(ExpectedConditions.visibilityOf(addPatientButton));
        addPatientButton.click();
    }

    public void completeFormAddPatient(String name, String cnp, String email, String weight, String conditions) {
        wait.until(ExpectedConditions.visibilityOf(fullNamePatient));
        fullNamePatient.clear();
        fullNamePatient.sendKeys(name);

        CNPPatient.clear();
        CNPPatient.sendKeys(cnp);

        emailPatient.clear();
        emailPatient.sendKeys(email);

        bloodTypePatient.click();
        selectBloodType("B+");

        weightKGPatient.clear();
        weightKGPatient.sendKeys(weight);

        conditionsPatient.clear();
        conditionsPatient.sendKeys(conditions);

        submitPatient.click();

    }

    public void selectBloodType(String bloodType) {
        String selector = String.format("//li[@data-value='%s']", bloodType);
        getDriver().findElement(By.xpath(selector)).click();
    }

    public void selectBirthDate(String date) {

        String day = date.split("-")[2];
        System.out.println(day);
        System.out.println(date);
        wait.until(ExpectedConditions.visibilityOf(dateBitrth));
        dateBitrth.click();
        WebElement day1 = getDriver().findElement(By.xpath(String.format("//button[text()='%s']", day)));
        wait.until(ExpectedConditions.visibilityOf(day1));
        day1.click();
        //  oKDate.click();
    }

    public void addingRequest() {
        requestButton.click();
    }

    public void generalInfoForm(String title, String description, String patient) {
        wait.until(ExpectedConditions.visibilityOf(titleRequest));
        titleRequest.click();
        titleRequest.sendKeys(title);
        descriptionRequest.click();
        descriptionRequest.sendKeys(description);
        patientRequest.click();
        patientRequest.sendKeys(patient);
        WebElement listRequestPatient = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(.,'" + patient + "')]")));
        assert listRequestPatient != null;
        listRequestPatient.click();
    }

    public void assignmentExpertTAB() {
        nextButton.click();
        String actualText = textExpertAssignment.getText();
        Assert.assertEquals("Expert Assignment", actualText);
    }

    public void chooseExpert() {
        selectExpert.click();
    }

    public void createRequest() {
        createRequestButton.click();
    }

    public void chackingCaseID() {
    //    WebElement lastRequestTitle = getDriver().findElement(By.xpath(String.format("//p[@aria-label='%s'][1]", titleRequest.getText())));
      WebElement lastRequestTitle1 = getDriver().findElement(By.xpath("//p[@aria-label='Back pain'][1]"));
        Assert.assertEquals("Back pain", lastRequestTitle1.getText());

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

    public void patientCreated() {
        wait.until(ExpectedConditions.visibilityOf(notistack));
        assertEquals("Patient created successfully!", notistack.getText());
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

    public void formWithoutCNP(String name) {
        wait.until(ExpectedConditions.visibilityOf(fullNamePatient));
        fullNamePatient.clear();
        fullNamePatient.sendKeys(name);
        submitPatient.click();
    }

    public void warningMessageCNP() {
        wait.until(ExpectedConditions.visibilityOf(CNP_required));
        assertEquals("CNP is required", CNP_required.getText());
    }


}




