package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.bouncycastle.cms.RecipientId.password;


public class Loginpage {
    WebDriver driver;
    private static final Logger log = LogManager.getLogger(Loginpage.class);

    public Loginpage(WebDriver driver){
        this.driver=driver;
    }

    private By emailField= By.xpath("//input[@data-qa='login-email']");
    private By passwordField= By.xpath("//input[@data-qa='login-password']");
    private By loginbutton= By.xpath("//button[@data-qa='login-button']");

    public void enterEmail(String email){
        log.info("Entering email");
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password){
        log.info("Entering password");
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton(){
        log.info("Click login button");
        driver.findElement(loginbutton).click();
    }

    public void login(String email,String password){
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void verifyPageTitleHeading() {
    //    System.out.print("page tite is :" + driver.getTitle());
        log.info("Page title is" + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
        log.info("Page title is read successfully");
    }

    public void validateTotalLinksinTestPage() {
       // log.info("Navigating to Next page");
        driver.findElement(By.xpath("//a[contains(text(),' Test Cases')]")).click();
        List<WebElement> allLinks = driver.findElements(By.xpath("//div[@class='panel-group']"));
      //  System.out.println("total links :" + allLinks.size());
        log.info("Total links: " +allLinks.size());

        for (WebElement link : allLinks) {
            String linkText = link.getText();
            String url = link.getAttribute("href");

            // Only print if the link isn't empty
            if (!linkText.isEmpty()) {
                System.out.println("Test Case: " + linkText + " --> URL: " + url);
            }
        }
    }
}
