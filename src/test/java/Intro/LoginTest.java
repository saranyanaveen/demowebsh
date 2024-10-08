package Intro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import fileutility.Dataproviders;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set up ChromeDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "LoginData", dataProviderClass = Dataproviders.class)
    public void testLogin(String username, String password, String expectedResult) {
        // Open demo webshop login page
        driver.get("https://demowebshop.tricentis.com/login");

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        WebElement passwordField = driver.findElement(By.id("Password"));
        WebElement loginButton = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));

        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();

        //  if login was successful or if an error message is displayed
        boolean isLoggedIn = !driver.getCurrentUrl().contains("login");
        String actualResult;

        try {
            if (isLoggedIn) {
                // check if login buton is present
                WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Log out")));
                Assert.assertTrue(logoutButton.isDisplayed(), "Logout button should be displayed after successful login.");
                actualResult = "success";
            } else {
                // to check message
                try {
                    WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("message-error")));
                    String errorText = errorMessage.getText();
                    System.out.println("Login failed for user: " + username);
                    System.out.println("Error message: " + errorText);
                    actualResult = "failure";
                } catch (Exception ex) {
                    System.out.println("Login failed for user: " + username);
                    System.out.println("Error message element not found.");
                    actualResult = "failure";
                }
            }

            // Assert the result
            Assert.assertEquals(actualResult, expectedResult, "Test result mismatch for user: " + username);
        } catch (Exception e) {
            // print error message if there is an exception
            System.out.println("An error occurred during login for user: " + username);
            e.printStackTrace();
            Assert.fail("Error during login: " + e.getMessage());
        }
    }
}
