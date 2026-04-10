package Appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;

public class Activity1 {

    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");

        // ✅ FIXED (Works for most emulators - Google Calculator)
        options.setAppPackage("com.google.android.calculator");
        options.setAppActivity("com.android.calculator2.Calculator");

        options.noReset();

        URL url = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(url, options);
    }

    @Test
    public void multiplyTest() {

        // 5
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();

        // *
        driver.findElement(AppiumBy.accessibilityId("multiply")).click();

        // 8
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_8")).click();

        // =
        driver.findElement(AppiumBy.accessibilityId("equals")).click();

        // Result
        String result = driver.findElement(
                AppiumBy.id("com.google.android.calculator:id/result_final")).getText();

        System.out.println("Result: " + result);

        Assert.assertTrue(result.contains("40"));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}