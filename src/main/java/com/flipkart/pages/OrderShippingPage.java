package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderShippingPage {
	
	WebDriver driver;

	//Constructor
	public OrderShippingPage(WebDriver driver) {
		this.driver= driver;
	}
	
	//Locators
	private By proceedToCheckoutButtonAddressPage = By.xpath("//button[@name='processAddress']");
	private By agreeTermsShippingPage = By.cssSelector("input#cgv");
	private By proceedToCheckoutButtonShippingPage = By.xpath("//button[@name='processCarrier']");
	private By payByCheckShippingPage = By.xpath("//a[@title='Pay by check.']");
	
	//methods
	public void clickProceedToCheckoutButtonAddressPage() {
	    driver.findElement(proceedToCheckoutButtonAddressPage).click();
	}
	
	public void selectAgreeTermsShippingPage() {
	    driver.findElement(agreeTermsShippingPage).click();
	}
	
	public void clickProceedToCheckoutButtonShippingPage() {
	    driver.findElement(proceedToCheckoutButtonShippingPage).click();
	}
	
	public void selectPayByCheckOption() {
		driver.findElement(payByCheckShippingPage).click();		
	}
	
	public OrderPaymentPage proceedToOrderPaymentPage() {
		this.clickProceedToCheckoutButtonAddressPage();
		this.selectAgreeTermsShippingPage();
		this.clickProceedToCheckoutButtonShippingPage();
		this.selectPayByCheckOption();
		return new OrderPaymentPage(driver);
	}

}
