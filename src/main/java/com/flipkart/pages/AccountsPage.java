package com.flipkart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountsPage {
	
	private WebDriver driver;
	
	//Constuctor	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
	}

	
	//Locators
	private By tab = By.cssSelector("#center_column > div > div:nth-child(1) > ul >li > a> span");
	private By tabWishList = By.xpath("//a[@title='My wishlists']");
	private By womenProduct = By.linkText("Women");
	
	public String getAccountsPageTitle(){

        return driver.getTitle();

       }
	
	  public int totalTabItemsCount() {
		  List<WebElement> tabItemsList = driver.findElements(tab);
		   return tabItemsList.size();
	  }
	
	
	   public boolean verifyTabItem(String tabName) {
		   boolean flag = false;
		   List<WebElement> tabItemsList = driver.findElements(tab);
		     for(int i=0; i<tabItemsList.size(); i++) {
		//	   System.out.println(tabItemsList.get(i).getText());
			   if(tabItemsList.get(i).getText().equalsIgnoreCase(tabName)) {
				   flag = true;
			   }
			  }
		   return flag;
	   }
	   
	   public boolean verifyTabWishList(String tabName) {
		   return driver.findElement(tabWishList).isDisplayed();
	   }
	   
	   
	   public ProductsPage navigateToProductsPage() {
		   driver.findElement(womenProduct).click();
		   return new ProductsPage(driver);
	   }
	   
	
}
