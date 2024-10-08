package uploadfile;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginHrms  {
	public WebDriver driver;
	public WebDriverWait wait;

    @BeforeClass
    @Parameters("browser") // Add this to receive browser name
    public void setUp(String browser) {
    	//test.log(Status.INFO, "Setting up browser:" + browser);
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } 
		/*
		 * else if (browser.equalsIgnoreCase("edge")) {
		 * WebDriverManager.edgedriver().setup(); driver = new EdgeDriver(); }
		 */
        driver.manage().window().maximize();
        driver.get("https://hrms-dev.kaditinnovations.com");
    }
    
    @Test(dependsOnMethods = "uploadfiletest", priority = 1)
    public void adminLogin() {
    	login("saraswathi.m@kaditinnovations.com", "Saraswathi@1997");
		  wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		  try {
		  WebElement verifylogin =
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.
		  xpath("//div[contains(text(),'Congratulations, your inbox is empty')]")));
		  String actualMessage = verifylogin.getText().trim(); // Get the full message
		  
		  // Assert if the confirmation message contains "Congratulations"
		  Assert.assertTrue(actualMessage.contains("Congratulations"),
		  "The confirmation message does not contain 'Congratulations'!");
		  
		  // Print the message for debugging purposes
		  System.out.println("Login done successfully: " + actualMessage);
		 // test.log(Status.PASS, "Login Successful");
		  
		  } catch (Exception e) { 
			  
			  Assert.fail("Login test failed!"); 
			  //test.log(Status.FAIL, "Login Failed:"+ e.getMessage());
		  }
		 
		 
    }
    
    
    public void login(String email, String password) {
    	 //test.log(Status.INFO, "Starting login Test");
    	 driver.findElement(By.xpath("//a[text()='LOGIN']")).click();
	        driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(email);
	        driver.findElement(By.name("password")).sendKeys(password);
	        driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
	}

	@AfterClass
    public void tearDown() {
        driver.quit();
    }

}
