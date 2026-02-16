package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;

import java.util.List;


public class FirstTest extends BaseTest {

    @BeforeClass
    public void start() {
        setup();
    }

    @Test(priority = 1)
    public void loginToUIAutomation() {

        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("cua@example.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();
    }

    @Test(priority = 2)
    public void verifyPageTitle() {
        System.out.print("page tite is :" + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
    }

    @Test(priority = 3)
    public void validateTestPage() {
        driver.findElement(By.xpath("(//button[contains(text(),'Test Cases')])[1]")).click();
        List<WebElement> allLinks = driver.findElements(By.xpath("//div[@class='panel-group']"));
        System.out.println("total links :" + allLinks.size());

        for (WebElement link : allLinks) {
            String linkText = link.getText();
            String url = link.getAttribute("href");

            // Only print if the link isn't empty
            if (!linkText.isEmpty()) {
                System.out.println("Test Case: " + linkText + " --> URL: " + url);
            }
        }
    }
        @AfterClass
        public void end () {
            tearDown();
        }

    }

