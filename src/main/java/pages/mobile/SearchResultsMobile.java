package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class SearchResultsMobile {
    private final AndroidDriver driver;
    private final By firstProduct = By.xpath("(//android.view.View[contains(@content-desc,'₹') or contains(@content-desc,'ratings')])[1]");

    public SearchResultsMobile(AndroidDriver driver) { this.driver = driver; }

    public ProductPageMobile openFirstProduct() {
        driver.findElement(firstProduct).click();
        return new ProductPageMobile(driver);
    }
}
