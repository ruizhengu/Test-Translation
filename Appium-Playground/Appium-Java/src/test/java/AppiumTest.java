import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("appPackage", "com.example.toyaut");
        caps.setCapability("appActivity", ".MainActivity");
        caps.setCapability("language", "en");
        caps.setCapability("locale", "US");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        WebElement el = driver.findElement(AppiumBy.androidUIAutomator("text(\"Hello Android!\")"));
        el.click();
    }
}
