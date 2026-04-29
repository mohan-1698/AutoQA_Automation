package pages;

import org.openqa.selenium.*;

public class HomePage extends BasePage {

    private By logoutBtn = By.cssSelector("a[href='/logout']");
    private By signupLoginBtn = By.cssSelector("a[href='/login']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLogout() {
        waitForElement(logoutBtn).click();
        System.out.println("Logout clicked");
    }

    public void clickSignupLogin() {
        waitForElement(signupLoginBtn).click();
        System.out.println("Signup/Login clicked again");
    }
}