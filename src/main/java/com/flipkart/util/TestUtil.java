package com.flipkart.util;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.flipkart.base.TestBase;

public class TestUtil extends TestBase {
	public static int totalBrokenLinksCount=0;
	public static int totalBrokenImagesCount=0;
	public static WebDriver driver;
	
	public TestUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public static int FindBrokenLinks(WebElement element) {
		
		String url = element.getAttribute("href");
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			if(conn.getResponseCode() > 400) {
				System.out.println("Found broken Link with URL: "+url);
				totalBrokenLinksCount++;
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalBrokenLinksCount;
		
		
		
	}
	
	public static int FindBrokenImages(WebElement element) {
		
		String url = element.getAttribute("src");
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			if(conn.getResponseCode() > 400) {
				System.out.println("Found broken Image with URL: "+url);
				totalBrokenImagesCount++;
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalBrokenImagesCount;
		
		
	}
		
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

}
