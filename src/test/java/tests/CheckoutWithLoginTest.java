package tests;

import base.BaseTest;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutWithLoginTest extends BaseTest {

    @Test
    public void testCheckoutFlow() {

        //Login (reuse your E2E login)
        LoginPage login = new LoginPage(driver);
        HomePage home = new HomePage(driver);

        home.clickSignupLogin();
        login.login("yourEmail@gmail.com", "Test@123");

        Assert.assertTrue(login.isLoginSuccessful());

        // Add to cart
        CartPage cart = new CartPage(driver);
        cart.addProductsFromConfig();

        cart.goToCart();

        //Checkout
        CheckoutPage checkout = new CheckoutPage(driver);

        checkout.clickProceedToCheckout();
        checkout.clickPlaceOrder();

        checkout.enterPaymentDetails();
        checkout.confirmOrder();

        System.out.println("CHECKOUT TEST PASSED");
    }
}