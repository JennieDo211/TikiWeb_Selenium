package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SubCategoryPage {
    WebDriver driver;
    WebDriverWait wait;
    public SubCategoryPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Check Navigate
    public boolean isDisplayed (String subCategoryName)
    {
        WebElement subCategoryTitle = driver.findElement(By.xpath("//div[contains(@class,'sc-4b956788-0 dUZwur')]//h2"));
        String subCategoryHeader = subCategoryTitle.getText();
        if (subCategoryHeader.contains(subCategoryName))
        {
            System.out.println("Dieu huong thanh cong SubCategory Page");
            return true;
        } else
        {
            return false;
        }
    }

    // Find Elements
    @FindBy(xpath = "//div[contains(@class, 'sc-2d0320b9-0 gHqeOl')]")
    WebElement productSubCategoryList;

    // Choose Product From SubCategory
    public ProductDetailPage selectProductFromCategory (String productName)
    {
        try
        {
            WebElement productOption = productSubCategoryList.findElement(By.xpath(".//h3[text() = '"+ productName +"']"));
            Actions chooseProduct = new Actions(driver);
            chooseProduct.moveToElement(productOption).click().perform();
            Thread.sleep(3000);
            return new ProductDetailPage(driver);
        } catch (Exception e)
        {
            System.out.println("Failed in Select product from SubCategory" + e.getMessage());
            return null;
        }

    }

}
