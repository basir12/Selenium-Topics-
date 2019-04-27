package com.Selenium_Grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class Server_Executionof_TestCases {
	
	
	@Test
	public void seleniumGridTest() throws MalformedURLException {
		
		
		
		/**
		 *  by Mukish: Url : https://www.youtube.com/watch?v=6nq1dOiTdC8
		 *  desirecap help us to run our testscrips on selenium gird
		*/
		DesiredCapabilities cop = DesiredCapabilities.chrome();
		
		cop.setPlatform(Platform.WINDOWS);
				
		/**
		 * Here we connect our hub localhost: 
		 * wd stands for webdriver
		 */
		URL url = new URL("http://localhost:4444/wd/hub");
		
		WebDriver driver = new RemoteWebDriver(url, cop);
				
		driver.get("http://learn-automation.com");
		driver.quit();
		
		
		
	}
	
	

}
