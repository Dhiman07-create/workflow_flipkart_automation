package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CartPageMobile {
    private final AndroidDriver driver;
    private final By placeOrder = By.xpath("//android.widget.TextView[contains(@text,'Place Order')]");

    public CartPageMobile(AndroidDriver driver) { this.driver = driver; }

    public CheckoutPageMobile placeOrder() {
        driver.findElement(placeOrder).click();
        return new CheckoutPageMobile(driver);
    }
}
