package sampleutil;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration; // Import Duration

public class DymanicControl {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void disableCheckbox() {
        driver.get("http://the-internet.herokuapp.com/dynamic_controls");

        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkbox.click();
        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));
        removeButton.click(); // Click "Remove" button

       

        removeButton.click(); // Click "Remove" button

        // Wait for checkbox to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(checkbox));
        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "It's gone!", "Message is not correct.");
    }
        

@Test
public void enableCheckbox() {
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
        addButton.click(); // Click "Add" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for checkbox to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkbox")));
        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        
        // Re-find the checkbox element after it has been re-added to the DOM
        checkbox = driver.findElement(By.id("checkbox"));
        WebElement message = driver.findElement(By.id("message"));

        Assert.assertTrue(checkbox.isDisplayed(), "Checkbox should be visible.");
        Assert.assertEquals(message.getText(), "It's back!", "Message is not correct.");
    }

    

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
