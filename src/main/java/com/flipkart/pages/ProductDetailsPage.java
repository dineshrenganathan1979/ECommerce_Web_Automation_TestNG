package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {
	

	WebDriver driver;
	
	//Constructor
	ProductDetailsPage(WebDriver driver){
		this.driver = driver;
	}

	//Locators
	
	private By selectedProduct = By.xpath("//h1[@itemprop='name']");
	private By addtoCartButton  = By.cssSelector("p#add_to_cart>button");
	private By productImage = By.xpath("//img[@id='bigpic']");
	private By ProductPrice = By.cssSelector("#our_price_display");
	
	
	public boolean isAddToCartButtonVisible() {
		return driver.findElement(addtoCartButton).isDisplayed();
	}
	
	public String getSelectedProductName() {
		return driver.findElement(selectedProduct).getText();
	}
	
	public String verifyProductImage() {
		
		return driver.findElement(productImage).getAttribute("title");
		
	}
	
	public String getProductPrice() {
		return driver.findElement(ProductPrice).getText();
	}
	
	public ShoppingCartPage addProductToShoppingCart() {
		
		driver.findElement(addtoCartButton).click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String handle = driver.getWindowHandle();
		driver.switchTo().window(handle);
		return new ShoppingCartPage(driver);
	}
}
