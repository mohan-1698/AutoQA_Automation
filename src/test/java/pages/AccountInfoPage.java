package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import utils.ConfigReader;

public class AccountInfoPage extends BasePage {

    private By titleMr = By.id("id_gender2");
    private By password = By.id("password");

    private By day = By.id("days");
    private By month = By.id("months");
    private By year = By.id("years");

    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By address = By.id("address1");

    private By state = By.id("state");
    private By city = By.id("city");
    private By zipcode = By.id("zipcode");
    private By mobile = By.id("mobile_number");

    private By createAccountBtn = By.cssSelector("button[data-qa='create-account']");

    public AccountInfoPage(WebDriver driver) {
        super(driver);
    }

    public void fillAccountDetails() {

        waitForElement(password);

        driver.findElement(titleMr).click();

        driver.findElement(password).sendKeys(ConfigReader.get("password"));

        new Select(driver.findElement(day)).selectByValue("10");
        new Select(driver.findElement(month)).selectByValue("5");
        new Select(driver.findElement(year)).selectByValue("2000");

        driver.findElement(firstName).sendKeys(ConfigReader.get("firstname"));
        driver.findElement(lastName).sendKeys(ConfigReader.get("lastname"));
        driver.findElement(address).sendKeys(ConfigReader.get("address"));

        driver.findElement(state).sendKeys(ConfigReader.get("state"));
        driver.findElement(city).sendKeys(ConfigReader.get("city"));
        driver.findElement(zipcode).sendKeys(ConfigReader.get("zipcode"));
        driver.findElement(mobile).sendKeys(ConfigReader.get("mobile"));
    }

    public void clickCreateAccount() {
        waitForElement(createAccountBtn).click();
    }
}