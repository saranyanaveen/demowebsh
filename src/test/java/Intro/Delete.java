package Intro;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;

import basepack.Ecommerce;

public class Delete extends Ecommerce {
	@Test(priority = 2)
	public void deletecart() {
		driver.findElement(By.id("nav-cart")).click();
		driver.findElement(By.xpath("//input[@aria-label='Delete My Journey (Tamil)']")).click();
		Reporter.log("Successfully delete from the cart",true);
		
		
	}

}
