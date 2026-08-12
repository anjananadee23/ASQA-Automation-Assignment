package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupLoginPage;
import utils.DataProviders;

/**
 * Automates TC04 (site's official "Test Case 2: Login User with correct email and password")
 * and TC05 ("Test Case 3: Login User with incorrect email and password").
 */
@Listeners(listeners.TestListener.class)
public class LoginTests extends BaseTest {

    @Test(description = "TC04 - Login with correct email and password", dataProvider = "validLoginData", dataProviderClass = DataProviders.class)
    public void testValidLogin(String email, String password) {
        SignupLoginPage signupLoginPage = homePage.goToSignupLogin();
        HomePage loggedInHome = signupLoginPage.login(email, password);

        Assert.assertTrue(
                loggedInHome.isLoggedIn(),
                "Expected 'Logged in as <username>' to be visible after a successful login."
        );
    }

    @Test(description = "TC05 - Login fails with incorrect email/password", dataProvider = "invalidLoginData", dataProviderClass = DataProviders.class)
    public void testLoginWithIncorrectCredentials(String email, String password) {
        SignupLoginPage signupLoginPage = homePage.goToSignupLogin();
        signupLoginPage.loginExpectingFailure(email, password);

        String actualError = signupLoginPage.getLoginErrorMessage();
        Assert.assertTrue(
                actualError.toLowerCase().contains("incorrect"),
                "Expected 'Your email or password is incorrect!' but got: " + actualError
        );
    }
}
