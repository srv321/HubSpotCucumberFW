package StepDefinitions;

import java.util.List;
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

public class ContactsDTStepDefinition {

	WebDriver driver;
	WebDriverWait wait;

	@Given("^user already on Login Page$")
	public void user_already_on_Login_Page() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Selenium Live Training-Naveen\\drivers\\chromedriver79.0.3945.36.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://app.hubspot.com/login");
		wait = new WebDriverWait(driver, 10);

	}

	@When("^title login page is HubSpot Login$")
	public void title_login_page_is_HubSpot_Login() {
		wait.until(ExpectedConditions.titleContains("HubSpot Login"));
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("HubSpot Login", title);

	}

	@Then("^user fills username and password$")
	public void user_fills_username_and_password(DataTable credentials) {
		List<List<String>> data = credentials.raw();
		driver.findElement(By.id("username")).sendKeys(data.get(0).get(0));
		driver.findElement(By.id("password")).sendKeys(data.get(0).get(1));
	}

	@Then("^user does click login button$")
	public void user_does_click_login_button() {
		driver.findElement(By.id("loginBtn")).click();
	}

	@Then("^user is located on home page$")
	public void user_is_located_on_home_page() {
		wait.until(ExpectedConditions.titleContains("Reports dashboard"));
		String title = driver.getTitle();
		Assert.assertEquals(title, "Reports dashboard");
	}

	@Then("^user navigates to contact page$")
	public void user_navigates_to_contact_page() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-primary-contacts-branch")));
		driver.findElement(By.id("nav-primary-contacts-branch")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-secondary-contacts")));
		driver.findElement(By.id("nav-secondary-contacts")).click();
	}

	@Then("^user does click on create contact$")
	public void user_does_click_on_create_contact() {
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]")));
		driver.findElement(By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]")).click();
	}

	@Then("^user fills contact details$")
	public void user_fills_contact_details(DataTable contactsData) {
		List<List<String>> contactValues = contactsData.raw();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-field='email']")));
		driver.findElement(By.xpath("//input[@data-field='email']")).sendKeys(contactValues.get(0).get(0));
		driver.findElement(By.xpath("//input[@data-field='firstname']")).sendKeys(contactValues.get(0).get(1));
		driver.findElement(By.xpath("//input[@data-field='lastname']")).sendKeys(contactValues.get(0).get(2));
		driver.findElement(By.xpath("//input[@data-field='jobtitle']")).sendKeys(contactValues.get(0).get(3));

		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]")));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", driver
				.findElement(By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]")));
	}

	@Then("^closes the browser$")
	public void closes_the_browser() {
		driver.quit();
	}

}
