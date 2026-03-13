package selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class AmazonThirdRow {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.amazon.in");

        // Search iphone 17 pro
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone 17 pro");
        driver.findElement(By.id("nav-search-submit-button")).click();

        // Wait for search results
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@data-component-type='s-search-result']")));

        // Get all results
        List<WebElement> results = driver.findElements(
                By.xpath("//div[@data-component-type='s-search-result']"));

        // Get the 3rd product
        WebElement thirdProduct = results.get(2);

        // Extract price
        String price = "Price not available";
        try {
            price = thirdProduct.findElement(By.xpath(".//span[@class='a-price-whole']")).getText();
        } catch (Exception e) {
            System.out.println("Price not found");
        }

        // Extract delivery info
        String delivery = "Delivery info not available";
        try {
            delivery = thirdProduct.findElement(By.xpath(".//span[contains(text(),'delivery')]")).getText();
        } catch (Exception e) {
            System.out.println("Delivery info not found");
        }

        // Print output
        System.out.println("3rd Product Price: " + price);
        System.out.println("Delivery Info: " + delivery);

        driver.quit();
    }
}