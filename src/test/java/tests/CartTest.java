package tests;

import base.BaseTest;
import pages.CartPage;
import pages.ProductsPage;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void testCartWithConfigProducts() {

        ProductsPage product = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);

        // STEP 1: Open Products Page
        System.out.println("STEP 1: Open Products Page");
        product.openProductsPage();

        // STEP 2: Add products using config
        System.out.println("STEP 2: Add Products from Config");
        cart.addProductsFromConfig();

        // STEP 3: Go to Cart
        System.out.println("STEP 3: Navigate to Cart");
        cart.goToCart();

        System.out.println("CART TEST PASSED");
    }
}