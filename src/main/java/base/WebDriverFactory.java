package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.ConfigReader;

import java.io.File;
import java.nio.file.Files;
import java.time.Duration;

public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static void init() {
        WebDriverManager.edgedriver().setup();

        EdgeOptions options = new EdgeOptions();

        if (ConfigReader.getBool("headless")) {
            options.addArguments("--headless=new");
        }

        // CI friendly flags
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait")));
        TL.set(driver);
    }

    public static WebDriver get() { return TL.get(); }

    public static void quit() {
        if (TL.get() != null) {
            TL.get().quit();
            TL.remove();
        }
    }
}