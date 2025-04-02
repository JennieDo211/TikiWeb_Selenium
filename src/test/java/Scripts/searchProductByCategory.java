package Scripts;

import Pages.*;
import Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class searchProductByCategory extends BaseTest {
    HomePage homePage;
    SearchResultPage searchResultPage;
    CategoryPage categoryPage;
    SubCategoryPage subCategoryPage;
    ProductDetailPage productDetailPage;

    // Search By Category
    @BeforeMethod
    public void testSelectByCategory ()
    {
        homePage = new HomePage(driver);
        categoryPage = homePage.selectCategory("Thiết Bị Số - Phụ Kiện Số");
        Assert.assertTrue(categoryPage.isDisplayed("Thiết Bị Số - Phụ Kiện Số"));
        subCategoryPage = categoryPage.selectSubCategory("Thiết Bị Âm Thanh và Phụ Kiện");
        Assert.assertTrue(subCategoryPage.isDisplayed("Thiết Bị Âm Thanh và Phụ Kiện"));
    }

    public void selectProduct (String productName, boolean expectDiscount, boolean expectVoucher)
    {
        productDetailPage = subCategoryPage.selectProductFromCategory(productName);
        Assert.assertTrue(productDetailPage.isDisplayed(productName));
        // Verify Discount
        Assert.assertEquals(productDetailPage.isProductDiscount(), expectDiscount, "Mismatch in discount expectation"); //Expect Discount là để gán kết quả mong đợi cho test

        if (expectDiscount) {
            Assert.assertTrue(productDetailPage.isDiscountRateCorrect(), "Discount rate incorrect");
        }
        // Verify Voucher
        Assert.assertEquals(productDetailPage.isProductHasVoucher(), expectVoucher, "Mismatch in voucher expectation");
    }

    // Case 1: Select Product Has No Discount
    @Test
    public void testSelectProduct01 ()
    {
        selectProduct("Tai Nghe Bluetooth Lenovo Livepods LP40 TWS Hàng Chính Hãng", false, false);
    }

    // Case 2: Select Product Has Discount but NO Voucher
    @Test
    public void testSelectProduct02 ()
    {
        selectProduct("Tai nghe cho Iphone chân dẹt cao cấp Hoco. M111 Max/Pro hỗ trợ nghe gọi mic đầy đủ, cắm dùng trực tiếp không cần kết nối bluetooth - Hàng chính hãng", true, false);
        productDetailPage.viewDetailPrice();
        Assert.assertTrue(productDetailPage.isPriceConsistency());
    }

    // Case 3: Select Product with Discount and Voucher
    @Test
    public void testSelectProduct03 ()
    {
        selectProduct("Tai nghe chụp tai không dây Remax RB-660HB - Hàng chính hãng", true, true);
        productDetailPage.viewDetailPrice();
        Assert.assertTrue(productDetailPage.isPriceConsistency());
        Assert.assertTrue(productDetailPage.isPriceCountedCorrect());
    }

}
