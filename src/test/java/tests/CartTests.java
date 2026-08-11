package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductsPage;

/**
 * Automates TC11 (site's official "Test Case 12: Add Products in Cart").
 */
@Listeners(listeners.TestListener.class)
public class CartTests extends BaseTest {

    @Test(description = "TC11 - Add a product to the cart")
    public void testAddProductToCart() {
        ProductsPage productsPage = homePage.goToProducts();

        Assert.assertTrue(productsPage.getProductCount() > 0, "Precondition failed: no products listed.");

        String productName = productsPage.getProductNameByIndex(0);
        CartPage cartPage = productsPage.addProductToCartAndViewCart(0);

        Assert.assertTrue(
                cartPage.isProductInCart(productName),
                "Expected '" + productName + "' to appear in the cart after adding it."
        );
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Expected exactly 1 item in the cart.");
    }
}
