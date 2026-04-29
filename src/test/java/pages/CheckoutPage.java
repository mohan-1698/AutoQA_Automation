package pages;

import org.openqa.selenium.*;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class CheckoutPage extends BasePage {

    private By proceedCheckout = By.xpath("//a[contains(text(),'Proceed To Checkout')]");
    private By placeOrder = By.xpath("//a[contains(text(),'Place Order')]");

    private By nameOnCard = By.name("name_on_card");
    private By cardNumber = By.name("card_number");
    private By cvc = By.name("cvc");
    private By expiryMonth = By.name("expiry_month");
    private By expiryYear = By.name("expiry_year");

    private By payBtn = By.id("submit");

    private By loginText = By.xpath("//h2[contains(text(),'Login to your account')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickProceedToCheckout() {
        waitForElement(proceedCheckout).click();
    }

    public void clickPlaceOrder() {
        waitForElement(placeOrder).click();
    }

    public void enterPaymentDetails() {

        waitForElement(nameOnCard).sendKeys(ConfigReader.get("nameOnCard"));
        waitForElement(cardNumber).sendKeys(ConfigReader.get("cardNumber"));
        waitForElement(cvc).sendKeys(ConfigReader.get("cvc"));
        waitForElement(expiryMonth).sendKeys(ConfigReader.get("expiryMonth"));
        waitForElement(expiryYear).sendKeys(ConfigReader.get("expiryYear"));

        // TAKE SCREENSHOT HERE 
        ScreenshotUtil.capture(driver, "Payment_Page");

        System.out.println("Payment details entered");
    }

    public void confirmOrder() {
        waitForElement(payBtn).click();
        System.out.println("Order confirmed");
    }

    public boolean isRedirectedToLogin() {
        return waitForElement(loginText).isDisplayed();
    }
}