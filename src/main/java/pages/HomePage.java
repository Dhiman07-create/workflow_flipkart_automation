package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class HomePage {
    WebDriver driver;

    @FindBy(name = "q") WebElement searchBox;
    @FindBy(css = "button[type='submit']") WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String product) {
        searchBox.sendKeys(product);
        searchButton.click();
    }

    public boolean isSearchResultDisplayed() {
        return driver.getPageSource().contains("Showing");
    }
}
