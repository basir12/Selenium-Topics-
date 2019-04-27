package com.Library;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.swing.tree.ExpandVetoException;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.sql.Driver;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.io.Files;

public class seleniumRepository extends BasePage {
	final static Logger logger = Logger.getLogger(seleniumRepository.class);
	private WebDriver driver;
	private boolean isDemo = false;

	public boolean getDemo() {
		return isDemo;
	}

	public void setDemo(boolean isDemo) {
		this.isDemo = isDemo;
	}

	private WebDriver startChromeBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/Browser/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Error", e);
		}
		return driver;
	}

	public seleniumRepository(WebDriver _driver) {

		driver = _driver;

	}

	public void enterTextField(By by) {

		WebElement varibileName = driver.findElement(by);

		varibileName.click();

	}

	public void enterTextFieldwithRaw(By by) {

		WebElement varibileName = driver.findElement(by);
		highlightElement(varibileName);
		varibileName.click();
		varibileName.sendKeys(Keys.ARROW_DOWN);
		varibileName.click();

	}

	public void enterTextField(By by, String value) {

		WebElement variableName = driver.findElement(by);
		highlightElement(variableName);
		variableName.click();
		variableName.sendKeys(value);

	}

	public void clickButton(By by) {

		driver.findElement(by).click();

	}

	public WebElement fluentWait(final By by) {
		WebElement targetElem = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

			targetElem = wait.until(new Function<WebDriver, WebElement>() {

				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
			});
		} catch (Exception e) {
		}
		return targetElem;
	}

	public WebElement windowHandle() {
		WebElement handle = null;

		driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		return handle;

	}

	public void selectDropDownVisibleText(By by, String visibleTextValue) {

		try {
			WebElement dropDownElement = driver.findElement(by);
			Select DropDown = new Select(dropDownElement);
			DropDown.selectByVisibleText(visibleTextValue);

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Error", e);
			assertTrue(false);
		}
	}

	public void handlePopups() {

		Set<String> windowId = driver.getWindowHandles(); // get window id of current window
		Iterator<String> itererator = windowId.iterator();

		String mainWinID = itererator.next();
		String newAdwinID = itererator.next();

		driver.switchTo().window(newAdwinID);
		System.out.println(driver.getTitle());

		driver.close();

		driver.switchTo().window(mainWinID);
		System.out.println(driver.getTitle());

	}

	public void handleAlerts() {

		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	public void declineAlerts() {

		Alert decline = driver.switchTo().alert();
		decline.dismiss();

	}

	public Alert isAlertPresent() {
		Alert alert = null;

		try {
			alert = driver.switchTo().alert();

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Error", e);
			assertTrue(false);

		}
		return alert;
	}

	public void actionClass(WebElement findElement, Keys arrowDown) {

		Actions actions = new Actions(driver);
		actions.moveToElement(findElement);
		actions.click();
		actions.sendKeys(arrowDown);
		actions.build().perform();

	}

	public String screenShot(String screenShotNmae, String filepath) {
		String screenShot = null;

		try {

			File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcfile, new File("target/screenShots.png/"));

		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Error", e);
			assertTrue(false);
		}
		return screenShot;
	}

	public WebElement highlightElement(WebElement webElement) {
		WebElement element = webElement;
		try {
			if (isDemo == true) {
				for (int i = 0; i < 4; i++) {
					// element = driver.findElement(by);
					WrapsDriver wrappedElement = (WrapsDriver) element;
					JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
					customWait(0.4);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: red; border: 2px solid yellow;");
					customWait(0.4);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
				}
			}
		} catch (Exception e) {
			logger.error("Error", e);
			assertTrue(false);
		}
		return element;

	}

	public WebElement highlightElement(By by) {
		WebElement element = null;
		try {
			if (isDemo == true) {
				for (int i = 0; i < 4; i++) {
					element = driver.findElement(by);
					WrapsDriver wrappedElement = (WrapsDriver) element;
					JavascriptExecutor js = (JavascriptExecutor) wrappedElement.getWrappedDriver();
					customWait(0.4);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
							"color: red; border: 2px solid yellow;");
					customWait(0.4);
					js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
				}
			}
		} catch (Exception e) {
			logger.error("Error", e);
			;
			assertTrue(false);
		}
		return element;
	}

	public void customWait(double inSeconds) {
		try {
			Thread.sleep((long) (inSeconds * 1000));
		} catch (Exception e) {
			System.out.println();
			assertTrue(false);
		}

	}

	public String captureScreenshot(String screenshotFileName, String filePath) {
		String screenshotPath = null;
		String timestamp = getCurrentTime();
		try {
			if (!filePath.isEmpty()) {
				checkDirectory(filePath);
				screenshotPath = filePath + screenshotFileName + timestamp + ".png";
			} else {
				checkDirectory("target/screenshots/");
				screenshotPath = "target/screenshots/" + screenshotFileName + timestamp + ".png";
			}

			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Files.copy(srcFile, new File(screenshotPath));
		} catch (Exception e) {
			// logger.error("Error: ", e);
			assertTrue(false);
		}
		// logger.info("Screenshot Captured: " + screenshotPath);
		return screenshotPath;
	}

	private String checkDirectory(String inputPath) {
		File file = new File(inputPath);
		String abPath = file.getAbsolutePath();
		File file2 = new File(abPath);
		if (!file2.exists()) {
			if (file2.mkdirs()) {
				// logger.info("folders created...");
			} else {
				// logger.info("folders Not created...");
			}

		}
		return abPath;
	}

	public String getCurrentTime() {
		String finalTimeStamp = null;
		Date date = new Date();
		String tempTime = new Timestamp(date.getTime()).toString();
		// logger.info("original time stamp is: [" +tempTime+ "]");
		finalTimeStamp = tempTime.replace(":", "_").replace(" ", "_").replace(".", "_").replace("-", "_");
		// logger.info("updated time stamp is: [" +finalTimeStamp+ "]");
		// tempTime.replace(':', '_').replace(' ', '_').replace('.', '_');
		return finalTimeStamp;
	}

	public WebDriver startLocalBrowser(String browser) {

		if (browser.contains("Chrome")) {
			// start chrome browser
			driver = startChromeBrowser();
		} else if (browser.contains("FireFox")) {

			driver = startFireFoxBrowser();
			logger.info("starting FireFox browser, but not ipmlemented yett.. sorry");

		} else if (browser.contains("IE")) {
			driver = startIEBrowser();
			logger.info("starting Firefox browser, but not ipmlemented yett.. sorry");
		} else {
			// other browsers we don't support with this versio of library
			logger.info(
					"ops, sorry, you are not initialized the any other browsers please contact with automation team. Thanks ");
		}

		return driver;
	}

	private WebDriver startIEBrowser() {

		try {
			System.setProperty("webdriver.ie.driver", "src/test/resources/Browser/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			logger.info("Starting IE browser");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("error: ", e);
		}
		return driver;
	}

	private WebDriver startFireFoxBrowser() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/Browser/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		return null;
	}

}
