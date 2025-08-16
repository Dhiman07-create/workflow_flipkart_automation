package pages.flows;

import io.appium.java_client.android.AndroidDriver;
import pages.mobile.*;

public class MobileShopFlow implements ShopFlow {
    private final AndroidDriver driver;

    public MobileShopFlow(AndroidDriver driver) { this.driver = driver; }

    @Override
    public void searchAddToCartPlaceOrderCOD(String query) {
        HomePageMobile home = new HomePageMobile(driver);
        var results = home.search(query);
        var pdp = results.openFirstProduct();
        var cart = pdp.addToCart();
        var checkout = cart.placeOrder();
        checkout.ensureAddressAndProceed()
                .chooseCODAndStopIfProd();
    }
}
