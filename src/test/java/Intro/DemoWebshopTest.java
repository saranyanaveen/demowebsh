package Intro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;

public class DemoWebshopTest {
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;

    public static void main(String[] args) {
        // Setup ExtentReports with Spark
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("sparkReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Initialize WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        try {
            test = extent.createTest("DemoWebshop Test", "Verify the Demo Webshop functionality");

            // Navigate to Demo Webshop
            driver.get("http://demowebshop.tricentis.com/");
            test.info("Navigated to Demo Webshop");

            // Perform actions (example: click on 'Log in')
            driver.findElement(By.linkText("Log in")).click();
            test.info("Clicked on Log in link");

            // Validate page title
            String title = driver.getTitle();
            if (!title.equals("Your store. Login")) {
                test.fail("Title does not match! Expected: 'Your store. Login', Found: '" + title + "'");
                takeScreenshot("LoginPageTitleMismatch");
            } else {
                test.pass("Title matches successfully: " + title);
            }
        } catch (Exception e) {
            test.error("Exception occurred: " + e.getMessage());
            takeScreenshot("ExceptionOccurred");
        } finally {
            // Close the browser
            driver.quit();
            extent.flush();
        }
    }

    private static void takeScreenshot(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshots/" + fileName + ".png");
        try {
            com.google.common.io.Files.copy(srcFile, destFile);
            test.info("Screenshot captured: " + destFile.getAbsolutePath(),
                    MediaEntityBuilder.createScreenCaptureFromPath(destFile.getAbsolutePath()).build());
        } catch (IOException e) {
            test.error("Failed to capture screenshot: " + e.getMessage());
        }
    }
}

	