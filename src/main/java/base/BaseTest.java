package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {
    protected String baseUrl = ConfigReader.get("base.url");

    @BeforeMethod(alwaysRun = true, groups = {"web"})
    public void setUpWeb() {
        WebDriverFactory.init();
        WebDriverFactory.get().get(baseUrl);
    }

    @AfterMethod(alwaysRun = true, groups = {"web"})
    public void tearDownWeb() {
        WebDriverFactory.quit();
    }
}
