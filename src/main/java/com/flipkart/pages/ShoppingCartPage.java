package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage {
	
	WebDriver driver;

	//Constructor
	public ShoppingCartPage(WebDriver driver) {
		this.driver= driver;
		
	}
	
	//Locators
	
	private By successMessage = By.cssSelector("div.layer_cart_product.col-xs-12.col-md-6 > h2");
	private By productName = By.cssSelector("span#layer_cart_product_title");
	private By productQuantity = By.cssSelector("span#layer_cart_product_quantity");
	private By productPrice = By.cssSelector("span#layer_cart_product_price");
	private By proceedToCheckoutButton = By.xpath("//a[@title='Proceed to checkout']");
	private By continueShoppingButton = By.xpath("//span[@title='Continue shopping']");
	
	public String getSucessMessage() {
	
		return driver.findElement(successMessage).getText();
	}
	
	public String getProductName() {
		
		return driver.findElement(productName).getText();
	}

	public String getProductQuantity() {
		
		return driver.findElement(productQuantity).getText();
	}

	public String getProductPrice() {
		
		return driver.findElement(productPrice).getText();
	}

	public boolean verifyProceedtoCheckButton() {
		return driver.findElement(proceedToCheckoutButton).isDisplayed();
	}
	
	public boolean verifyContinueShoppingButton() {
		return driver.findElement(continueShoppingButton).isDisplayed();
	}
	
	public OrderDetailsPage navigatetoOrderDetailsPage() {
		driver.findElement(proceedToCheckoutButton).click();		
		return new OrderDetailsPage(driver);
	}
	
}



