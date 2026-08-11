package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.SearchResultsPage;

/**
 * Automates TC08 (site's official "Test Case 9: Search Product").
 */
@Listeners(listeners.TestListener.class)
public class SearchTests extends BaseTest {

    @Test(description = "TC08 - Search for a product by keyword returns matching results")
    public void testSearchReturnsResults() {
        ProductsPage productsPage = homePage.goToProducts();
        SearchResultsPage resultsPage = productsPage.searchFor("dress");

        Assert.assertTrue(
                resultsPage.isSearchedProductsTitleDisplayed(),
                "Expected the 'SEARCHED PRODUCTS' heading to be visible."
        );

        int resultCount = resultsPage.getResultCount();
        Assert.assertTrue(
                resultCount > 0,
                "Expected at least one product in the search results for 'dress', but found " + resultCount
        );
    }
}
