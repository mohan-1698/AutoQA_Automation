package pages;

import org.openqa.selenium.*;

public class LoginPage extends BasePage {

    private By loginEmail = By.cssSelector("input[data-qa='login-email']");
    private By loginPassword = By.cssSelector("input[data-qa='login-password']");
    private By loginBtn = By.cssSelector("button[data-qa='login-button']");

    // CORRECT VALIDATION
    private By loggedInText = By.xpath("//a[contains(text(),'Logged in as')]");
    private By errorMsg = By.xpath("//p[contains(text(),'incorrect')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {

        waitForElement(loginEmail).clear();
        driver.findElement(loginEmail).sendKeys(email);

        waitForElement(loginPassword).clear();
        driver.findElement(loginPassword).sendKeys(password);

        waitForElement(loginBtn).click();
    }

    public boolean isLoginSuccessful() {
        return waitForElement(loggedInText).isDisplayed();
    }

    public boolean isErrorDisplayed() {
        return waitForElement(errorMsg).isDisplayed();
    }
}