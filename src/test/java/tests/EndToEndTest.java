package tests;

import base.BaseTest;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseTest {

    @Test
    public void testCompleteE2EFlow() {

        // 🔹 STEP 1: Register
        SignupPage signup = new SignupPage(driver);
        signup.openSignupPage();

        String email = "bhavya" + System.currentTimeMillis() + "@gmail.com";

        System.out.println("STEP 1: Register");
        signup.enterDetails("Bhavya", email);
        signup.clickSignup();

        AccountInfoPage account = new AccountInfoPage(driver);
        account.fillAccountDetails();
        account.clickCreateAccount();

        AccountCreatedPage created = new AccountCreatedPage(driver);
        created.clickContinue();

        System.out.println("After Continue URL: " + driver.getCurrentUrl());

        // STEP 2: Logout
        HomePage home = new HomePage(driver);
        System.out.println("STEP 2: Logout");
        home.clickLogout();

        // STEP 3: Login
        System.out.println("STEP 3: Login");
        home.clickSignupLogin();

        LoginPage login = new LoginPage(driver);
        login.login(email, "Test@123");

        Assert.assertTrue(login.isLoginSuccessful(), "❌ Login failed");
        System.out.println("LOGIN SUCCESS");

        // STEP 4: Open Products
        ProductsPage product = new ProductsPage(driver);
        product.openProductsPage();

        //STEP 5: Add products
        System.out.println("STEP 4: Add Products");

        CartPage cart = new CartPage(driver);
        cart.addProductsFromConfig();

        // STEP 6: Go to Cart
        System.out.println("STEP 5: Go to Cart");
        cart.goToCart();

        // STEP 7: Checkout
        System.out.println("STEP 6: Checkout");

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.clickProceedToCheckout();
        checkout.clickPlaceOrder();

        //STEP 8: Payment
        System.out.println("STEP 7: Payment");

        checkout.enterPaymentDetails();
        checkout.confirmOrder();

        System.out.println("END-TO-END TEST PASSED");
    }
}