package utils;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() {

        return new Object[][]{
                {"Bhavya", "bhavya"},
                {"TestUser", "testuser"},
//                {"DemoUser", "demo"}
        };
    }
}