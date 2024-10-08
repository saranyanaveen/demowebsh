package sampleutil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {
    private WebDriver driver;

    public WebDriver setUpDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless"); // Uncomment for headless mode
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public void login(WebDriver driver, String username, String password) {
        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.id("Email")).sendKeys(username);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed(), "Login failed for user: " + username);
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
