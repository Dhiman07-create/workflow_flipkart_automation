package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ConfigReader;

import java.net.URL;
import java.time.Duration;

public class MobileDriverFactory {
    private static final ThreadLocal<AndroidDriver> TL = new ThreadLocal<>();

    public static void initAndroid() {
        try {
            UiAutomator2Options opts = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setDeviceName(ConfigReader.get("android.deviceName"))
                    .setPlatformVersion(ConfigReader.get("android.platformVersion"))
                    .setAppPackage(ConfigReader.get("android.appPackage"))
                    .setAppActivity(ConfigReader.get("android.appActivity"))
                    .setNoReset(true)
                    .setAutoGrantPermissions(true);
            AndroidDriver driver = new AndroidDriver(new URL(ConfigReader.get("appium.server")), opts);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            TL.set(driver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to start AndroidDriver", e);
        }
    }
    public static AndroidDriver get() { return TL.get(); }
    public static void quit() {
        if (TL.get() != null) { TL.get().quit(); TL.remove(); }
    }
}
