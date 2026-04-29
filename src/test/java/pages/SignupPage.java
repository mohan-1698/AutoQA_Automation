package pages;

import org.openqa.selenium.*;

public class SignupPage extends BasePage {

    // Locators
    private By signupLoginBtn = By.xpath("//a[@href='/login']");

    private By nameField = By.name("name");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");

    private By signupBtn = By.xpath("//button[text()='Signup']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    // Open Signup/Login Page
    public void openSignupPage() {
        waitForElement(signupLoginBtn).click();
    }

    // ADD THIS METHOD 
    public void enterDetails(String name, String email) {
        waitForElement(nameField).sendKeys(name);
        waitForElement(emailField).sendKeys(email);

        System.out.println("Entered Name: " + name);
        System.out.println("Entered Email: " + email);
    }

    // ADD THIS METHOD 
    public void clickSignup() {
        waitForElement(signupBtn).click();
        System.out.println("Signup button clicked");
    }

    // Validation methods (optional)
    public void submitEmptyForm() {
        waitForElement(signupBtn).click();
    }

    public void enterInvalidEmail() {
        waitForElement(nameField).sendKeys("Bhavya");
        waitForElement(emailField).sendKeys("invalidemail");
        waitForElement(signupBtn).click();
    }

    public boolean isValidationTriggered() {
        WebElement email = waitForElement(emailField);
        String msg = email.getAttribute("validationMessage");

        System.out.println("Validation Message: " + msg);

        return msg != null && !msg.isEmpty();
    }
}