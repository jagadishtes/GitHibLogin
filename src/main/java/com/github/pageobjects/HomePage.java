package com.github.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void login(String userName, String password) {
		driver.findElement(By.id("login_field")).sendKeys(userName);
//		Reporter.log("Entered UserName ");
		driver.findElement(By.id("password")).sendKeys(password);
//		Reporter.log("Entered Password");
		driver.findElement(By.name("commit")).click();
//		Reporter.log("Clicked on submit button");
	}
}
