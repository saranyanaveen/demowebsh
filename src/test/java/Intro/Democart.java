package Intro;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Democart{
    public static void main(String[] args) {
        // Set path to ChromeDriver executable
       

        // Set ChromeOptions to run in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);

        // Your test code here
        driver.get("https://www.amazon.com");
        // Add code to interact with the page, e.g., add to cart
        String title = driver.getTitle();

        // Print the title
        System.out.println("Page title is: " + title);
        // Close the browser
        
        driver.quit();
    }
}


   