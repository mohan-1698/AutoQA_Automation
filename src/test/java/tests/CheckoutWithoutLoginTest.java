package tests;

import base.BaseTest;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutWithoutLoginTest extends BaseTest {

    @Test
    public void testCheckoutWithoutLogin() {

        ProductsPage product = new ProductsPage(driver);
        CartPage cart = new CartPage(driver);

        // 🔹 Open products
        product.openProductsPage();

        // Add product
        cart.addProductsFromConfig();
        cart.goToCart();

        //Try checkout
        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clickProceedToCheckout();

        // Validate redirect
        Assert.assertTrue(checkout.isRedirectedToLogin(),
                "Not redirected to login");

        System.out.println("REDIRECT TO LOGIN VERIFIED");
    }
}