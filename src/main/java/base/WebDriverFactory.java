package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import utils.ConfigReader;

import java.nio.file.Files;
import java.time.Duration;

public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // ✅ Headless if enabled in config
        if (ConfigReader.getBool("headless")) {
            options.addArguments("--headless=new"); // modern headless
        }

        // ✅ Stable Chrome args for CI/CD
        options.addArguments(
                "--start-maximized",
                "--disable-notifications",
                "--disable-dev-shm-usage",   // fixes crashes in CI
                "--no-sandbox",              // required in GitHub Actions Linux
                "--remote-allow-origins=*"
        );

        try {
            // ✅ Assign a unique temp user-data-dir to avoid conflicts
            String tmpProfileDir = Files.createTempDirectory("chrome-profile").toString();
            options.addArguments("--user-data-dir=" + tmpProfileDir);
        } catch (Exception e) {
            System.err.println("Could not create temp user-data-dir for Chrome: " + e.getMessage());
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait")));
        TL.set(driver);
    }

    public static WebDriver get() {
        return TL.get();
    }

    public static void quit() {
        if (TL.get() != null) {
            TL.get().quit();
            TL.remove();
        }
    }
}
