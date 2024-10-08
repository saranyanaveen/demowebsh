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

public class OodoApplicationForm {
    private WebDriver driver;

    @BeforeClass
    @Parameters("browser")
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

        driver.get("https://hrms-dev.kaditinnovations.com/jobs");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    @Test
    public void uploadfiletest() throws Throwable {
    	//test.log(Status.INFO, "Filling out application form");
    	
    	    driver.findElement(By.xpath("//span[text()='Project Manager / Service Delivery Manager']")).click();
    	    driver.findElement(By.xpath("//div[@class='float-right']")).click();
    	    driver.findElement(By.name("partner_name")).sendKeys("saranya");
    	    driver.findElement(By.name("email_from")).sendKeys("saranaveen1997@gmail.com");
    	    driver.findElement(By.name("partner_phone")).sendKeys("9790345209");
    	    driver.findElement(By.name("Location")).sendKeys("Tenkasi");

    	    // Wait for the file input to be visible
    	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    	    WebElement uploadFileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Resume")));

    	    // Check if the element is displayed
    	    if (uploadFileElement.isDisplayed()) {
    	        String filePath = "C:\\Users\\KADIT\\Downloads\\Praveen_Resume.pdf";
    	        uploadFileElement.sendKeys(filePath); // Upload the file
    	        //test.log(Status.INFO, "File uploaded");
    	    } else {
    	        System.out.println("File input is not visible!");
    	    }

    	    // Click on the Submit button
    	    driver.findElement(By.xpath("//a[text()='Submit']")).click();
    	    
    	    wait.until(ExpectedConditions.urlToBe("https://hrms-dev.kaditinnovations.com/job-thank-you")); // Wait for the exact URL

    	    
                WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-center']")));
                
                // Use assertion to verify the confirmation message
                String expectedMessage = "Your application has been posted successfully.";
                String actualMessage = confirmationMessage.getText().trim();//use trim() to remove any extra spaces.
                
                // Assert if the confirmation message is displayed correctly
                Assert.assertEquals(actualMessage, expectedMessage, "The confirmation message does not match the expected message!");

                // Print the message for debugging purposes
                System.out.println("Form submitted successfully: " + actualMessage);
                //test.log(Status.PASS, "Form submitted successfully");
            }
        
    
    @AfterClass
    public void tearDown() {
        driver.quit();
       // test.log(Status.INFO, "Browser closed");
    }
}
