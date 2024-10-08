package Intro;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class User1{

	@Test(dataProvider = "userData", dataProviderClass = Datapro.class)
	public void testUser1(String username, String password, String searchProduct) throws InterruptedException {
		Log loginPage = new Log();
		WebDriver driver = loginPage.setUpDriver();

		try {
			loginPage.login(driver, username, password);

			driver.get("https://www.amazon.in/");
			WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
			searchBox.sendKeys(searchProduct);
			searchBox.submit();

			
			  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
				/*
				 * WebElement productLink = wait
				 * .until(ExpectedConditions.elementToBeClickable(By.partialLinkText(
				 * searchProduct))); productLink.click();
				 */
           WebElement productlink=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-size-large product-title-word-break']")));
           productlink.click();
           String originalTab = driver.getWindowHandle();
           for (String tab : driver.getWindowHandles()) {
               if (!tab.equals(originalTab)) {
                   driver.switchTo().window(tab);
                   break;
               }
           }
			WebElement addToCartButton = wait
					.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
			addToCartButton.click();
		}

			
		 finally {
			loginPage.tearDown();
			
		}
	
}
}
