package sampleutil;


	import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.Assert;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class JavaScriptAlert {
	    private WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	    }

	    @Test
	    public void testAlert() {
	        driver.get("http://the-internet.herokuapp.com/javascript_alerts");

	        WebElement alertButton = driver.findElement(By.cssSelector("button[onclick='jsAlert()']"));
	        alertButton.click();

	        Alert alert = driver.switchTo().alert();
	        Assert.assertEquals(alert.getText(), "I am a JS Alert", "Alert text is incorrect.");
	        alert.accept();
	    }

	    @Test
	    public void testConfirmAlert() {
	        driver.get("http://the-internet.herokuapp.com/javascript_alerts");

	        WebElement confirmButton = driver.findElement(By.cssSelector("button[onclick='jsConfirm()']"));
	        confirmButton.click();

	        Alert alert = driver.switchTo().alert();
	        Assert.assertEquals(alert.getText(), "I am a JS Confirm", "Confirm alert text is incorrect.");
	        alert.accept();
	        WebElement result = driver.findElement(By.id("result"));
	        Assert.assertEquals(result.getText(), "You clicked: Ok", "Result text is incorrect.");
	    }

	    @Test
	    public void testPromptAlert() {
	        driver.get("http://the-internet.herokuapp.com/javascript_alerts");

	        WebElement promptButton = driver.findElement(By.cssSelector("button[onclick='jsPrompt()']"));
	        promptButton.click();

	        Alert alert = driver.switchTo().alert();
	        alert.sendKeys("Test");
	        alert.accept();
	        WebElement result = driver.findElement(By.id("result"));
	        Assert.assertEquals(result.getText(), "You entered: Test", "Result text  incorrect.");
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}



