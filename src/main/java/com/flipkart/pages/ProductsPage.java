package com.flipkart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.base.TestBase;

public class ProductsPage  {
	
	WebDriver driver;
	
	//Constructor
	ProductsPage(WebDriver driver){
		this.driver = driver;
	}
	
	//Locators
	
	private By multpleProductName = By.cssSelector(".product-name");
	private By multipleAddtoCartButton = By.xpath("//a[@title='Add to cart']");
	private By multipleProductImage = By.cssSelector("div.product-image-container> a > img.replace-2x.img-responsive");
	private By selectedProduct = By.xpath("//h1[@itemprop='name']");
	
	public int totalProductsImage() {
		List<WebElement> productsImageList = driver.findElements(multipleProductImage);
		System.out.println("Total products: "+productsImageList.size());
		return productsImageList.size();
		
	}
	
	
	public ProductDetailsPage clickProductImage(String productTitle) {
		String locator = "(//a[@title='"+ productTitle +"'])[1]";
		By SingleImageElement = By.xpath(locator);
		WebElement SingleProductImage= driver.findElement(SingleImageElement);
		
		Actions action = new Actions(driver);
		action.moveToElement(SingleProductImage).click().build().perform();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Navigating to frame with index 0
		driver.switchTo().frame(0);
		return new ProductDetailsPage(driver);
	}

}
