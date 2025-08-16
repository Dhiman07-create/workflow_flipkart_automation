package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ConfigReader;

public class HomePageMobile {
    private final AndroidDriver driver;

    private final By searchBox = By.xpath("//android.widget.TextView[contains(@text,'Search for Products') or contains(@text,'Search for products')]");
    private final By searchEdit = By.className("android.widget.EditText");

    public HomePageMobile(AndroidDriver driver) { this.driver = driver; }

    public SearchResultsMobile search(String query) {
        driver.findElement(searchBox).click();
        driver.findElement(searchEdit).sendKeys(query + "\n");
        return new SearchResultsMobile(driver);
    }
}
