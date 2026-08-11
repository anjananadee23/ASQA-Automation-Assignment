package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;
import utils.ConfigReader;

/**
 * Automates TC04 (site's official "Test Case 2: Login User with correct email and password")
 * and TC05 ("Test Case 3: Login User with incorrect email and password").
 *
 * IMPORTANT: Update VALID_EMAIL / VALID_PASSWORD with an account that actually exists
 * (e.g., register one manually first, or reuse the email printed by RegistrationTests).
 */
@Listeners(listeners.TestListener.class)
public class LoginTests extends BaseTest {

    private static final String VALID_EMAIL    = ConfigReader.get("validEmail");
    private static final String VALID_PASSWORD  = ConfigReader.get("validPassword");

    @Test(description = "TC04 - Login with correct email and password")
    public void testValidLogin() {
        SignupLoginPage signupLoginPage = homePage.goToSignupLogin();
        HomePage loggedInHome = signupLoginPage.login(VALID_EMAIL, VALID_PASSWORD);

        Assert.assertTrue(
                loggedInHome.isLoggedIn(),
                "Expected 'Logged in as <username>' to be visible after a successful login."
        );
    }

    @Test(description = "TC05 - Login fails with incorrect email/password")
    public void testLoginWithIncorrectCredentials() {
        SignupLoginPage signupLoginPage = homePage.goToSignupLogin();
        signupLoginPage.loginExpectingFailure("not.a.real.user@example.com", "WrongPassword123");

        String actualError = signupLoginPage.getLoginErrorMessage();
        Assert.assertTrue(
                actualError.toLowerCase().contains("incorrect"),
                "Expected 'Your email or password is incorrect!' but got: " + actualError
        );
    }
}
