package StepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class LoginStepDefinition {

	WebDriver driver;
	WebDriverWait wait;

	@Given("^user is already on Login Page$")
	public void user_is_already_on_Login_Page() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Selenium Live Training-Naveen\\drivers\\chromedriver79.0.3945.36.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://app.hubspot.com/login");
		wait = new WebDriverWait(driver, 10);

	}

	@When("^title of login page is HubSpot Login$")
	public void title_of_login_page_is_HubSpot_Login() {
		wait.until(ExpectedConditions.titleContains("HubSpot Login"));
		String title = driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("HubSpot Login", title);

	}

	// //Reg Exp:
	// //1. \"([^\"]*)\"
	// //2. \"(.*)\"

	@Then("^user enters \"(.*)\" and \"(.*)\"$")
	public void user_enters_username_and_password(String username, String password) {
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
	}

	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() {
		driver.findElement(By.id("loginBtn")).click();
	}

	@Then("^user is on home page$")
	public void user_is_on_home_page() {
		wait.until(ExpectedConditions.titleContains("Reports dashboard"));
		String title = driver.getTitle();
		Assert.assertEquals(title, "Reports dashboard");

	}

	@Then("^user moves to contact page$")
	public void user_moves_to_contact_page() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-primary-contacts-branch")));
		driver.findElement(By.id("nav-primary-contacts-branch")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nav-secondary-contacts")));
		driver.findElement(By.id("nav-secondary-contacts")).click();

	}

	@Then("^user clicks on create contact$")
	public void user_clicks_on_create_contact() {
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]")));
		driver.findElement(By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=1]")).click();

	}

	@Then("^user enters contact details \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enters_contact_details_and_and_and(String email, String firstname, String lastname,
			String jobtitle) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-field='email']")));
		driver.findElement(By.xpath("//input[@data-field='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@data-field='firstname']")).sendKeys(firstname);
		driver.findElement(By.xpath("//input[@data-field='lastname']")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@data-field='jobtitle']")).sendKeys(jobtitle);

		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]")));
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", driver
				.findElement(By.xpath("(//button[@type='button']//span[text()='Create contact'])[position()=2]")));

	}

	@Then("^close the browser$")
	public void close_the_browser() {
		driver.quit();
	}
}
