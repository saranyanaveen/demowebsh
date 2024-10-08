package Intro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import fileutility.Dataproviders;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Demoweb extends Dataproviders {
    @Test(dataProvider = "LoginData", dataProviderClass = Dataproviders.class)
    public void login(String username, String password) {
        // Set up ChromeDriver with WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.manage().window().maximize();
            driver.get("https://demowebshop.tricentis.com/");
            
            driver.findElement(By.xpath("//a[@href='/login']")).click();
            driver.findElement(By.id("Email")).sendKeys(username);
            driver.findElement(By.id("Password")).sendKeys(password);
            driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
            
            // Check if logout button is displayed
            WebElement logoutButton = driver.findElement(By.linkText("Log out"));
            if (logoutButton.isDisplayed()) {
                System.out.println("Login success for user: " + username);
                //Assert.assertTrue(true); // Optionally ensure the test passes
            } else {
                // If logout button is not displayed, check for error message
                try {
                    WebElement errorMessage = driver.findElement(By.className("message-error"));
                    String errorText = errorMessage.getText();
                    System.out.println("Login failed for user: " + username);
                    System.out.println("Error message: " + errorText);
                    Assert.fail("Error: Login failed with message: " + errorText);
                } catch (Exception ex) {
                    System.out.println("Login failed for user: " + username);
                    System.out.println("Error message element not found.");
                    Assert.fail("Error: Login failed, but no error message found.");
                }
            }
        } catch (Exception e) {
            // Print error message if there's an exception
            System.out.println("An error occurred during login for user: " + username);
            e.printStackTrace();
            Assert.fail("Error during login: " + e.getMessage()); // Fail the test if there's an exception
        } finally {
            driver.quit(); // Ensure the driver is quit even if there is an error
        }
    }
}
