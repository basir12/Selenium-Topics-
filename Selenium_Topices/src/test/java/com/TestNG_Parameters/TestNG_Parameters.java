package com.TestNG_Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_Parameters {

	// String Webpage = "https://opensource-demo.orangehrmlive.com/";
	WebDriver driver;

	@BeforeMethod
	public void beforMethod() {

		System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	@Parameters({ "url", "username", "pass" })
	public void parametersTest(String url, String username, String pass) throws Exception {

		// URL url = new URL(Webpage);
		// driver.navigate().to(url);

		driver.get(url);

		driver.findElement(By.id("txtUsername")).sendKeys(username);

		driver.findElement(By.id("txtPassword")).sendKeys(pass);

		driver.findElement(By.id("btnLogin")).click();
		Assert.assertTrue(true);

		// driver.quit();

	}

}
