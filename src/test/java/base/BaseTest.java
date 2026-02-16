package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    public void setup(){

        String browser= ConfigReader.getProperty("browser");

        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        else{
            throw new RuntimeException("Browser not suported" +browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("timeout"))));
        driver.get(ConfigReader.getProperty("url"));
    }

    public void tearDown(){
        if(driver!=null)
        {
            driver.quit();
        }
    }
}
