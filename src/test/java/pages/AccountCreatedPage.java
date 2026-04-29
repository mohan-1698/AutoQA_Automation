package pages;

import org.openqa.selenium.*;

public class AccountCreatedPage extends BasePage {

    private By continueBtn = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public void clickContinue() {
        waitForElement(continueBtn).click();
        System.out.println("Continue button clicked");
    }
}