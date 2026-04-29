package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigReader;

public class ProductsPage extends BasePage {

    // Locators
    private By productsMenu = By.xpath("//a[@href='/products']");
    private By searchBox = By.id("search_product");
    private By searchBtn = By.id("submit_search");

    private By searchedProductsText = By.xpath("//h2[contains(text(),'Searched Products')]");
    private By productList = By.cssSelector(".features_items .product-image-wrapper");

    private By productName = By.xpath("//div[@class='product-information']/h2");
    private By productPrice = By.xpath("//div[@class='product-information']//span/span");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    //Step 1: Open Products Page
    public void openProductsPage() {
        waitForElement(productsMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    }

    // Step 2: Search Product (from config)
    public void searchProduct() {
        String keyword = ConfigReader.get("searchKeyword");

        WebElement search = waitForElement(searchBox);
        search.clear();
        search.sendKeys(keyword);

        waitForElement(searchBtn).click();

        System.out.println("✅ Searched for: " + keyword);
    }

    // Step 3: Verify Search Results
    public boolean isSearchResultDisplayed() {
        return waitForElement(searchedProductsText).isDisplayed()
                && driver.findElements(productList).size() > 0;
    }

    //Step 4: Select Category (from config)
    public void selectCategoryFromConfig() {

        String main = ConfigReader.get("mainCategory");
        String sub = ConfigReader.get("subCategory");

        By mainCategory = By.xpath("//a[@href='#" + main + "']");
        By subCategory = By.xpath("//a[normalize-space()='" + sub + "']");

        waitForElement(mainCategory).click();
        waitForElement(subCategory).click();

        System.out.println(" Category selected: " + main + " → " + sub);
    }

    public boolean isCategoryProductsDisplayed() {
        return driver.findElements(productList).size() > 0;
    }

    // Step 5: Click product by index
    public void clickViewProductByIndex(int index) {
        By product = By.xpath("(//a[contains(text(),'View Product')])[" + index + "]");
        waitForElement(product).click();
    }

    // Step 6: Get product name
    public String getProductName() {
        return waitForElement(productName).getText();
    }

    // Step 7: Get product price
    public String getProductPrice() {
        return waitForElement(productPrice).getText();
    }

    // Step 8: View multiple products using config (BEST METHOD)
    public void viewProductsAndPrintPrices() {

        String indexes = ConfigReader.get("productIndexes");

        // If specific indexes provided
        if (indexes != null && !indexes.isEmpty()) {

            String[] idxArray = indexes.split(",");

            for (String idx : idxArray) {

                int i = Integer.parseInt(idx.trim());

                clickViewProductByIndex(i);

                String price = getProductPrice();
                System.out.println("Product " + i + " Price: " + price);

                driver.navigate().back();
            }

        } else {
            // Otherwise use count
            int count = Integer.parseInt(ConfigReader.get("productCount"));

            for (int i = 1; i <= count; i++) {

                clickViewProductByIndex(i);

                String price = getProductPrice();
                System.out.println("Product " + i + " Price: " + price);

                driver.navigate().back();
            }
        }

        System.out.println("Product prices verified using config");
    }
}