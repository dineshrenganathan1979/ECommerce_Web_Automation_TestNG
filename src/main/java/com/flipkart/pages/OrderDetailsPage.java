package com.flipkart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderDetailsPage {
	
	WebDriver driver;
	
	//Constructor
	public OrderDetailsPage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By productName = By.xpath("(//p[@class='product-name']/a)[2]");
	private By productImage = By.xpath("//td[@class='cart_product']/a/img");
	private By orderSteps = By.xpath("//ul[@id='order_step']/li/span");
	private By activeOrderStep = By.cssSelector("li.step_current.first");
	private By itemAvailable = By.xpath("//td[@class='cart_avail']/span");
	private By unitPrice = By.xpath("//td[@class='cart_unit']/span/span");
	private By quanityInputField = By.cssSelector("td.cart_quantity.text-center>input[type='text']");
	private By cartTotalPrice = By.cssSelector("td.cart_total>span");
	private By deleteOrder = By.cssSelector("i.icon-trash");
	private By total_productPrice = By.cssSelector("td#total_product");
	private By total_shippingPrice = By.cssSelector("td#total_shipping");
	private By total_price_without_tax = By.cssSelector("td#total_price_without_tax");
	private By total_tax = By.cssSelector("td#total_tax");
	private By total_price = By.cssSelector("span#total_price");
	private By proceedToCheckoutButton = By.xpath("(//a[@title='Proceed to checkout'])[2]");
	
	public String getProductName() {
		return driver.findElement(productName).getText();
	}
	
	public int totalStepsCount() {
		List<WebElement> stepCount = driver.findElements(orderSteps);
		return stepCount.size();
	}
	
	public String getItemAvailable() {
		return driver.findElement(itemAvailable).getText();
	}
	
	public String getActiveOrderStepColor() {
		return driver.findElement(activeOrderStep).getCssValue("border-color");
	}
	
	public String getUnitPrice() {
		return driver.findElement(unitPrice).getText();
	}
	
	public String getQuanityInputField() {
		return driver.findElement(quanityInputField).getAttribute("value");
	}
	
	public String getCartTotalPrice() {
		return driver.findElement(cartTotalPrice).getText();
	}
	
	public void deleteOrder() {
		driver.findElement(deleteOrder).click();
	}
	
	public String getTotalProductPrice() {
		return driver.findElement(total_productPrice).getText();
	}

	public String getTotalShippingPrice() {
		return driver.findElement(total_shippingPrice).getText();
	}
	
	public String getTotal_price_without_tax() {
		return driver.findElement(total_price_without_tax).getText();
	}
	
	public String getTotal_tax() {
		return driver.findElement(total_tax).getText();
	}
	
	public String getTotal_price() {
		return driver.findElement(total_price).getText();
	}
	
	public boolean verifyProceedToCheckOutButton() {
		return driver.findElement(proceedToCheckoutButton).isDisplayed();
	}
	
	public boolean isProductNameDisplayedInProductIamge(String productName) {
		return driver.findElement(productImage).getAttribute("alt").contains(productName);
	}
	
}
