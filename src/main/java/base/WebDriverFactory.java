package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import utils.ConfigReader;

import java.io.File;
import java.nio.file.Files;
import java.time.Duration;

public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> TL = new ThreadLocal<>();

    public static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        File headlessShell = null;

// Check for common binaries
        String[] candidates = {
                "/usr/bin/chrome-headless-shell",
                "/usr/bin/chromium-browser",
                "/snap/bin/chromium"
        };

        for (String path : candidates) {
            File bin = new File(path);
            if (bin.exists()) {
                headlessShell = bin;
                break;
            }
        }

        if (headlessShell != null) {
            options.setBinary(headlessShell);
            System.out.println("✅ Using binary: " + headlessShell.getAbsolutePath());
        } else {
            System.out.println("⚠️ Falling back to normal Chrome");
        }

        options.addArguments("--headless=chrome");
        String tmpProfile = "/tmp/chrome-profile-" + System.currentTimeMillis();
        options.addArguments("--user-data-dir=" + tmpProfile);
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getInt("implicit.wait")));
        TL.set(driver);
        System.out.println("Launching Chrome with options: " + options.toString());

    }
    public static WebDriver get() { return TL.get(); }
    public static void quit() {
        if (TL.get() != null) { TL.get().quit(); TL.remove(); }
    }
}
