package com.HeadLess_Browser;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class Headless_Browser {

	/**
	 * Why Headless browser: because it runs internal there is no any GUI Headless
	 * browser use HtMLUnit
	 * 
	 * @throws FileNotFoundException
	 * @throws InterruptedException
	 */

	@Test
	public void verfyFaceBook() throws FileNotFoundException {

		 WebDriver driver = new HtmlUnitDriver();

//		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Drivers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();

		driver.get("http://www.facebook.com");
		String title = driver.getTitle();

		WebElement username = driver.findElement(By.id("email"));
		username.clear();
		username.sendKeys("baseer.wardak@gmail.com");

		WebElement pass = driver.findElement(By.xpath("//input[@type='password']"));
		pass.clear();
		pass.sendKeys("baseerminabaseermina123");

		assertEquals(title, "Facebook - Log In or Sign Up");

		

	}

}
