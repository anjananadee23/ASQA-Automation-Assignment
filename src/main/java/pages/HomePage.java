package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ConfigReader;

/**
 * Page Object for the AutomationExercise home page header/navigation.
 */
public class HomePage extends BasePage {

    // Navigation is done via direct URLs to avoid ad popups
    private final By logoutLink = By.cssSelector("a[href='/logout']");
    // The header shows "Logged in as <username>" inside a <li><a> when a session is active.
    private final By loggedInAsText = By.xpath("//a[contains(text(),'Logged in as')]");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public SignupLoginPage goToSignupLogin() {
        open(ConfigReader.getBaseUrl() + "login");
        return new SignupLoginPage(driver);
    }

    public ProductsPage goToProducts() {
        open(ConfigReader.getBaseUrl() + "products");
        return new ProductsPage(driver);
    }

    public CartPage goToCart() {
        open(ConfigReader.getBaseUrl() + "view_cart");
        return new CartPage(driver);
    }

    public boolean isLoggedIn() {
        return isDisplayed(loggedInAsText);
    }

    public String getLoggedInUserText() {
        return getText(loggedInAsText);
    }

    public HomePage logout() {
        click(logoutLink);
        return new HomePage(driver);
    }
}
