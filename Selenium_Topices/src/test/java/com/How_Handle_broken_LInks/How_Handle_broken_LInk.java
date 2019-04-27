package com.How_Handle_broken_LInks;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.google.common.base.Verify;

public class How_Handle_broken_LInk {

	public WebDriver driver;

	/**
	 * What is broken LInk What is HTTP broken connection link What happens when you
	 * click at the link send request to server. server respond back What happen if
	 * it not respond back or the link is not visable 
	 * 
	 * @throws Exception
	 */
	
	@Test
	public synchronized void brokenLINnk() throws Exception {

		System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.bankofamerica.com");
		Assert.assertTrue(true);

		String title = driver.getTitle();
		System.out.println("This is the WebPage title:\t" + title);
		assertEquals(title, "Bank of America - Banking, Credit Cards, Home Loans and Auto Loans");

		List<WebElement> links = driver.findElements(By.tagName("a"));
	
		System.out.println("Total links are " + links.size());

		for (int i = 0; i < links.size(); i++) {
			WebElement elem = links.get(i);

			String url = elem.getAttribute("href");
			verfiyLinkActive(url);

		}

	}

	public static void verfiyLinkActive(String LinkUrl) {

		try {

			URL url = new URL(LinkUrl);
			HttpsURLConnection httpUrlconnect = (HttpsURLConnection) url.openConnection();
			httpUrlconnect.setConnectTimeout(3000);
			httpUrlconnect.connect();

			System.out.println(httpUrlconnect.getResponseCode());

			if (httpUrlconnect.getResponseCode() == 200) {
				System.out.println(LinkUrl + " - " + httpUrlconnect.getResponseMessage());

			}
			if (httpUrlconnect.getResponseCode() == HttpsURLConnection.HTTP_NOT_FOUND) {
				System.out.println(LinkUrl + " - " + httpUrlconnect.getResponseMessage() + " - "
						+ HttpsURLConnection.HTTP_NOT_FOUND);
				Assert.assertTrue(true, "This method execution is fail becouse the system found broken links");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@AfterMethod
	public void teardown() {
		driver.manage().deleteAllCookies();
		driver.close();
		driver.quit();

	}
}
