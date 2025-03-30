package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailPage {
    WebDriver driver;
    WebDriverWait wait;
    public ProductDetailPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDisplayed (String productName)
    {
        WebElement productHeader = driver.findElement(By.xpath("//div[contains(@class, 'sc-f71dfc2-0 bIhKUr')]//h1"));
        String productTitle = productHeader.getText();
        if (productTitle.contains(productName))
        {
            System.out.println("Dieu Huong Thanh Cong Den Product Detail Page");
            return true;
        } else
        {
            return false;
        }
    }

}
