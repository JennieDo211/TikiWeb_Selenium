package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    public HomePage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Find Elements
    @FindBy (xpath = "//input[@data-view-id = 'main_search_form_input']")
    WebElement searchBox;

    // Search Action
    public SearchResultPage searchAction (String keyWord)
    {
        try {
            Actions search = new Actions(driver);
            search.moveToElement(searchBox).click().perform();
            search.sendKeys(keyWord, Keys.RETURN).perform();
            Thread.sleep(3000);
            return new SearchResultPage(driver);
        } catch (Exception e)
        {
            System.out.println("That Bai 1" + e.getMessage());
            return null;
        }
    }

    // Find Elements
    @FindBy (xpath = "//div[contains(@class, 'sc-cffe1c5-0 bKBPyH')]")
    WebElement categoryList;
//    @FindBy (xpath = "//div[contains(@class, 'sc-e6fb8ae7-3 kLhGA-D')]")
//    WebElement productPriceInfor;
//    @FindBy (xpath = "//div[contains(@class, 'product-price__current-price')]")
//    WebElement currentPrice;
//    @FindBy (xpath = "//div[contains(@class, 'product-price__discount-rate')]")
//    WebElement discountRate;
//    @FindBy (xpath = "//div[contains(@class, 'product-price__original-price')]")
//    WebElement originalPrice;

    // Select Category Action
    public CategoryPage selectCategory (String categoryName)
    {
        try {
            WebElement categoryOption = categoryList.findElement(By.xpath(".//a[@title='" + categoryName + "']"));
            Actions select = new Actions(driver);
            select.moveToElement(categoryOption).click().perform();
            Thread.sleep(3000);
            return new CategoryPage(driver);
        } catch (Exception e)
        {
            System.out.println("That Bai 2" + e.getMessage());
            return null;
        }
    }
}
