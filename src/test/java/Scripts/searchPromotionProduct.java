package Scripts;

import Pages.HomePage;
import Pages.ProductDetailPage;
import Pages.TikiTradingPage;
import Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class searchPromotionProduct extends BaseTest {
    HomePage homePage;
    TikiTradingPage tikiTradingPage;
    ProductDetailPage productDetailPage;
    @BeforeMethod
    public void testSelectTikiTrading ()
    {
        homePage = new HomePage(driver);
        tikiTradingPage = homePage.selectDealsCategoryNavBar("Tiki Trading");
        Assert.assertTrue(tikiTradingPage.isDisplayed("Tiki Trading"));
    }

    // Case 1: Product has no Promotion
    @Test
    public void testSelectProduct01 ()
    {
        tikiTradingPage.product("Phim Thấm Dầu Acnes Oil Remover Film 50 Tờ");
        Assert.assertFalse(tikiTradingPage.isProductPromotion());
    }

    // Case 2: Product has Promotion
    @Test
    public void testSelectProduct02 ()
    {
        tikiTradingPage.product("Miếng dán mụn giúp giảm mụn sưng viêm Acnes Clear Patch (24 Miếng)");
        Assert.assertTrue(tikiTradingPage.isProductPromotion());
    }

    // Verify Detail Infor of Promotion Product
    public void selectProduct (String productName, boolean expectDiscount, boolean expectVoucher)
    {
        productDetailPage = tikiTradingPage.selectProductFromTikiTrading(productName);
        Assert.assertTrue(productDetailPage.isDisplayed(productName));
        // Verify Discount
        Assert.assertEquals(productDetailPage.isProductDiscount(), expectDiscount, "Mismatch in discount expectation"); //Expect Discount là để gán kết quả mong đợi cho test

        if (expectDiscount) {
            Assert.assertTrue(productDetailPage.isDiscountRateCorrect(), "Discount rate incorrect");
        }
        // Verify Voucher
        Assert.assertEquals(productDetailPage.isProductHasVoucher(), expectVoucher, "Mismatch in voucher expectation");
    }

    // Case 01: Product Has No Promotion
    @Test
    public void testDetailInfor01 ()
    {
        selectProduct("Phim Thấm Dầu Acnes Oil Remover Film 50 Tờ", false, false);
        productDetailPage.viewDetailPrice();
    }

    // Case 02: Product Has Promotion
    @Test
    public void testDetailInfor02 ()
    {
        selectProduct("[Tặng Bộ picnic 2 ly và hộp đựng] Combo 2 Bịch Cà phê Hòa tan NESCAFÉ CAFÉ VIỆT 35 gói Vị Mạnh đặc trưng", true, true);
        productDetailPage.viewDetailPrice();
        Assert.assertTrue(productDetailPage.isPriceConsistency());
        Assert.assertTrue(productDetailPage.isPriceCountedCorrect());
    }



}
