package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public static Logger logger = LogManager.getLogger("testBookingFromHomePage");
    public String baseURl = "https://tiki.vn/";

    @BeforeClass
    public void setupDriver ()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @BeforeMethod
    public void navigateHomePage ()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURl);
    }

    @AfterClass
    public void tearDown ()

    {
        driver.quit();
    }

}
