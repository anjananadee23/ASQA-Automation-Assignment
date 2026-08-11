package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for the "/view_cart" page.
 */
public class CartPage extends BasePage {

    private final By cartRows = By.cssSelector("#cart_info_table tbody tr");
    private final By productNameCell = By.cssSelector("td.cart_description h4 a");
    private final By quantityCell = By.cssSelector("td.cart_quantity button");
    private final By removeButtons = By.cssSelector(".cart_quantity_delete");
    private final By emptyCartMessage = By.xpath("//b[contains(text(),'Cart is empty')]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getCartItemCount() {
        return driver.findElements(cartRows).size();
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> names = driver.findElements(productNameCell);
        return names.stream().anyMatch(el -> el.getText().trim().equalsIgnoreCase(productName.trim()));
    }

    public String getQuantityForRow(int rowIndex) {
        List<WebElement> qtyButtons = driver.findElements(quantityCell);
        return qtyButtons.get(rowIndex).getText().trim();
    }

    public CartPage removeProductByIndex(int rowIndex) {
        List<WebElement> removeLinks = driver.findElements(removeButtons);
        removeLinks.get(rowIndex).click();
        return this;
    }

    public boolean isCartEmpty() {
        return isDisplayed(emptyCartMessage);
    }
}
