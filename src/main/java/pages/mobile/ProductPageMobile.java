package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductPageMobile {
    private final AndroidDriver driver;
    private final By addToCart = By.xpath("//android.widget.TextView[contains(@text,'Add to cart') or contains(@text,'ADD TO CART')]");
    private final By buyNow    = By.xpath("//android.widget.TextView[contains(@text,'Buy Now') or contains(@text,'BUY NOW')]");

    public ProductPageMobile(AndroidDriver driver) { this.driver = driver; }

    public CartPageMobile addToCart() {
        driver.findElement(addToCart).click();
        return new CartPageMobile(driver);
    }

    public CheckoutPageMobile buyNow() {
        driver.findElement(buyNow).click();
        return new CheckoutPageMobile(driver);
    }
}
