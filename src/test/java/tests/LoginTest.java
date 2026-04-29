package tests;

import base.BaseTest;
import pages.LoginPage;
import pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ExcelUtil;
import utils.ScreenshotUtil;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = ExcelUtil.class)
    public void testLogin(String email, String password) {

        HomePage home = new HomePage(driver);
        home.clickSignupLogin();

        LoginPage login = new LoginPage(driver);

        login.login(email, password);

        // VALID LOGIN
        if (login.isLoginSuccessful()) {
            System.out.println("Login Successful");

            // logout check
            home.clickLogout();

        } else {
            System.out.println("Login Failed");

            // Screenshot on failure
            ScreenshotUtil.capture(driver, "LoginFailed");

            Assert.assertTrue(login.isErrorDisplayed(), "Error message not displayed");
        }
    }
}