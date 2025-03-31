package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    // Find Elements -> Cái này chỉ hoạt động khi Element tồn tại trên Web
    @FindBy (xpath = "//div[contains(@class, 'product-price__current-price')]")
    WebElement priceCurrent;
    @FindBy (xpath = "//div[contains(@class, 'product-price__discount-rate')]")
    WebElement rateofDiscount;
    @FindBy (xpath = "//div[contains(@class, 'product-price__original-price')]")
    WebElement priceOriginal;
    @FindBy (xpath = "//div[contains(@class, 'sc-596b3c2b-1 iFCUnH')]//strong")
    List<WebElement> discountValues;

    // Get Price from Element
    public String getTextFromElement (WebElement element)
    {
        try {
            return element.getText().trim();
        } catch (Exception e)
        {
            System.out.println("Failed to get Text from element" + element);
            return null;
        }
    }

    // Loại bỏ các kí hiệu . đ trong giá, biến String thành Integer
    public int normalizePrice (String priceText)
    {
        return Integer.parseInt(priceText.replaceAll("[^\\d]", ""));
    }


    public boolean isPriceCorrectly ()
    {
        String currentPrice = getTextFromElement(priceCurrent);
        // Check co khuyen mai hay khong
        boolean isDiscount = !driver.findElements(By.xpath("//div[contains(@class, 'product-price__discount-rate')]")).isEmpty();
        boolean isOriginalDisplayed = !driver.findElements(By.xpath("//div[contains(@class, 'product-price__original-price')]")).isEmpty();

        String discountRate = "";
        if (isDiscount) {
            discountRate = getTextFromElement(rateofDiscount);
        } else {
            discountRate = "0%";
        }

        String originalPrice = "";
        if (isOriginalDisplayed) {
            originalPrice = getTextFromElement(priceOriginal);
        } else {
            originalPrice = currentPrice;
        }

        // Check price hien thi sau khi getText dung khong
        System.out.println("Current Price: " +currentPrice);
        System.out.println("Discount Rate: " + discountRate);
        System.out.println("Original Price: " + originalPrice);

        if (!isDiscount && !isOriginalDisplayed)
        {
            System.out.println("No discount applied, current price is correct");
            return true;
        } else
        {
            // Lấy số tiền được giảm cho từng voucher
            int totalDiscount = 0;
            for (WebElement discountMoney : discountValues)
            {
                totalDiscount = totalDiscount + normalizePrice(discountMoney.getText().trim());
            }

            // Thực hiện tính toán Final Price
            int finalPrice = normalizePrice(currentPrice);
            int basePrice = normalizePrice(originalPrice);
            int expectedFinalPrice = basePrice - totalDiscount;
            System.out.println("Expected: " + expectedFinalPrice);
            boolean isFinalPriceCountedCorrect = (expectedFinalPrice == finalPrice);
            System.out.println("Price is counting correctly with promo: " + isFinalPriceCountedCorrect);
            return isFinalPriceCountedCorrect;
        }
    }

    public boolean isDiscountRateCorrect () {
        String currentPrice = getTextFromElement(priceCurrent);
        String discountRate = getTextFromElement(rateofDiscount);
        String originalPrice = getTextFromElement(priceOriginal);

        // Check price hien thi sau khi getText dung khong
        System.out.println("Current Price: " + currentPrice);
        System.out.println("Discount Rate: " + discountRate);
        System.out.println("Original Price: " + originalPrice);
        // Thực hiện tính toán Final Price
        int totalDiscount = 0;
        for (WebElement discountMoney : discountValues) {
            totalDiscount = totalDiscount + normalizePrice(discountMoney.getText().trim());
        }

        // Thực hiện tính toán Final Price
        int finalPrice = normalizePrice(currentPrice);
        int basePrice = normalizePrice(originalPrice);
        int discountPercent = normalizePrice(discountRate);
        int expectedDiscountRate = (int) Math.round((1 - (finalPrice / (double) basePrice)) * 100);
        System.out.println("Expected: " + expectedDiscountRate);
        boolean isDiscountRateCorrect = (expectedDiscountRate == discountPercent);
        System.out.println("Discount Rate is counting correctly: " + isDiscountRateCorrect);
        return isDiscountRateCorrect;
    }

}
