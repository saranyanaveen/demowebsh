package Intro;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import basepack.Ecommerce;

public class amaztest extends Ecommerce {

	@Test(priority = 1)
	public void addtocart() throws Throwable  {

		// Search for the product
		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		search.click();
		search.sendKeys("kalam Wings of fire books in tamil");
		
		/*
		 * Robot robo = new Robot(); robo.keyPress(KeyEvent.VK_ENTER);
		 * robo.keyRelease(KeyEvent.VK_ENTER);
		 */
		Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.RETURN).perform();
		
		/*
		 * // Wait for the search results to load and click the product WebDriverWait
		 * wait = new WebDriverWait(driver, Duration.ofSeconds(20)); WebElement link =
		 * wait.until(ExpectedConditions.elementToBeClickable(By.
		 * xpath("//span[contains(text(),'My Journey (Tamil)')]"))); link.click();
		 */
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement productlink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'My Journey (Tamil)')]")));
	    productlink.click();

		String originalTab = driver.getWindowHandle();
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

		driver.findElement(By.id("add-to-cart-button")).click();
		Reporter.log("Product added to cart successfully!",true);
		
}
	
	
}

