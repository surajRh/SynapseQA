package utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {

    @DataProvider(name="loginData")
    public static Object[][] logindata(){
        return new Object[][]{
                {"cua@example.com","test123"},
             //   {"wrong@example.com","wrongpass"},
              //  {"demo@test.com","password"}
        };
    }
}
