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
        if (ConfigReader.getBool("headless")) options.addArguments("--headless=new");
        options.addArguments("--start-maximized", "--disable-notifications", "--remote-allow-origins=*");

        try {
            // ✅ Unique profile directory
            String tmpProfileDir = Files.createTempDirectory("chrome-profile").toString();
            options.addArguments("--user-data-dir=" + tmpProfileDir);

            // ✅ Unique debugging port (prevents conflicts across sessions)
            int randomPort = 9222 + (int)(Math.random() * 1000);
            options.addArguments("--remote-debugging-port=" + randomPort);

        } catch (Exception e) {
            System.err.println("Failed to configure unique Chrome profile: " + e.getMessage());
        }

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait")));
        TL.set(driver);
    }
    public static WebDriver get() { return TL.get(); }
    public static void quit() {
        if (TL.get() != null) { TL.get().quit(); TL.remove(); }
    }
}
