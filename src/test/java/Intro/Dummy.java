package Intro;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Dummy {
	


		//chrome launch
		//chrome.exe
	      @Test
		  public void LaunchURL(){
		  WebDriver driver=new ChromeDriver(); driver.get("https://www.flipkart.com/");
		  System.out.println(driver.getTitle());//print the page title.
		  System.out.println(driver.getCurrentUrl());//print current url
		  driver.close();
		  }
		 
		//quit entire browser.
		//firefox lauch
		//geckodriver
		//webdriver.gecko.driver
	      @Test
		public void LaunchFFURL() {
		WebDriver driverfox=new FirefoxDriver();
		driverfox.get("https://www.flipkart.com/");
		System.out.println(driverfox.getTitle());//print the page title.
		System.out.println(driverfox.getCurrentUrl());//print current url
		driverfox.close();
		}
		//quit entire browser.
	      @Test
		public void LaunchEURL() {
		WebDriver driveredge=new EdgeDriver();
		driveredge.get("https://www.flipkart.com/");
		System.out.println(driveredge.getTitle());//print the page title.
		System.out.println(driveredge.getCurrentUrl());//print current url
		driveredge.close();
		driveredge.quit();
		//quit entire browser.
		}
	

}
