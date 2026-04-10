package Appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URI;
import java.net.URL;

public class Activity3 {

    AndroidDriver driver;

    @BeforeClass
    public void setUp() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");

        // ✅ Use default Google Calculator app
        options.setAppPackage("com.google.android.calculator");
        options.setAppActivity("com.android.calculator2.Calculator");

        URL serverURL = new URI("http://127.0.0.1:4723").toURL();

        driver = new AndroidDriver(serverURL, options);
    }

    @Test
    public void additionTest() {

        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.accessibilityId("plus")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_9")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();

        String result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText();

        System.out.println("Addition Result: " + result);
        Assert.assertEquals(result, "14");
    }

    @Test
    public void subtractTest() {

        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_1")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_0")).click();
        driver.findElement(AppiumBy.accessibilityId("minus")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();

        String result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText();

        System.out.println("Subtraction Result: " + result);
        Assert.assertEquals(result, "5");
    }

    @Test
    public void multiplyTest() {

        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.accessibilityId("multiply")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_1")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_0")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_0")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();

        String result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText();

        System.out.println("Multiply Result: " + result);
        Assert.assertEquals(result, "500");
    }

    @Test
    public void divideTest() {

        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_0")).click();
        driver.findElement(AppiumBy.accessibilityId("divide")).click();
        driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_2")).click();
        driver.findElement(AppiumBy.accessibilityId("equals")).click();

        String result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText();

        System.out.println("Divide Result: " + result);
        Assert.assertEquals(result, "25");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}