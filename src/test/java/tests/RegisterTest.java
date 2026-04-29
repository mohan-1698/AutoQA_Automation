package tests;

import base.BaseTest;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;
import utils.ScreenshotUtil;

public class RegisterTest extends BaseTest {

    @Test(dataProvider = "registerData", dataProviderClass = TestData.class)
    public void testFullFlow(String name, String emailPrefix) {

        SignupPage signup = new SignupPage(driver);

        System.out.println("STEP 1: Open Signup/Login");
        signup.openSignupPage();

        String email = emailPrefix + System.currentTimeMillis() + "@gmail.com";

        System.out.println("STEP 2: Enter Name & Email");
        signup.enterDetails(name, email);
        signup.clickSignup();

        System.out.println("STEP 3: Fill Account Information");
        AccountInfoPage account = new AccountInfoPage(driver);
        account.fillAccountDetails();
        account.clickCreateAccount();

        System.out.println("STEP 4: Click Continue");
        AccountCreatedPage createdPage = new AccountCreatedPage(driver);
        createdPage.clickContinue();

        HomePage home = new HomePage(driver);

        System.out.println("STEP 5: Logout");
        home.clickLogout();

        System.out.println("STEP 6: Navigate back to Signup/Login");
        home.clickSignupLogin();

        //VALID LOGIN
        System.out.println("STEP 7: Valid Login");

        LoginPage login = new LoginPage(driver);
        login.login(email, "Test@123");

        if (login.isLoginSuccessful()) {
            System.out.println("✅ Valid Login Successful");
        } else {
            Assert.fail("Valid login failed");
        }

        //Logout again
        home.clickLogout();
        home.clickSignupLogin();

        //INVALID LOGIN
        System.out.println("STEP 8: Invalid Login");

        login.login(email, "wrong123");

        if (login.isErrorDisplayed()) {
            System.out.println("❌ Invalid Login Verified");
            ScreenshotUtil.capture(driver, "InvalidLogin");
        } else {
            Assert.fail("Error message not displayed for invalid login");
        }

        System.out.println("FULL FLOW COMPLETED");
    }
}