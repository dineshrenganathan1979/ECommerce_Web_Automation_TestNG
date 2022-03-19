package com.flipkart.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import com.flipkart.util.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
public static WebDriver driver;
public static Properties prop;

 public TestBase() {
	 prop = new Properties();
	 try {
		prop.load(new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Config.properties"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 }
	
	public static WebDriver getDriverInstance() {
		if(ConfigReader.getProperty("browser").equalsIgnoreCase("Chrome")) {
			    WebDriverManager.chromedriver().setup();
			    DesiredCapabilities caps = new DesiredCapabilities();
				caps.setAcceptInsecureCerts(false);
				caps.setJavascriptEnabled(true);
				ChromeOptions options = new ChromeOptions();
			    options.addArguments("start-maximized");
			    options.merge(caps);
			    driver = new ChromeDriver(options);
				
       	}
		else if(ConfigReader.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			 		
		} else if (ConfigReader.getProperty("browser").equals("safari")) {
			driver = new SafariDriver();
		} else {
		System.out.println("Please pass the correct browser value: " + ConfigReader.getProperty("browser"));
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		return driver;
		
	}
}
