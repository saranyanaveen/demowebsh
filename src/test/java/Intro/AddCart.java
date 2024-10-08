package Intro;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AddCart {
	@Test(dataProvider = "userAdddata", dataProviderClass = Datapro.class)
	public void addToCart(String username, String password, String searchproduct, String productLinkText) {
	    Log loginPage = new Log();
	    WebDriver driver = loginPage.setUpDriver();
	    try {
	        loginPage.login(driver, username, password);

	        driver.get("https://www.amazon.in/");
	        WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
	        search.sendKeys(searchproduct);
	        search.sendKeys(Keys.RETURN);
	        
	        // Wait for search results to load
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productLinkText)));
	        productLink.click();
	        
	        // Wait for the Add to Cart button and click it
	        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
	        addToCartButton.click();

	        Reporter.log("Product added to cart successfully!", true);
	    } catch (Exception e) {
	        Reporter.log("Error adding product to cart: " + e.getMessage(), true);
	    } finally {
	        loginPage.tearDown();
	    }
	}}



