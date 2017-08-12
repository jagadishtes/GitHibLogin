package GitHubLogin.GitHubLogin;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.github.pageobjects.HomePage;
import static org.testng.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class GitHubLoginTest {
	WebDriver driver;
	WebDriverWait driverWait;

	@BeforeTest
	public void loadDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/jagadishhatti/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driverWait = new WebDriverWait(driver, 15);
	}

	/***
	 * Method to test the login functionality with correct user name and password
	 * 
	 * @author jagadishhatti
	 */

	@Test()
	public void validLogin() {
		driver.get("https://github.com/");
//		Reporter.log(" GitHub Browser Opened");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Sign in']")));
		driver.findElement(By.xpath("//*[text()='Sign in']")).click();
//		Reporter.log(" Clicked on SignIn button on HomePage");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Sign in to GitHub']")));
		HomePage homePage = new HomePage(driver);
		homePage.login("jagadishtes", "Shippable1");
		driverWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[text()='Learn Git and GitHub without any code!']")));
		assertTrue(driver.findElement(By.xpath("//*[text()='Learn Git and GitHub without any code!']")).isDisplayed(),
				"User is not logged in");
//		Reporter.log("User successfully logged in");
	}

	/***
	 * Method to test the login functionality with incorrect user name and password.
	 * @author jagadishhatti
	 */
	@Test
	public void inVlaidLogin() {
		driver.get("https://github.com/");
//		Reporter.log(" GitHub Browser Opened");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Sign in']")));
		driver.findElement(By.xpath("//*[text()='Sign in']")).click();
//		Reporter.log(" Clicked on SignIn button on HomePage");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Sign in to GitHub']")));
		HomePage homePage = new HomePage(driver);
		homePage.login("jagadishte", "Shippable");
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".flash.flash-full.flash-error")));
		assertTrue(driver.findElement(By.cssSelector(".flash.flash-full.flash-error")).isDisplayed(),
				"Error message is not displayed");
//		Reporter.log("User not logged in , invalid credentials");
	}

	@AfterTest
	public void closeDriver() {
		driver.quit();
	}
}
