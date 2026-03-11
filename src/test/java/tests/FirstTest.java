package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.Loginpage;
import utils.ConfigReader;
import utils.DataProviderUtils;

import java.util.List;



public class FirstTest extends BaseTest {

    @BeforeMethod
    public void start() {
        setup();
    }

    @Test(priority = 1, dataProvider ="loginData", dataProviderClass = DataProviderUtils.class)

    public void loginToUIAutomation(String email,String password) {
        Loginpage loginpage = new Loginpage(getDriver());
        loginpage.login(email,password);
    }

    @Test(priority = 2)
    public void verifyPageTitle() {
        Loginpage loginpage = new Loginpage(getDriver());
        loginpage.verifyPageTitleHeading();
    }

    @Test(priority = 3)
    public void validateTestPage() {
        Loginpage loginpage = new Loginpage(getDriver());
        loginpage.validateTotalLinksinTestPage();
    }

    @AfterMethod
    public void end () {
        tearDown();
    }
}


