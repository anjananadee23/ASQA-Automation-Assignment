package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the "ENTER ACCOUNT INFORMATION" page shown after
 * submitting the initial name+email Signup form.
 */
public class AccountInformationPage extends BasePage {

    private final By titleMr = By.id("id_gender1");
    private final By password = By.id("password");
    private final By daysSelect = By.id("days");
    private final By monthsSelect = By.id("months");
    private final By yearsSelect = By.id("years");
    // newsletterCheckbox / offersCheckbox intentionally left unchecked (see fillDetailsAndSubmit)
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By address1 = By.id("address1");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("button[data-qa='create-account']");

    private final By accountCreatedMessage = By.cssSelector("h2[data-qa='account-created']");
    private final By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountInformationPage(WebDriver driver) {
        super(driver);
    }

    /** Fills the whole registration form with sensible defaults and submits it. */
    public AccountInformationPage fillDetailsAndSubmit(String passwordVal, String firstNameVal, String lastNameVal) {
        click(titleMr);
        type(password, passwordVal);
        selectByVisibleText(daysSelect, "10");
        selectByVisibleText(monthsSelect, "May");
        selectByVisibleText(yearsSelect, "1995");
        // Leave newsletter/offers unchecked for a faster, deterministic run; check them if your
        // test scenario explicitly requires it.
        type(firstName, firstNameVal);
        type(lastName, lastNameVal);
        type(address1, "123 Test Street");
        selectByVisibleText(country, "Canada");
        type(state, "Ontario");
        type(city, "Toronto");
        type(zipcode, "M4B1B3");
        type(mobileNumber, "0771234567");
        click(createAccountButton);
        return this;
    }

    private void selectByVisibleText(By locator, String visibleText) {
        new org.openqa.selenium.support.ui.Select(waitUtils.waitForVisible(locator)).selectByVisibleText(visibleText);
    }

    public boolean isAccountCreatedMessageDisplayed() {
        return isDisplayed(accountCreatedMessage);
    }

    public HomePage clickContinue() {
        click(continueButton);
        return new HomePage(driver);
    }
}
