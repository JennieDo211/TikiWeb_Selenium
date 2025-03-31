package Scripts;

import Pages.*;
import Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class searchProductByCategory extends BaseTest {
    HomePage homePage;
    SearchResultPage searchResultPage;
    CategoryPage categoryPage;
    SubCategoryPage subCategoryPage;
    ProductDetailPage productDetailPage;

    // Search By Category
    @Test
    public void testSelectByCategory ()
    {
        homePage = new HomePage(driver);
        categoryPage = homePage.selectCategory("Thiết Bị Số - Phụ Kiện Số");
        Assert.assertTrue(categoryPage.isDisplayed("Thiết Bị Số - Phụ Kiện Số"));
        subCategoryPage = categoryPage.selectSubCategory("Thiết Bị Âm Thanh và Phụ Kiện");
        Assert.assertTrue(subCategoryPage.isDisplayed("Thiết Bị Âm Thanh và Phụ Kiện"));
    }

    @Test
    public void testSelectProduct ()
    {
        testSelectByCategory();
        productDetailPage = subCategoryPage.selectProductFromCategory("Tai Nghe Nhét Tai Langsdom Super Bass JM26");
        Assert.assertTrue(productDetailPage.isDisplayed("Tai Nghe Nhét Tai Langsdom Super Bass JM26"));
        Assert.assertTrue(productDetailPage.isPriceCorrectly());
//        Assert.assertTrue(productDetailPage.isDiscountRateCorrect());
    }
}
