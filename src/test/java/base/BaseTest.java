package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

public class BaseTest {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    public void setup() {

        String browser = ConfigReader.getProperty("browser");
        log.info("Launching browser" + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());   // ✅ important
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(
                        Integer.parseInt(ConfigReader.getProperty("timeout"))));

        log.info("Opening url" + ConfigReader.getProperty("url"));
        getDriver().get(ConfigReader.getProperty("url"));
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();   // ✅ important for parallel
        }
    }
}
