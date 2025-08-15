package tests;

import base.BaseTest;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;

public class FlipkartTest extends BaseTest {

    @Test
    public void searchProductTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.closeLoginPopupIfPresent();

        HomePage homePage = new HomePage(driver);
        homePage.searchProduct("iPhone 14");
        assert homePage.isSearchResultDisplayed();
    }

    @Test
    public void addToCartTest() {

    }
}
