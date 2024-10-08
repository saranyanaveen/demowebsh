package uploadfile;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class RecruitmentModuleTest extends LoginHrms {

	/*
	 * private WebDriver driver; private WebDriverWait wait;
	 * 
	 * @BeforeClass public void setUp() { WebDriverManager.chromedriver().setup();
	 * driver = new ChromeDriver(); driver.manage().window().maximize(); wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(30)); }
	 */

	@Test(priority = 2, dependsOnMethods = "adminLogin")
	public void verifyApplicationInRecruitmentModule() throws Throwable {
		// test.log(Status.INFO, "Navigating to Recruitment Module");

		// Wait and click the full menu
		WebElement fullMenuLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='full']")));
		fullMenuLink.click();

		// Wait and click on the recruitment module
		WebElement recruitmentModuleLink = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#menu_id=680']")));
		recruitmentModuleLink.click();

		// Wait and check job position is listed
		WebElement jobposition = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[contains(.,'Project Manager / Service Delivery Manager')]")));

		String expectedMessage = "Project Manager / Service Delivery Manager";
		String actualMessage = jobposition.getText().trim(); // Use trim() to remove any extra spaces.

		// Assert if the confirmation message is displayed correctly
		Assert.assertEquals(actualMessage, expectedMessage, "The job position does not match the expected message!");

		System.out.println("Job viewed successfully: " + actualMessage);
		// test.log(Status.PASS, "Job Viewed successfully");

		// Wait and click on Applications button
		WebElement applicationButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div[3]/div/div/button")));
		applicationButton.click();
		/*
		 * WebElement initialQualification
		 * =driver.findElement(By.xpath("//span[contains(.,'Initial Qualification')]"));
		 * 
		 * 
		 * if (initialQualification.isDisplayed()) {
		 * System.out.println("Initial Qualification stage is present on the page."); }
		 * else { System.out.println("Initial Qualification stage is not present.");
		 * return; }
		 */

		// Wait and find candidate applications in the list
		List<WebElement> candidateApplications = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.cssSelector(".oe_kanban_color_0:nth-child(2) .o_kanban_record_top span")));

		// Check if the list is not empty and click the first element
		if (!candidateApplications.isEmpty()) {
			System.out.println("Candidate applications found!");

			// Optionally, assert that at least one application exists
			Assert.assertTrue(candidateApplications.size() > 0, "No candidate applications found!");

			 candidateApplications.get(0).click(); // Click the first element in the list
			WebElement sourceApplication = candidateApplications.get(0);
			sourceApplication.click();// Optional: Click to open details, if needed
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[3]/div")).click();
			driver.findElement(By.xpath("//button[@type='button'][15]")).click();
			System.out.println("Application Move to screening stage");
		} else {
			System.out.println("No applications found!");
			Assert.fail("Expected at least one candidate application to be present, but none were found.");
		}
	}
	
	/*
	 * @Test(priority = 3, dependsOnMethods =
	 * "verifyApplicationInRecruitmentModule") public void dragAndDrop() { try { //
	 * Locate the application (draggable element) WebElement fromElement =
	 * driver.findElement(By.xpath("//div[@class='oe_kanban_bottom_left']"));
	 * //System.out.println("Located the element to be dragged.");
	 * 
	 * // Locate the next screening round (droppable element) WebElement Element =
	 * driver.findElement(By.xpath("//span[@id='#kanban_header_2']")); WebElement
	 * toElement=driver.findElement(By.xpath("//div[3]/div")); //
	 * System.out.println("Located the drop target.");
	 * 
	 * // Use clickAndHold, moveToElement, and release instead of dragAndDrop
	 * 
	 * Actions actions = new Actions(driver); actions.clickAndHold(fromElement)
	 * .moveToElement(toElement) .pause(Duration.ofSeconds(2)) // Optional pause to
	 * visualize the action .release() .build() .perform();
	 * 
	 * // Perform drag and drop using JavaScriptExecutor
	 * 
	 * JavascriptExecutor js = (JavascriptExecutor) driver; String script =
	 * "function createEvent(typeOfEvent) { " +
	 * "var event = document.createEvent('CustomEvent'); " +
	 * "event.initCustomEvent(typeOfEvent, true, true, null); " +
	 * "event.dataTransfer = { " + "data: {}, " +
	 * "setData: function(key, value) { this.data[key] = value; }, " +
	 * "getData: function(key) { return this.data[key]; } " + "}; return event; } "
	 * + "function dispatchEvent(element, event, transferData) { " +
	 * "if (transferData !== undefined) { event.dataTransfer = transferData; } " +
	 * "if (element.dispatchEvent) { element.dispatchEvent(event); } " +
	 * "else if (element.fireEvent) { element.fireEvent('on' + event.type, event); } } "
	 * + "function simulateHTML5DragAndDrop(element, destination) { " +
	 * "var dragStartEvent = createEvent('dragstart'); dispatchEvent(element, dragStartEvent); "
	 * +
	 * "var dropEvent = createEvent('drop'); dispatchEvent(destination, dropEvent, dragStartEvent.dataTransfer); "
	 * +
	 * "var dragEndEvent = createEvent('dragend'); dispatchEvent(element, dragEndEvent, dragStartEvent.dataTransfer); } "
	 * + "simulateHTML5DragAndDrop(arguments[0], arguments[1]);";
	 * 
	 * // Execute the script // js.executeScript(script, fromElement, toElement);
	 * 
	 * // Optional: Add assertion to check the result after drag-and-drop // For
	 * example, you can check if the item is now in the new column/stage //
	 * System.out.println("Drag and Drop action performed using JavaScriptExecutor"
	 * ); try { Thread.sleep(2000); // Add delay to observe the action and let the
	 * page update } catch (InterruptedException e) { e.printStackTrace(); }
	 * 
	 * // Check if the drag-and-drop was successful by verifying the application's
	 * new stage WebElement movedApplication =
	 * driver.findElement(By.xpath("//span[@id='#kanban_header_2']"));
	 * Assert.assertTrue(movedApplication.isDisplayed(),
	 * "The application was not moved to the Screening Stage.");
	 * 
	 * // Optional: Add print statement to confirm the action System.out.
	 * println("Drag and Drop action performed and validated successfully."); }
	 * 
	 * 
	 * 
	 * // Console log for successful drag and drop //
	 * System.out.println("Drag and drop action performed successfully.");
	 * 
	 * catch (Exception e) { System.out.println("Drag and drop action failed: " +
	 * e.getMessage()); Assert.fail("Drag and drop functionality failed!"); }
	 */
	

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
