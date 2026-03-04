package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static org.bouncycastle.cms.RecipientId.password;

public class Loginpage {
    WebDriver driver;

    public Loginpage(WebDriver driver){
        this.driver=driver;
    }

    private By emailField= By.xpath("//input[@data-qa='login-email']");
    private By passwordField= By.xpath("//input[@data-qa='login-password']");
    private By loginbutton= By.xpath("//button[@data-qa='login-button']");

    public void enterEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginbutton).click();
    }

    public void login(String email,String password){
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void verifyPageTitleHeading() {
        System.out.print("page tite is :" + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));
    }

    public void validateTotalLinksinTestPage() {
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
}
