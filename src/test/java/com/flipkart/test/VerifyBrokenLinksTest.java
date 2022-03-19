package com.flipkart.test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


//@Listeners(TestListener.class)
public class VerifyBrokenLinksTest {

    static WebDriver driver;
	public static void main(String[] args) {


	  WebDriverManager.chromedriver().setup();
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().deleteAllCookies();
	  driver.get("https://amazon.in");
	  List<WebElement> linksList = driver.findElements(By.tagName("a"));
	  for(WebElement link: linksList) {
	  if(link != null) {
		  verifyBrokenLinks(link);  
	  }
		  
	  }
	  driver.navigate().to("https://the-internet.herokuapp.com/broken_images");
	  List<WebElement> imagesList = driver.findElements(By.tagName("img"));
	  for(WebElement image: imagesList) {
		  if(image != null) {
			  verifyBrokenImages(image);
		  } 
		  else if(image.getAttribute("naturalWidth").equals("0")) {
			  System.out.println("Found Broken image with url: "+image.getAttribute("src"));
		  }
	  }
	  driver.quit();

	}

	
	public static void verifyBrokenLinks(WebElement element) {
		
		String url = element.getAttribute("href");
		
		try {
			HttpURLConnection conn = (HttpURLConnection) (new URL(url).openConnection());
			conn.setRequestMethod("HEAD");
			conn.connect();
			System.out.println(conn.getResponseCode());
			if(conn.getResponseCode() >400) {
				System.out.println("Found the Broken Link: "+url);
			}
			else {
				System.out.println("Found valid Link:"+url);
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void verifyBrokenImages(WebElement element) {
		
		String url = element.getAttribute("src");
		
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			System.out.println(conn.getResponseCode());
			if(conn.getResponseCode() > 400)
			{
				System.out.println("Found broken image with url: "+url);
			}
			else {
				System.out.println("Found Valid Image with url: "+url);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
