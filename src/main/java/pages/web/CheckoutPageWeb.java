package pages.web;

import base.BasePage;
import base.WebDriverFactory;
import org.openqa.selenium.By;
import utils.ConfigReader;

public class CheckoutPageWeb extends BasePage {

    private final By loginPhoneField = By.cssSelector("input[type='text'][maxlength='10'], input[type='text'][maxlength='50']");
    private final By requestOtpBtn   = By.xpath("//button[contains(.,'Request OTP') or contains(.,'CONTINUE')]");
    private final By deliverHereBtn  = By.xpath("//button[contains(.,'Deliver Here') or contains(.,'DELIVER HERE')]");
    private final By continueBtn     = By.xpath("//button[contains(.,'CONTINUE')]");
    private final By codOption       = By.xpath("//div[contains(.,'Cash on Delivery') or contains(.,'COD')]");
    private final By payBtn          = By.xpath("//button[contains(.,'Pay') or contains(.,'CONFIRM ORDER')]");

    public CheckoutPageWeb() { super(WebDriverFactory.get()); }

    public CheckoutPageWeb ensureLoggedInOrGuest() {
        // Flipkart may auto-login from cookie. If login step appears, fill it.
        try {
            if (driver.findElements(loginPhoneField).size() > 0) {
                wait.visible(loginPhoneField).sendKeys(ConfigReader.get("fk.username"));
                wait.safeClick(requestOtpBtn);
                // Pause here for manual OTP in prod OR add OTP retrieval hook in sandbox
            }
        } catch (Exception ignored) {}
        return this;
    }

    public CheckoutPageWeb chooseDefaultAddress() {
        try { wait.safeClick(deliverHereBtn); } catch (Exception ignored) {}
        try { wait.safeClick(continueBtn); } catch (Exception ignored) {}
        return this;
    }

    public void chooseCODAndStopIfProd() {
        wait.safeClick(codOption);
        if ("prod".equalsIgnoreCase(ConfigReader.get("env.mode"))) {
            // safety stop — do not confirm order on real prod
            return;
        }
        wait.safeClick(payBtn);
    }
}
