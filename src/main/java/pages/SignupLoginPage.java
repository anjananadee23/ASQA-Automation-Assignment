package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the combined "/login" page: it hosts both the
 * New User Signup form (name + email) and the existing-user Login form.
 */
public class SignupLoginPage extends BasePage {

    // --- New User Signup form ---
    private final By signupNameInput = By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmailInput = By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton = By.cssSelector("button[data-qa='signup-button']");
    private final By signupErrorMessage = By.cssSelector("form[action='/signup'] p"); // "Email Address already exist!"

    // --- Existing User Login form ---
    private final By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private final By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");
    private final By loginErrorMessage = By.cssSelector("form[action='/login'] p");

    public SignupLoginPage(WebDriver driver) {
        super(driver);
    }

    /** Fills the "New User Signup!" mini-form and submits it. */
    public AccountInformationPage signup(String name, String email) {
        type(signupNameInput, name);
        type(signupEmailInput, email);
        click(signupButton);
        return new AccountInformationPage(driver);
    }

    /**
     * Attempts signup expecting it to fail (e.g., duplicate email) and stay on this
     * page.
     */
    public SignupLoginPage signupExpectingFailure(String name, String email) {
        type(signupNameInput, name);
        type(signupEmailInput, email);
        click(signupButton);
        return this;
    }

    public String getSignupErrorMessage() {
        return getText(signupErrorMessage);
    }

    /**
     * Logs in with valid credentials and lands on the (now logged-in) home page.
     */
    public HomePage login(String email, String password) {
        type(loginEmailInput, email);
        type(loginPasswordInput, password);
        click(loginButton);
        return new HomePage(driver);
    }

    /** Attempts login expecting it to fail and stay on this page. */
    public SignupLoginPage loginExpectingFailure(String email, String password) {
        type(loginEmailInput, email);
        type(loginPasswordInput, password);
        click(loginButton);
        return this;
    }

    public String getLoginErrorMessage() {
        return getText(loginErrorMessage);
    }
}
