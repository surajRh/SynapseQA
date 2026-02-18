package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.Loginpage;
import utils.ConfigReader;

import java.util.List;


public class FirstTest extends BaseTest {

    @BeforeClass
    public void start() {
        setup();
    }

    @Test(priority = 1)
    public void loginToUIAutomation() {

        Loginpage loginpage= new Loginpage(driver);
        loginpage.login("cua@example.com","test123");
    }

    @Test(priority = 2)
    public void verifyPageTitle() {
        Loginpage loginpage= new Loginpage(driver);
        loginpage.verifyPageTitleHeading();
    }

    @Test(priority = 3)
    public void validateTestPage() {
        Loginpage loginpage= new Loginpage(driver);
        loginpage.validateTotalLinksinTestPage();
    }
        @AfterClass
        public void end () {
            tearDown();
        }

    }

