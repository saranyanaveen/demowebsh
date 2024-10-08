package Intro;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.remote.RemoteWebDriver;

	public class ChromeVersion {
	    public static void main(String[] args) {
			/*
			 * // Set the path to the ChromeDriver executable (if needed)
			 * System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
			 */
	        // Create ChromeOptions instance
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--headless"); // Ensure the browser runs in headless mode

	        // Initialize WebDriver
	        WebDriver driver = new ChromeDriver(options);

	        // Navigate to a page
	        driver.get("");

	        // Get ChromeDriver version via WebDriver command (works in some WebDriver implementations)
	        String chromeDriverVersion = (String) ((RemoteWebDriver) driver).getCapabilities().getBrowserVersion();
	        System.out.println("ChromeDriver Version: " + chromeDriverVersion);

	        // Clean up
	        driver.quit();
	    }
	}



