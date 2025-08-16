package pages.web;

import base.BasePage;
import base.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsWeb extends BasePage {
    private final By productTitles = By.cssSelector("a[href*='/p/'] div[class*='yKfJKb'] , a[href*='/p/'] .KzDlHZ"); // resilient for titles
    private final By firstProduct = By.cssSelector("a[href*='/p/']");

    public SearchResultsWeb() { super(WebDriverFactory.get()); }

    public ProductPageWeb openFirstProduct() {
        wait.visible(firstProduct).click();
        // Switch to new tab if it opened there
        var handles = driver.getWindowHandles().toArray();
        driver.switchTo().window(handles[handles.length-1].toString());
        return new ProductPageWeb();
    }

    public boolean hasResults() {
        List<WebElement> list = driver.findElements(productTitles);
        return !list.isEmpty();
    }
}
