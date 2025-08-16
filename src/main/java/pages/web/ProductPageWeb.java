package pages.web;

import base.BasePage;
import base.WebDriverFactory;
import org.openqa.selenium.By;

public class ProductPageWeb extends BasePage {
    private final By addToCartBtn = By.xpath("//button[contains(.,'Add to cart') or contains(.,'ADD TO CART')]");
    private final By buyNowBtn   = By.xpath("//button[contains(.,'Buy Now') or contains(.,'BUY NOW')]");

    public ProductPageWeb() { super(WebDriverFactory.get()); }

    public CartPageWeb addToCart() {
        wait.safeClick(addToCartBtn);
        return new CartPageWeb();
    }

    public CheckoutPageWeb buyNow() {
        wait.safeClick(buyNowBtn);
        return new CheckoutPageWeb();
    }
}
