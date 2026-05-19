package pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class PatientPage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(PatientPage.class);

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

//    @FindBy(xpath = "(//button[@aria-label='Edit'])[1]")
//    private WebElement editPatientButton;
//
//    @FindBy(xpath = "//button[normalize-space()='Update Patient']")
//    private WebElement updatePatientButton;

    @FindBy(xpath = "//p[@id='patient-name-helper-text']")
    private WebElement nameErrorMessage;

//    @FindBy(xpath = "//div[@role='dialog']")
//    private WebElement editDialog;


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
////    ================EDIT PATIENT========================
//
//    public void clickEditForPatient(String name) {
//        find(By.xpath(
//                "//tr[contains(.,'" + name + "')]//button[@aria-label='Edit']"
//        )).click();
//    }
//
//    public void clearFullNameField() {
//        new WebDriverWait(getDriver(), Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOf(fullNameField));
//        fullNameField.clear();
//
//    }
//
//    public void clickUpdatePatient() {
//        updatePatientButton.click();
//    }
//
//    public boolean isErrorMessageDisplayed() {
//        return waitFor(nameErrorMessage).isDisplayed();
//    }
//
//    public boolean isDialogOpen() {
//        return editDialog.isDisplayed();
//    }





