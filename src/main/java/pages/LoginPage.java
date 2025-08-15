package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class LoginPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@class='_2IX_2- VJZDxU']") WebElement usernameInput;
    @FindBy(xpath = "//input[@type='password']") WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']") WebElement loginButton;
    @FindBy(xpath = "//button[text()='✕']") WebElement closeLoginPopup;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void closeLoginPopupIfPresent() {
        try {
            closeLoginPopup.click();
        } catch (Exception e) {
            // popup not present
        }
    }

    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
