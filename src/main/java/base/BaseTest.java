package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
