package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;

public class DriverFactory {
    WebDriver driver;
    public void setDriver (String browser)
    {
        switch (browser)
        {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "Safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("This browser is not Supported");
        }
        driver.manage().window().maximize();
    }
    public WebDriver getDriver(){
        return driver;
    }
}
