package Intro;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import fileutility.Dataproviders;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoLog extends Dataproviders {
    @Test(dataProvider = "LoginData", dataProviderClass = Dataproviders.class)
    public void login(String scenario, String username, String password) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        try {
            driver.manage().window().maximize();
            driver.get("https://demowebshop.tricentis.com/");
            
            driver.findElement(By.xpath("//a[@href='/login']")).click();
            driver.findElement(By.id("Email")).sendKeys(username);
            driver.findElement(By.id("Password")).sendKeys(password);
            driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
            
            Reporter.log("Testing scenario: " + scenario, true);
            
            if (scenario.equals("bothcorrect")) {
                WebElement account = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account")));
                Assert.assertTrue(account.isDisplayed(), "Login successful: Account element is not displayed.");
                Reporter.log("Login successful: Account element is displayed.", true);
            } else {
                WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='message-error']")));
                String errorMessage = errorMessageElement.getText();
                Reporter.log("Actual error message: " + errorMessage, true);
                Assert.assertTrue(errorMessage.contains("The credentials provided are incorrect"), "Error message does not match expected text.");
                Reporter.log("Login failed: " + errorMessage, true);
            }
        } catch (Exception e) {
            Reporter.log("Test failed due to exception: " + e.getMessage(), true);
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
