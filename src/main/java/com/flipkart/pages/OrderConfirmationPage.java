package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderConfirmationPage {
	
	WebDriver driver;

	//Constructor
	public OrderConfirmationPage(WebDriver driver) {
		this.driver= driver;
		
	}
	
	//Locators
	private By orderConfimationPageLabel = By.cssSelector("#center_column > h1");
	private By orderConfimationSuccessMessage = By.cssSelector("#center_column > p.alert.alert-success");
	private By orderConfirmationText = By.cssSelector("#center_column > div > strong:nth-child(10)");
	private By orderAmount = By.cssSelector("#center_column > div > span > strong");
	private By orderID = By.xpath("//div[@class='box order-confirmation']");
	
	//methods
	
	public String getOrderConfimationPageLabelText() {
		return driver.findElement(orderConfimationPageLabel).getText();
	}
	
	public String getOrderConfimationSuccessMessage() {
		return driver.findElement(orderConfimationSuccessMessage).getText();
	}
	
	public String getorderConfirmationText() {
		return driver.findElement(orderConfirmationText).getText();
	}
	
	public String getOrderAmount() {
		return driver.findElement(orderAmount).getText();
	}
	
	public WebElement getOrderID() {
		return driver.findElement(orderID);
	}
	
}
