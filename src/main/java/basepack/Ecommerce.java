package basepack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import fileutility.Readproperties;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Ecommerce {
	public WebDriver driver;

	@BeforeClass()
	public void openBrowser() throws Throwable {
		 // Set up ChromeDriver with WebDriverManager
	      WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless"); // Run in headless mode
		/*
		 * options.addArguments("--disable-gpu"); // Disable GPU acceleration //
		 * options.addArguments("--no-sandbox"); // Disable sand boxing for running in
		 * CI environments
		 */
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// driver.get("https://www.amazon.in/");
		driver.get(Readproperties.getUrl());

		Reporter.log("openBrowser done", true);
	}

	@BeforeMethod()
	public void login() throws Throwable {
		driver.findElement(By.id("nav-link-accountList-nav-line-1")).click();
		// driver.findElement(By.name("email")).sendKeys("suresh14octo@gmail.com");
		driver.findElement(By.name("email")).sendKeys(Readproperties.getusername());
		driver.findElement(By.id("continue")).click();
		// driver.findElement(By.id("ap_password")).sendKeys("Pavithra@2321");
		driver.findElement(By.id("ap_password")).sendKeys(Readproperties.getpassword());
		driver.findElement(By.xpath("//input[@class='a-button-input']")).click();
		Thread.sleep(1000);
		Reporter.log("Login done", true);

	}

	@AfterMethod()
	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		// To handle mouse.
		Actions act = new Actions(driver);
		WebElement mainmenu = driver.findElement(By.id("nav-link-accountList"));
		act.moveToElement(mainmenu).perform();
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement signout = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Sign Out')]")));
		signout.click();
		Reporter.log("Logout done", true);
	}

	@AfterClass()
	public void closeBrowser() {
		driver.quit();
		Reporter.log("Successfully close the browser", true);

	}

}
