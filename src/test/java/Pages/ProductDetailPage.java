package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
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
    public int normalizePrice(String priceText) {
        if (priceText == null || priceText.isEmpty()) {
            System.out.println("Warning: Price text is null or empty!");
            return 0; // Default value
        }
        return Integer.parseInt(priceText.replaceAll("[^\\d]", ""));
    }

    // Product Detail Elements -> Cái này chỉ hoạt động khi Element tồn tại trên Web
    @FindBy (xpath = "//div[contains(@class, 'product-price__current-price')]")
    WebElement priceCurrent;
    @FindBy (xpath = "//div[contains(@class, 'product-price__discount-rate')]")
    WebElement rateofDiscount;
    @FindBy (xpath = "//div[contains(@class, 'product-price__original-price')]")
    WebElement priceOriginal;
    @FindBy (xpath = "//div[contains(@class, 'sc-596b3c2b-1 iFCUnH')]//strong")
    List<WebElement> discountValues;

    // Instance variables to store extracted prices
    public String currentPrice;
    public String discountRate;
    public String originalPrice;

    // Check coi product có discount không
    public boolean isProductDiscount ()
    {
        currentPrice = getTextFromElement(priceCurrent);
        // Check co khuyen mai hay khong
        boolean isDiscount = !driver.findElements(By.xpath("//div[contains(@class, 'product-price__discount-rate')]")).isEmpty();
        boolean isOriginalDisplayed = !driver.findElements(By.xpath("//div[contains(@class, 'product-price__original-price')]")).isEmpty();

        discountRate = "";
        if (isDiscount) {
            discountRate = getTextFromElement(rateofDiscount);
        } else {
            discountRate = "0%";
        }

        originalPrice = "";
        if (isOriginalDisplayed) {
            originalPrice = getTextFromElement(priceOriginal);
        } else {
            originalPrice = currentPrice;
        }

        // Check price hien thi sau khi getText dung khong
        System.out.println("Current Price: " + currentPrice);
        System.out.println("Discount Rate: " + discountRate);
        System.out.println("Original Price: " + originalPrice);

        if (!isDiscount && !isOriginalDisplayed)
        {
            System.out.println("No discount applied, current price is correct");
            return false;
        } else
        {
            System.out.println("Product has discount");
            return true;
        }
    }


    // Check discount rate tính đúng không
    public boolean isDiscountRateCorrect ()
    {
        int finalPrice = normalizePrice(currentPrice);
        int basePrice = normalizePrice(originalPrice);
        int discountPercent = normalizePrice(discountRate);
        int expectedDiscountRate = (int) Math.round((1 - (finalPrice / (double) basePrice)) * 100);
        System.out.println("Expected: " + expectedDiscountRate);
        if (expectedDiscountRate == discountPercent)
        {
            return true;
        } else {
            System.out.println("Discount Percent incorrect");
            return false;
        }
    }

    public boolean isProductHasVoucher ()
    {
        boolean isProductHasVoucher = !driver.findElements(By.xpath("//div[contains(@class, 'sc-596b3c2b-1 iFCUnH')]")).isEmpty();
        if (isProductHasVoucher)
        {
            System.out.println("Product Has Voucher");
            return true;
        } else
        {
            System.out.println("Product Has No Voucher");
            return false;
        }
    }

    @FindBy (xpath = "//div[contains(@aria-describedby, 'popup-1')]//picture[contains(@class, 'webpimg-container')]")
    WebElement priceMoreInforButton;

    public void viewDetailPrice ()
    {
        try {
            Actions clickPopup = new Actions(driver);
            clickPopup.moveToElement(priceMoreInforButton).click().perform();
            Thread.sleep(3000);
        } catch (Exception e)
        {
            System.out.println("Failed to view Popup" + e.getMessage());
        }
    }

    // Pop-up elements
    @FindBy (xpath = "//div[text() = 'Giá gốc']//following-sibling::div")
    WebElement popupOriginalPrice;
    @FindBy (xpath = "//div[contains(text(),'Giá bán')]/ancestor::div[contains(@class, 'info')]/div[contains(@class, 'info__price')]")
    WebElement popupPriceFromSeller;
    @FindBy (xpath = "//div[contains(text(),'Giá sau áp dụng mã khuyến mãi')]/ancestor::div[contains(@class, 'info')]/div[contains(@class, 'info__price')]")
    WebElement popupPriceAfterDiscount;

    // Popup value
    public String popupBase;
    public String popupSeller;
    public String popupFinal;

    public boolean isPriceConsistency ()
    {
            // Get Text tu Popup
            popupBase = getTextFromElement(popupOriginalPrice);
            popupSeller = getTextFromElement(popupPriceFromSeller);
            popupFinal = "";
            if (isProductHasVoucher ()) {
                popupFinal = getTextFromElement(popupPriceAfterDiscount);
            } else {
                popupFinal = popupSeller;
            }

            // In ra console de check
            System.out.println("Popup Original Price: " + popupBase);
            System.out.println("Popup Seller Price: " + popupSeller);
            System.out.println("Popup Final Price: " + popupFinal);


        if (popupBase.equals(originalPrice) && popupFinal.equals(currentPrice))
            {
                System.out.println("Price consistency");
                return true;
            } else {
                System.out.println("Mismatch Price");
                return false;
            }
        }

    public boolean isPriceCountedCorrect ()
    {
        // Lấy số tiền được giảm cho từng voucher
        int totalDiscount = 0;
        for (WebElement discountMoney : discountValues)
        {
            totalDiscount = totalDiscount + normalizePrice(discountMoney.getText().trim());
        }

        // Thực hiện tính toán Final Price
        int finalPrice = normalizePrice(currentPrice);
        int popupSellPrice = normalizePrice(popupSeller);
        int expectedPrice = popupSellPrice - totalDiscount;
        if (expectedPrice == finalPrice)
        {
            System.out.println("Price is counted Correctly");
            return true;
        } else
        {
            System.out.println("Price is counted incorrect");
            return false;
        }
    }

}
