package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for the "/products" (ALL PRODUCTS) page:
 * search box, product grid, and hover-to-reveal "Add to cart" overlay.
 */
public class ProductsPage extends BasePage {

    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By productItems = By.cssSelector(".features_items .product-image-wrapper");
    // addToCartOverlayLink is scoped per-item inline; no class-level By needed
    private final By continueShoppingButton = By.cssSelector("button.close-modal.btn-success");
    private final By viewCartModalLink = By.cssSelector("#cartModal a[href='/view_cart']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage searchFor(String keyword) {
        type(searchInput, keyword);
        click(searchButton);
        return new SearchResultsPage(driver);
    }

    public int getProductCount() {
        return driver.findElements(productItems).size();
    }

    /**
     * Hovers over the Nth product (0-indexed) to reveal the overlay, then clicks "Add to cart",
     * and dismisses the confirmation modal via "Continue Shopping".
     */
    public ProductsPage addProductToCartByIndex(int index) {
        List<WebElement> items = driver.findElements(productItems);
        WebElement item = items.get(index);
        jsClick(item.findElement(By.cssSelector(".productinfo .btn.add-to-cart")));
        waitUtils.waitForVisible(continueShoppingButton);
        jsClick(continueShoppingButton);
        return this;
    }

    /** Same as above, but goes straight to the cart instead of continuing to shop. */
    public CartPage addProductToCartAndViewCart(int index) {
        List<WebElement> items = driver.findElements(productItems);
        WebElement item = items.get(index);
        jsClick(item.findElement(By.cssSelector(".productinfo .btn.add-to-cart")));
        waitUtils.waitForVisible(viewCartModalLink);
        jsClick(viewCartModalLink);
        return new CartPage(driver);
    }

    /** Returns the product name for the Nth product, used for later assertions in the cart. */
    public String getProductNameByIndex(int index) {
        List<WebElement> items = driver.findElements(productItems);
        return items.get(index).findElement(By.cssSelector("p")).getText().trim();
    }
}
