package pages.web;

import base.BasePage;
import base.WebDriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import utils.ConfigReader;

public class HomePageWeb extends BasePage {
    private final By closeLoginModalBtn = By.cssSelector("button._2KpZ6l._2doB4z, button._2KpZ6l._2doB4z._3AWRsL"); // common dismiss
    private final By searchBox = By.name("q");
    private final By searchSubmit = By.cssSelector("button[type='submit']");

    public HomePageWeb() {
        super(WebDriverFactory.get());
        dismissLoginPopupIfVisible();
    }

    public void dismissLoginPopupIfVisible() {
        try { driver.findElement(closeLoginModalBtn).click(); } catch (NoSuchElementException ignored) {}
    }

    public HomePageWeb open() {
        driver.get(ConfigReader.get("base.url"));
        dismissLoginPopupIfVisible();
        return this;
    }

    public SearchResultsWeb search(String query) {
        wait.visible(searchBox).clear();
        driver.findElement(searchBox).sendKeys(query);
        wait.safeClick(searchSubmit);
        return new SearchResultsWeb();
    }
}
