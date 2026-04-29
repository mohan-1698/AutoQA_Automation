package tests;

import base.BaseTest;
import pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test
    public void testProductsModule() {

        ProductsPage product = new ProductsPage(driver);

        //STEP 1: Open Products Page
        System.out.println("STEP 1: Open Products Page");
        product.openProductsPage();

        // STEP 2: Search Product
        System.out.println("STEP 2: Search Product");
        product.searchProduct();

        // STEP 3: Verify Search Results
        System.out.println("STEP 3: Verify Search Results");
        Assert.assertTrue(product.isSearchResultDisplayed(),
                "Search results not displayed");

        //STEP 4: Select Category
        System.out.println("STEP 4: Select Category");
        product.selectCategoryFromConfig();

        //STEP 5: Verify Category Products
        System.out.println("STEP 5: Verify Category Products");
        Assert.assertTrue(product.isCategoryProductsDisplayed(),
                "Category products not displayed");

        // STEP 6: View Products & Print Prices (CONFIG-DRIVEN)
        System.out.println("STEP 6: View Products & Capture Prices");
        product.viewProductsAndPrintPrices();

        System.out.println("PRODUCTS TEST PASSED");
    }
}