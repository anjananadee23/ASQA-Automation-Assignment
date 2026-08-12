package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginData() {
        return new Object[][]{
                {ConfigReader.get("validEmail"), ConfigReader.get("validPassword")}
        };
    }

    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {
        return new Object[][]{
                {"not.a.real.user@example.com", "WrongPassword123"},
                {"another.invalid@example.com", "Invalid123"}
        };
    }
}
