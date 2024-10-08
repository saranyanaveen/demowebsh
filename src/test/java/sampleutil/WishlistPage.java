package sampleutil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishlistPage {

    @Test(dataProvider = "userWishlistData", dataProviderClass = Dataprovider.class)
    public void addToWishlist(String username, String password, String searchproduct, String productLinkText) {
        LoginPage loginPage = new LoginPage();
        WebDriver driver = loginPage.setUpDriver();
        loginPage.login(driver, username, password);

        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.name("q")).sendKeys(searchproduct);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        driver.findElement(By.linkText(productLinkText)).click();
        driver.findElement(By.id("As")).click();
        driver.findElement(By.id("Sid")).click();
        driver.findElement(By.xpath("//input[@class='button-1 search-button']")).click();
        driver.findElement(By.xpath("//div[@data-productid='28']/div[2]/h2/a")).click();
        driver.findElement(By.id("add-to-wishlist-button-28")).click();
        driver.findElement(By.linkText("Wishlist")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'" + productLinkText + "')]")).isDisplayed(), "Item not added to wishlist");

        loginPage.tearDown();
    }
}
