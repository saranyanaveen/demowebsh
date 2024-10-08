package uploadfile;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;



public class BaseClass {
	
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;
	
	@BeforeClass
	public void setupExtentReport() {
		ExtentSparkReporter reporter= new ExtentSparkReporter("./reports/TestReports.html");
		extent= new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@BeforeMethod
	public void startTest(Method method) {
		test= extent.createTest(method.getName());
		
	}
	
	@AfterMethod
	
	public void logTestResult(ITestResult result) {
		if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Passed");}
		else if(result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test failed:" + result.getThrowable());
		}
		
		else if(result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test skipped:" + result.getThrowable());
		}
	}
		
	@AfterClass
	
	public void tearDown() {
		extent.flush();
	}
}
