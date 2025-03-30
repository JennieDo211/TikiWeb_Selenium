package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryPage {
    WebDriver driver;
    WebDriverWait wait;
    public CategoryPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Check Navigate
    public boolean isDisplayed (String categoryName)
    {
        WebElement categoryTitle = driver.findElement(By.xpath("//div[contains(@class, 'sc-4b956788-0 dUZwur')]//h2"));
        String categoryHeader = categoryTitle.getText();
        if (categoryHeader.contains(categoryName))
        {
            System.out.println("Dieu huong thanh cong Category Page");
            return true;
        } else
        {
            return false;
        }
    }

    // Find Elements
    @FindBy(xpath = "//div[contains(@class,'sc-9f1e84db-0 hzwFlv')]")
    WebElement subCategoryList;

    // Select SubCategory From Category Page
    public SubCategoryPage selectSubCategory (String subCategoryName)
    {
        try {
            WebElement subcategoryOption = subCategoryList.findElement(By.xpath(".//div[text() = '" + subCategoryName + "']"));
            Actions select = new Actions(driver);
            select.moveToElement(subcategoryOption).click().perform();
            Thread.sleep(3000);
            return new SubCategoryPage(driver);
        } catch (Exception e)
        {
            System.out.println("That Bai 3" + e.getMessage());
            return null;
        }
    }

}
