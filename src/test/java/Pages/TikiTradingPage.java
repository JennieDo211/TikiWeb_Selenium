package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TikiTradingPage {
    WebDriver driver;
    WebDriverWait wait;

    public TikiTradingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Check Navigate
    public boolean isDisplayed(String promotionTab) {
        WebElement tikiTradingTitle = driver.findElement(By.xpath("//div[contains(@class, 'sc-b4c2fa32-5 dwjueM')]//h1"));
        String tikiTradingLabel = tikiTradingTitle.getText();
        if (tikiTradingLabel.contains(promotionTab)) {
            System.out.println("Dieu huong den Tiki Trading Thanh Cong");
            return true;
        } else {
            return false;
        }
    }

    // Check Promotion
    @FindBy(xpath = "//div[contains(@class, 'sc-d086bdc6-0 iuFRcE ALL_PRODUCT')]")
    WebElement allProduct;

    // Get Price and Promotion from Element
    public String getTextFromElement(WebElement element) {
        try {
            return element.getText().trim();
        } catch (Exception e) {
            System.out.println("Failed to get Text from element" + element);
            return null;
        }
    }

    // Action move to product to check Promotion
    public void product(String productName)
    {
        try {
            Actions move = new Actions(driver);
            move.scrollToElement(allProduct).perform();
            productOption = driver.findElement(By.xpath("//div[contains(@class, 'p-container')][.//h3[text() = '" + productName + "']]"));
            move.moveToElement(productOption).perform();
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Failed to hover product" + e.getMessage());
        }
    }
    public WebElement productOption;
    public boolean isProductPromotion() {
        boolean hasPromotion = !productOption.findElements(By.xpath(".//div[contains(@class, 'sc-e14980c3-9 cspIwy has-discount')]")).isEmpty();
        boolean hasDiscountPercent = !productOption.findElements(By.xpath(".//div[contains(@class, 'sc-e14980c3-10 iasvUQ')]")).isEmpty();

        String promotionPrice = "";
        if (hasPromotion) {
            WebElement productPromoPrice = productOption.findElement(By.xpath(".//div[contains(@class, 'sc-e14980c3-9 cspIwy has-discount')]"));
            promotionPrice = getTextFromElement(productPromoPrice);
        } else {
            WebElement nonPromoPrice = productOption.findElement(By.xpath(".//div[contains(@class, 'sc-e14980c3-9 cspIwy')]"));
            promotionPrice = getTextFromElement(nonPromoPrice);
        }

        String promotionPercent = "";
        if (hasDiscountPercent) {
            WebElement productPromoteRate = productOption.findElement(By.xpath(".//div[contains(@class, 'sc-e14980c3-10 iasvUQ')]"));
            promotionPercent = getTextFromElement(productPromoteRate);
        } else {
            promotionPercent = "0%";
        }

        // Check price hien thi sau khi getText dung khong
        System.out.println("Current Price: " + promotionPrice);
        System.out.println("Discount Rate: " + promotionPercent);

        if (!hasPromotion && !hasDiscountPercent) {
            System.out.println("No discount applied, current price is correct");
            return false;
        } else {
            System.out.println("Product has Promotion");
            return true;
        }
    }

    // Select Product and go to Product Detail Page
    public ProductDetailPage selectProductFromTikiTrading (String productName)
    {
        try {
            Actions move = new Actions(driver);
            move.scrollToElement(allProduct).perform();
            productOption = driver.findElement(By.xpath("//div[contains(@class, 'p-container')][.//h3[text() = '" + productName + "']]"));
            move.moveToElement(productOption).click().perform();
            Thread.sleep(3000);
            return new ProductDetailPage(driver);
        } catch (Exception e) {
            System.out.println("Failed to hover product" + e.getMessage());
            return null;
        }
    }




}



