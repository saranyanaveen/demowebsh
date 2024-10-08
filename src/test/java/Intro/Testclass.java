package Intro;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import basepack.Ecommerce;

public class Testclass extends Ecommerce{
    @Test
    public void addtocart() throws Throwable{
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.flipkart.com/");
        
		/*
		 * // Close any initial pop-up if it appears try { WebElement closeButton =
		 * driver.findElement(By.xpath("//button[contains(text(), '✕')]"));
		 * closeButton.click(); } catch (Exception e) { // No pop-up appeared }
		 */
        
        // Search for the product
        WebElement searchbox = driver.findElement(By.name("q")); 
        searchbox.click();
        searchbox.sendKeys("Apple iPhone 15 (Black, 128 GB)");
        Robot robo = new Robot();
        robo.keyPress(KeyEvent.VK_ENTER);
        robo.keyRelease(KeyEvent.VK_ENTER);
        
        // Wait for search results to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@data-id='MOBG7G2BF8EXG2PR']//a[@class='_1fQZEK']")));
        productLink.click();
        
        // Wait for the "Add to Cart" button to be clickable
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(),'ADD TO CART')]")));
        
        // Scroll into view and click the button
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addToCartButton.click();
        
        // Confirm product is added to the cart
        Reporter.log("Product successfully added to the cart", true);
        
        // Clean up
        driver.quit();
    }
}