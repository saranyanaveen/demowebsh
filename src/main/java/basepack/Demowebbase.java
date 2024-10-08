package basepack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class Demowebbase {
	public WebDriver driver;

	@BeforeClass
	public void openBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demowebshop.tricentis.com/");
		Reporter.log("openBrowser done", true);
	}
	@BeforeMethod
	public void login() {
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		driver.findElement(By.name("Email")).sendKeys("saranaveen1997@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("Saranya@1997");
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		Reporter.log("Login done", true);
	}
	@AfterMethod(alwaysRun=true)
    public void logout() {
    	driver.findElement(By.xpath("//a[text()='Log out']")).click();
    }
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}

	


