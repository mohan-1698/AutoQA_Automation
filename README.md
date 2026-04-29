# AutoQA - E-Commerce Automation Testing Framework

## Project Overview

AutoQA is a comprehensive Selenium WebDriver-based automation testing framework designed for testing e-commerce applications. The framework implements the Page Object Model (POM) pattern and includes data-driven testing with Excel integration, comprehensive reporting with Extent Reports, and configuration-driven test execution.

Framework Type: Selenium WebDriver + TestNG  
Language: Java  
Build Tool: Maven  
Java Version: 17  
Reporting: Extent Reports  
Data-Driven Testing: Excel (Apache POI) + Properties Files

---

## Project Flow

The automation framework follows a comprehensive test execution flow:

```
Start
  |
  v
Load Configuration (baseUrl, timeout, products)
  |
  v
Initialize WebDriver (Chrome with WebDriverManager)
  |
  v
Maximize Browser Window
  |
  v
Navigate to Base URL
  |
  v
Test Module Execution
  |
  +-- Registration Module
  |     |
  |     v
  |-- Open Signup Page
  |-- Enter User Details
  |-- Fill Account Information
  |-- Create Account
  |-- Verify Account Creation
  |     |
  |     v
  |
  +-- Login Module
  |     |
  |     v
  |-- Navigate to Login Page
  |-- Enter Email & Password
  |-- Verify Login Success or Error
  |     |
  |     v
  |
  +-- Products Module
  |     |
  |     v
  |-- Open Products Page
  |-- Search Products
  |-- Select Category (Config-Driven)
  |-- View Products & Capture Prices
  |     |
  |     v
  |
  +-- Cart Module
  |     |
  |     v
  |-- Add Products (Config-Driven)
  |-- Navigate to Cart
  |-- View Cart Items
  |     |
  |     v
  |
  +-- Checkout Module
  |     |
  |     v
  |-- Proceed to Checkout
  |-- Place Order
  |-- Enter Payment Details
  |-- Confirm Order
  |     |
  |     v
  |
  +-- End-to-End Module
  |     Complete flow: Register -> Login -> Products -> Cart -> Checkout
  |
  v
Capture Screenshots (on failure)
  |
  v
Generate Extent Report
  |
  v
TestListener captures results
  |
  v
Close WebDriver
  |
  v
End
```

---

## Project Structure

```
AutoQA/
|
├── src/
|   |
|   ├── main/
|   |   └── java/
|   |       └── com/srm/AutoQA/
|   |           ├── App.java                 (Main application class)
|   |
|   └── test/
|       ├── java/
|       |   ├── base/
|       |   |   └── BaseTest.java            (Base class with setup/teardown)
|       |   |
|       |   ├── pages/
|       |   |   ├── BasePage.java            (Reusable page methods & waits)
|       |   |   ├── HomePage.java            (Home page actions)
|       |   |   ├── LoginPage.java           (Login page locators & methods)
|       |   |   ├── SignupPage.java          (Signup page operations)
|       |   |   ├── AccountInfoPage.java     (Account details form)
|       |   |   ├── AccountCreatedPage.java  (Account creation confirmation)
|       |   |   ├── ProductsPage.java        (Products listing & search)
|       |   |   ├── CartPage.java            (Shopping cart operations)
|       |   |   └── CheckoutPage.java        (Checkout & payment)
|       |   |
|       |   ├── tests/
|       |   |   ├── EndToEndTest.java        (Complete E2E workflow test)
|       |   |   ├── RegisterTest.java        (User registration with data-driven)
|       |   |   ├── LoginTest.java           (Login with Excel data)
|       |   |   ├── ProductTest.java         (Products module testing)
|       |   |   ├── CartTest.java            (Cart functionality testing)
|       |   |   ├── CheckoutWithLoginTest.java     (Checkout after login)
|       |   |   └── CheckoutWithoutLoginTest.java  (Guest checkout)
|       |   |
|       |   └── utils/
|       |       ├── ConfigReader.java        (Read config.properties)
|       |       ├── ExcelUtil.java           (Read test data from Excel)
|       |       ├── DriverFactory.java       (WebDriver initialization)
|       |       ├── ExtentManager.java       (Extent report management)
|       |       ├── ScreenshotUtil.java      (Screenshot capture on failure)
|       |       ├── TestData.java            (TestNG data provider)
|       |       └── TestListener.java        (TestNG listener for reporting)
|       |
|       └── resources/
|           ├── config.properties            (Test configuration)
|           └── testdata/
|               └── LoginData.xlsx           (Excel test data)
|
├── reports/
|   └── ExtentReport.html                    (Generated HTML test report)
|
├── screenshots/
|   └── [Failed test screenshots]            (Captured on test failures)
|
├── target/                                  (Build artifacts)
|   ├── classes/
|   └── test-classes/
|
├── test-output/                             (TestNG reports)
|
├── testng.xml                               (Test suite configuration)
├── pom.xml                                  (Maven dependencies)
├── .project                                 (Eclipse project file)
├── .classpath                               (Eclipse classpath)
├── .git/                                    (Git repository)
├── .gitignore                               (Git ignore rules)
└── README.md                                (This file)
```

---

## Test Modules Details

### 1. EndToEndTest.java

Comprehensive end-to-end workflow covering the entire user journey.

**Test Methods:**
- testCompleteE2EFlow() - Complete flow from registration to payment

**Flow:**
1. Register new user with unique email
2. Fill account information
3. Verify account creation
4. Logout
5. Login with created credentials
6. Navigate to products
7. Add products to cart (config-driven)
8. Proceed to checkout
9. Enter payment details
10. Confirm order

---

### 2. RegisterTest.java

User registration testing with data-driven approach and valid/invalid login scenarios.

**Test Methods:**
- testFullFlow(String name, String emailPrefix) - Data-driven registration test

**Data Provider:**
- Uses TestData class with registerData provider
- Parameterized: name, emailPrefix

**Operations:**
1. Open signup/login page
2. Enter user name and email
3. Fill account information
4. Create account
5. Logout
6. Login with valid credentials
7. Verify valid login success
8. Logout again
9. Login with invalid password
10. Verify error message displayed

**Features:**
- Data-driven testing
- Dynamic email generation with timestamp
- Screenshot capture on invalid login

---

### 3. LoginTest.java

Login functionality testing with Excel data-driven approach.

**Test Methods:**
- testLogin(String email, String password) - Parameterized login test

**Data Source:**
- Excel file: src/test/resources/testdata/LoginData.xlsx
- Uses Apache POI for Excel reading
- Data provider: loginData

**Validation:**
- Valid login: Verify "Logged in as" message and logout
- Invalid login: Verify error message displayed
- Screenshot capture on login failure

---

### 4. ProductTest.java

Products module functionality testing including search and category filtering.

**Test Methods:**
- testProductsModule() - Tests products functionality

**Operations:**
1. Open products page
2. Search for product
3. Verify search results display
4. Select category from config
5. Verify category products display
6. View products and print prices

**Features:**
- Config-driven category selection
- Price capture and logging
- Search functionality validation

---

### 5. CartTest.java

Shopping cart operations testing.

**Test Methods:**
- testCartWithConfigProducts() - Tests cart with config-driven products

**Operations:**
1. Open products page
2. Add products from configuration
3. Navigate to cart
4. Verify cart displays items

**Features:**
- Config-driven product addition
- Cart navigation validation

---

### 6. CheckoutWithLoginTest.java

Checkout flow for authenticated users.

**Operations:**
- Login to application
- Add products to cart
- Proceed to checkout
- Enter payment details
- Confirm order

---

### 7. CheckoutWithoutLoginTest.java

Guest checkout flow without user registration.

**Operations:**
- Browse products without login
- Add products to cart
- Proceed to checkout as guest
- Enter payment details
- Complete order

---

## Page Objects

All page interactions are implemented using Page Object Model (POM) pattern:

| Page Class | Purpose |
|-----------|---------|
| BasePage | Common methods and explicit waits |
| HomePage | Home page navigation (logout, signup/login links) |
| LoginPage | Login form and validation |
| SignupPage | User registration form |
| AccountInfoPage | Account details (address, phone, etc.) |
| AccountCreatedPage | Account creation confirmation |
| ProductsPage | Products listing, search, and category filter |
| CartPage | Shopping cart operations |
| CheckoutPage | Checkout and payment |

---

## Base Classes

### BaseTest.java

Provides common setup and teardown for all tests.

**Methods:**
- setUp() (Before each test)
  - Initializes WebDriver using WebDriverManager
  - Loads configuration
  - Maximizes browser window
  - Navigates to base URL

- tearDown() (After each test)
  - Closes WebDriver
  - Cleans up resources

**Implementation:**
- Uses TestNG annotations
- Public WebDriver instance for test classes

### BasePage.java

Common methods and utilities for all page objects.

**Methods:**
- waitForElement(By locator) - Explicit wait for element visibility
- Timeout configured via ConfigReader

**Features:**
- WebDriverWait integration
- Configuration-driven timeouts

---

## Configuration Management

### config.properties

Located at: src/test/resources/config.properties

Contains externalized test configuration:
- baseUrl - Application URL
- timeout - WebDriver wait timeout in seconds
- Product names for testing
- Category selections
- Payment details
- User credentials

---

## Data-Driven Testing

### Excel Data (Apache POI)

**File:** src/test/resources/testdata/LoginData.xlsx

**Structure:**
- Row 1: Headers (email, password)
- Rows 2+: Test data

**Format:**
- Column 1: Email addresses
- Column 2: Passwords

### TestNG Data Provider

**Class:** TestData.java

**Methods:**
- registerData - Data for registration tests
- Provides: name, emailPrefix parameters

---

## Dependencies

Maven Dependencies (from pom.xml):

| Dependency | Version | Purpose |
|-----------|---------|---------|
| TestNG | 7.11.0 | Test framework & annotations |
| Selenium Java | 4.34.0 | WebDriver & element interaction |
| Apache POI | 5.4.1 | Excel file reading |
| Apache POI OOXML | 5.4.1 | OOXML file support (.xlsx) |
| WebDriverManager | 5.8.0 | Automatic driver management |
| Extent Reports | 5.1.1 | HTML test reporting |
| Commons-IO | 2.15.1 | File I/O operations |
| JUnit | 3.8.1 | Additional assertions |

---

## How to Run Tests

### Prerequisites:
- Java 17 or higher
- Maven 3.6+
- Chrome browser installed
- Excel test data file configured

### Run All Tests:
```bash
mvn clean test
```

### Run Specific Test Class:
```bash
mvn clean test -Dtest=EndToEndTest
```

### Run Specific Test Method:
```bash
mvn clean test -Dtest=RegisterTest#testFullFlow
```

### Run Tests from testng.xml:
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Run with Maven Verify:
```bash
mvn clean verify
```

---

## TestNG Suite Configuration (testng.xml)

**Suite Name:** AutomationSuite

**Listeners:**
- TestListener class for reporting

**Test Modules:**
- Currently configured to run: EndToEndTest
- Can add more test classes to the suite

---

## Test Execution & Reporting

### Extent Report

**Location:** reports/ExtentReport.html

**Contains:**
- Test execution summary
- Pass/Fail/Skip statistics
- Test execution timeline
- Screenshots on failure
- Detailed logs

### Screenshots

**Location:** screenshots/

**Captured On:**
- Test failure
- File naming: {TestMethodName}.png

### TestNG Report

**Location:** test-output/

**Contains:**
- TestNG default HTML report
- Pass/Fail statistics
- Execution time metrics

---

## Test Execution Flow

### Setup Phase (@BeforeMethod):
1. Initialize WebDriver (Chrome)
2. Load configuration properties
3. Maximize browser window
4. Navigate to base URL
5. Store driver in test context

### Test Execution Phase:
1. Create page object instances
2. Perform user interactions
3. Assert expected results
4. Capture data if needed

### Teardown Phase (@AfterMethod):
1. Close WebDriver
2. Clean up resources

### Reporting Phase (TestListener):
1. Log test start
2. On success: Mark as passed
3. On failure: Capture screenshot, log error, mark as failed
4. On completion: Generate Extent report

---

## Page Object Methods Reference

### HomePage
- clickLogout() - Click logout button
- clickSignupLogin() - Click signup/login link

### LoginPage
- login(String email, String password) - Enter credentials and login
- isLoginSuccessful() - Verify "Logged in as" message
- isErrorDisplayed() - Verify error message

### SignupPage
- openSignupPage() - Navigate to signup page
- enterDetails(String name, String email) - Enter name and email
- clickSignup() - Submit signup form

### AccountInfoPage
- fillAccountDetails() - Fill account information form
- clickCreateAccount() - Submit account creation

### ProductsPage
- openProductsPage() - Navigate to products page
- searchProduct() - Search for product
- selectCategoryFromConfig() - Select category from configuration
- viewProductsAndPrintPrices() - View products and log prices

### CartPage
- addProductsFromConfig() - Add products from configuration
- goToCart() - Navigate to cart page

### CheckoutPage
- clickProceedToCheckout() - Proceed to checkout
- clickPlaceOrder() - Place order
- enterPaymentDetails() - Enter payment information
- confirmOrder() - Confirm order placement

---

## Utilities Reference

### ConfigReader
Reads configuration from properties file:
```java
String baseUrl = ConfigReader.get("baseUrl");
String timeout = ConfigReader.get("timeout");
```

### ExcelUtil
Reads test data from Excel file:
```java
Object[][] loginData = ExcelUtil.getLoginData();
```

### ScreenshotUtil
Captures screenshots on failure:
```java
ScreenshotUtil.capture(driver, "ScreenshotName");
```

### ExtentManager
Manages Extent report instance:
```java
ExtentReports extent = ExtentManager.getReportInstance();
```

### DriverFactory
Initializes WebDriver:
```java
WebDriver driver = DriverFactory.initDriver();
```

---

## Key Features

[+] Comprehensive E-Commerce testing framework  
[+] Page Object Model (POM) implementation  
[+] Data-driven testing with Excel integration  
[+] Configuration-driven test execution  
[+] Extent Reports for detailed HTML reporting  
[+] WebDriverManager for automatic driver management  
[+] Screenshot capture on test failure  
[+] TestNG listener integration for reporting  
[+] Multiple test scenarios (E2E, Registration, Login, Products, Cart, Checkout)  
[+] Explicit waits for reliable element interaction  
[+] Externalized configuration for easy maintenance  

---

## Design Patterns Used

1. Page Object Model (POM) - Separation of test logic and page locators
2. Singleton Pattern - ExtentManager for single report instance
3. ThreadLocal Pattern - Thread-safe report logging
4. Factory Pattern - DriverFactory for WebDriver initialization
5. Base Class Pattern - BasePage & BaseTest for code reuse
6. Data-Driven Testing - TestNG DataProvider with Excel/Properties

---

## Best Practices Implemented

1. Explicit waits used for element interaction
2. Configuration externalized for flexibility
3. Data-driven tests for scalability
4. Screenshot capture on failure for debugging
5. Comprehensive reporting with Extent Reports
6. Meaningful test method names
7. Clear separation of concerns
8. Reusable page methods
9. Proper exception handling
10. Thread-safe test execution

---

## Common Test Scenarios

### 1. User Registration
- New user registration with unique email
- Account information verification
- Account creation confirmation

### 2. Login
- Valid credential login
- Invalid credential handling
- Error message verification

### 3. Product Search & Filtering
- Product search functionality
- Category filtering
- Price verification

### 4. Shopping Cart
- Add products to cart
- View cart items
- Cart item count verification

### 5. Checkout
- Proceed to checkout
- Place order
- Payment information entry
- Order confirmation

### 6. End-to-End
- Complete user journey from registration to order completion

---

## Troubleshooting

### Common Issues:

| Issue | Solution |
|-------|----------|
| WebDriver not initialized | Check WebDriverManager setup, ensure Chrome installed |
| Element not found | Verify locators in page objects, check dynamic elements |
| Timeout exceptions | Increase timeout in config.properties |
| Excel data not read | Verify Excel file path and format |
| Report not generated | Check reports/ folder permissions, verify ExtentManager |
| Screenshots not captured | Ensure screenshots/ folder exists and has permissions |

---

## Extending the Framework

### Add New Test Class:
1. Create class extending BaseTest
2. Implement page objects
3. Write test methods with TestNG annotations
4. Add to testng.xml suite

### Add New Page Object:
1. Create class extending BasePage
2. Define page locators using By
3. Implement page methods
4. Use in test classes

### Add Data Provider:
1. Create method in TestData class
2. Annotate with @DataProvider
3. Return Object[][]
4. Use in test method parameter

---

## Notes

- All tests use explicit waits for better reliability
- Test data is externalized for easy modification
- Screenshots are automatically captured on test failure
- Framework supports parallel execution (configurable in testng.xml)
- Configuration-driven approach allows different environment testing
- Excel data provides flexibility for test data management

---
