package Intro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Log {
	 public WebDriver driver;

	    public WebDriver setUpDriver() {
	        WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        //options.addArguments("--headless"); // Uncomment for headless mode
	        driver = new ChromeDriver(options);
	        driver.manage().window().maximize();
			return driver;
	        
	    }

	    public void login(WebDriver driver, String username, String password) throws InterruptedException {
	        driver.get("https://www.amazon.in/");
	        driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
	        driver.findElement(By.name("email")).sendKeys(username);
	        driver.findElement(By.id("continue")).click();
	        driver.findElement(By.id("ap_password")).sendKeys(password);
	        driver.findElement(By.xpath("//input[@class='a-button-input']")).click();
	        Thread.sleep(1000);
	       // Assert.assertTrue(driver.findElement(By.linkText("Log out")).isDisplayed(), "Login failed for user: " + username);
	    }

	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}



