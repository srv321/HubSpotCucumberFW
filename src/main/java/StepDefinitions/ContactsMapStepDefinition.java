package StepDefinitions;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class ContactsMapStepDefinition {

	WebDriver driver;
	WebDriverWait wait;

	@Given("^user alrdy on Login Page$")
	public void user_alrdy_on_Login_Page() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Selenium Live Training-Naveen\\drivers\\chromedriver79.0.3945.36.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://app.hubspot.com/login");
		wait = new WebDriverWait(driver, 10);

	}

	@When("^title login page HubSpot Login$")
	public void title_login_page_HubSpot_Login() {
		wait.until(ExpectedConditions.titleContains("HubSpot Login"));
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("HubSpot Login", title);

	}

	@Then("^user fills username and passwrd$")
	public void user_fills_username_and_passwrd(DataTable credentials) {
		for (Map<String, String> data : credentials.asMaps(String.class, String.class)) {

			driver.findElement(By.id("username")).sendKeys(data.get("username"));
			driver.findElement(By.id("password")).sendKeys(data.get("password"));

		}
	}

	@Then("^user does click login buttn$")
	public void user_does_click_login_buttn() {
		driver.findElement(By.id("loginBtn")).click();
	}

	@Then("^user is locatd on home page$")
	public void user_is_locatd_on_home_page() {
		wait.until(ExpectedConditions.titleContains("Reports dashboard"));
		String title = driver.getTitle();
		Assert.assertEquals(title, "Reports dashboard");
	}

	@Then("^user fills contct details$")
	public void user_fills_contct_details(DataTable contactsData) throws InterruptedException {
		for (Map<String, String> contactValues : contactsData.asMaps(String.class, String.class)) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-primary-contacts-branch")));
			driver.findElement(By.id("nav-primary-contacts-branch")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-secondary-contacts")));
			driver.findElement(By.id("nav-secondary-contacts")).click();
			
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]")));
			driver.findElement(By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]")).click();
			
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-field='email']")));
			driver.findElement(By.xpath("//input[@data-field='email']")).sendKeys(contactValues.get("email"));
			driver.findElement(By.xpath("//input[@data-field='firstname']")).sendKeys(contactValues.get("firstname"));
			driver.findElement(By.xpath("//input[@data-field='lastname']")).sendKeys(contactValues.get("lastname"));
			driver.findElement(By.xpath("//input[@data-field='jobtitle']")).sendKeys(contactValues.get("jobtitle"));

			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]")));
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].click();", driver
					.findElement(By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]")));
			Thread.sleep(7000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-primary-contacts-branch")));
		}
	}

	@Then("^closed the browser$")
	public void closed_the_browser() {
		driver.quit();
	}

}
