package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AccountInformationPage;
import pages.HomePage;
import pages.SignupLoginPage;

/**
 * Automates TC02 (site's official "Test Case 1: Register User"):
 * Verify a new user can register with valid details.
 */
@Listeners(listeners.TestListener.class)
public class RegistrationTests extends BaseTest {

    @Test(description = "TC02 - Register a new user with valid details")
    public void testUserCanRegisterWithValidDetails() {
        String uniqueEmail = "qauser" + System.currentTimeMillis() + "@example.com";
        String name = "John Tester";

        // Step 1: initial "New User Signup!" mini-form (name + email)
        SignupLoginPage signupLoginPage = homePage.goToSignupLogin();
        AccountInformationPage accountInfoPage = signupLoginPage.signup(name, uniqueEmail);

        // Step 2: full "ENTER ACCOUNT INFORMATION" form
        accountInfoPage.fillDetailsAndSubmit("Test@1234", "John", "Tester");

        Assert.assertTrue(
                accountInfoPage.isAccountCreatedMessageDisplayed(),
                "Expected 'ACCOUNT CREATED!' message to be visible after submitting the full registration form."
        );

        HomePage loggedInHome = accountInfoPage.clickContinue();

        Assert.assertTrue(
                loggedInHome.isLoggedIn(),
                "Expected 'Logged in as <username>' to be visible on the home page after registration."
        );
    }
}
