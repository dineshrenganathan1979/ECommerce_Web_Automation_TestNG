package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPaymentPage {

	WebDriver driver;
	
	//Constructor
	public OrderPaymentPage(WebDriver driver) {
		this.driver=driver;
	}

	
	//Locators
	private By paymentOption = By.cssSelector("#center_column > form > div > h3");
	private By paymentText = By.cssSelector("#center_column > form > div > p.cheque-indent > strong");
	private By totalAmountOfProduct = By.cssSelector("#amount");
	private By orderConfirmationButton = By.xpath("//button/span[contains(text(),'I confirm my order')]");
	
	//methods
	public String getPaymentOption() {
		return driver.findElement(paymentOption).getText();
	}

	public String getPaymentText() {
		return driver.findElement(paymentText).getText();
	}
	
	public String getTotalProductAmount() {
		return driver.findElement(totalAmountOfProduct).getText();
	}
	
	public OrderConfirmationPage navigateToOrderConfirmationPage() {
		driver.findElement(orderConfirmationButton).click();
		return new OrderConfirmationPage(driver);
	}
}
