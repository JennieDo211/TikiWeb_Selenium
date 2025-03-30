package Scripts;

import Pages.CategoryPage;
import Pages.HomePage;
import Pages.SearchResultPage;
import Pages.SubCategoryPage;
import Utils.BaseTest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class searchProduct extends BaseTest {
    HomePage homePage;

    SearchResultPage searchResultPage;
    // Search By Search Box
    @Test
    public void testSearchProductAdidas () {
        homePage = new HomePage(driver);
        searchResultPage = homePage.searchAction("adidas");
        logger.info("Search Xong");
        Assert.assertTrue(searchResultPage.isDisplayed("adidas"));
        Assert.assertTrue(searchResultPage.hasKeyword("adidas"));
    }

    @Test
    public void testSearchProductSamsung () {
        homePage = new HomePage(driver);
        searchResultPage = homePage.searchAction("samsung");
        Assert.assertTrue(searchResultPage.isDisplayed("samsung"));
        Assert.assertTrue(searchResultPage.hasKeyword("samsung"));
    }



}

