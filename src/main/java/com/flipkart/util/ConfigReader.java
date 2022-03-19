package com.flipkart.util;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static Properties prop;
	
	public static String getProperty(String propertyName) {
	try {
		prop = new Properties();
		prop.load(new FileInputStream("D:/eclipse-workspace/Selenium_Demo_Project/src/test/resources/Config.properties"));
	
	}catch(Exception e) {
		e.printStackTrace();
	}
	return prop.getProperty(propertyName);
	}
}
