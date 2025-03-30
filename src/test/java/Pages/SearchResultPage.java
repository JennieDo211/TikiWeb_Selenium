package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {
    WebDriver driver;
    WebDriverWait wait;
    public SearchResultPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isDisplayed (String keyword)
    {
        if (driver.getCurrentUrl().contains(keyword))
        {
            System.out.println("Navigate success");
            return true;
        } else
        {
            return false;
        }
    }

    // Find Elements
    @FindBy (xpath = "//div[contains(@class, 'sc-2d0320b9-0 gHqeOl')]//h3")
    List<WebElement> searchResultList;

    // Check Search Result List co contain keyword
    public boolean hasKeyword(String keyword) {
        boolean matchingKeyWord = true;
        for (int i = 0; i < searchResultList.size() && i < 10; i++)
        {
            WebElement title = searchResultList.get(i);
            String productTitle = title.getText().toLowerCase().trim();
            if (!productTitle.contains(keyword)) {
                System.out.println("Mismatch: " + title.getText()); // Print only mismatched ones
                matchingKeyWord = false;
            } else
            {
                System.out.println("Matching: " + title.getText());
            }
        }
        return matchingKeyWord;
    }


}
