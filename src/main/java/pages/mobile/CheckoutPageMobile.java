package pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import utils.ConfigReader;

public class CheckoutPageMobile {
    private final AndroidDriver driver;

    private final By deliverHere = By.xpath("//android.widget.TextView[contains(@text,'Deliver Here')]");
    private final By continueBtn = By.xpath("//android.widget.TextView[contains(@text,'CONTINUE') or contains(@text,'Continue')]");
    private final By codOption   = By.xpath("//android.widget.TextView[contains(@text,'Cash on Delivery') or contains(@text,'COD')]");
    private final By confirmBtn  = By.xpath("//android.widget.TextView[contains(@text,'CONFIRM ORDER') or contains(@text,'Pay')]");

    public CheckoutPageMobile(AndroidDriver driver) { this.driver = driver; }

    public CheckoutPageMobile ensureAddressAndProceed() {
        try { driver.findElement(deliverHere).click(); } catch (Exception ignored) {}
        try { driver.findElement(continueBtn).click(); } catch (Exception ignored) {}
        return this;
    }

    public void chooseCODAndStopIfProd() {
        driver.findElement(codOption).click();
        if ("prod".equalsIgnoreCase(ConfigReader.get("env.mode"))) return;
        driver.findElement(confirmBtn).click();
    }
}
