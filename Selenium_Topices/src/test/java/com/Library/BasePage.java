package com.Library;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BasePage {
	 final static Logger logger = Logger.getLogger(BasePage.class);
	public static WebDriver driver;
	public static seleniumRepository myRepository;

	private static String browser;
	private static String demoType;

	@BeforeClass  (groups="Smoke_Test")
	public void beforeAllClassesStare() {
	
				
		myRepository = new seleniumRepository(driver);
						
	/*	if (demoType.contains("true")) {
			myRepository.setDemo(true);
			logger.info("Test is running in Demo mode ON...");
		}else{
			logger.info("Test is running in Demo mode OFF...");
		}*/
		
	}

	@AfterClass
	public void afterAllTestCompleted() {

	}

/*	@BeforeMethod (groups="Smoke_Test")
	public void beforeEachTestStart() {
//		driver= myRepository.startLocalBrowser(browser);
		//driver = myRepository.startChromeBrowser();

	}*/

	@AfterMethod (groups="Smoke_Test")
	public void afterEachTestEnd(ITestResult result) {
		try {
			if (ITestResult.FAILURE == result.getStatus()) {
				myRepository.captureScreenshot(result.getName(), "target/screenshots/");
			}
			Thread.sleep(2 * 1000);

			driver.close(); // close the browser
			driver.quit(); // kills/deletes the driver object

		} catch (Exception e) {
			logger.error("Error: ", e);
		}
	}

}
