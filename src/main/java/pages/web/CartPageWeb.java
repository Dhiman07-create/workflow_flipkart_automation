package pages.web;

import base.BasePage;
import base.WebDriverFactory;
import org.openqa.selenium.By;

public class CartPageWeb extends BasePage {
    private final By placeOrderBtn = By.xpath("//span[text()='Place Order' or text()='PLACE ORDER']/ancestor::button");

    public CartPageWeb() { super(WebDriverFactory.get()); }

    public CheckoutPageWeb placeOrder() {
        wait.safeClick(placeOrderBtn);
        return new CheckoutPageWeb();
    }
}
