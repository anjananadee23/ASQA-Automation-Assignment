package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the "SEARCHED PRODUCTS" results view (still served at /products,
 * but with the product grid filtered and a "Searched Products" heading shown).
 */
public class SearchResultsPage extends BasePage {

    private final By searchedProductsTitle = By.cssSelector(".title.text-center");
    private final By productItems = By.cssSelector(".features_items .product-image-wrapper");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchedProductsTitleDisplayed() {
        return getText(searchedProductsTitle).toLowerCase().contains("searched products");
    }

    public int getResultCount() {
        return driver.findElements(productItems).size();
    }
}
