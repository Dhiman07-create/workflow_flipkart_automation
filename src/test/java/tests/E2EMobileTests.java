package tests;

import base.MobileDriverFactory;
import pages.flows.MobileShopFlow;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;

public class E2EMobileTests {

    @BeforeMethod(alwaysRun = true)
    public void setUpMobile() { MobileDriverFactory.initAndroid(); }

    @AfterMethod(alwaysRun = true)
    public void tearDownMobile() { MobileDriverFactory.quit(); }

    @Test(groups = {"mobile","smoke"})
    public void mobile_e2e_buy_cod_single() {
        AndroidDriver driver = MobileDriverFactory.get();
        new MobileShopFlow(driver).searchAddToCartPlaceOrderCOD("iPhone 14");
    }
}
