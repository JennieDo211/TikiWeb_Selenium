package Utils;

import drivers.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public static Logger logger = LogManager.getLogger("testBookingFromHomePage");
    public String baseURl = "https://tiki.vn/";

//    @Parameters ({"browser"})
//    public void setupDriver (String browser)
//    {
//        switch (browser)
//        {
//            case "Chrome":
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//                break;
//            case "Safari":
//                WebDriverManager.safaridriver().setup();
//                driver = new SafariDriver();
//                break;
//            case "Edge":
//                WebDriverManager.edgedriver().setup();
//                driver = new EdgeDriver();
//                break;
//            default:
//                System.out.println("This browser is not Supported");
//        }
//        driver.manage().window().maximize();
//    }

    @BeforeMethod
    @Parameters({"browser"})
    public void navigateHomePage(String browser) {
        logger.info("Setting up WebDriver for: " + browser);

        DriverFactory driverFactory = new DriverFactory();
        driverFactory.setDriver(browser);
        driver = driverFactory.getDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(baseURl);
    }

    public WebDriver getDriver ()
    {
        return driver;
    } void teardown ()
    {
        driver.quit();
    }

    @AfterMethod
    public void tearDown () {
        driver.quit();
    }

}
