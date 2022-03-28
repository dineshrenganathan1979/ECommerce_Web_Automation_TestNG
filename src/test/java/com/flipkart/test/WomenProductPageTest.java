package com.flipkart.test;

import static org.testng.Assert.assertEquals;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.AccountsPage;
import com.flipkart.pages.LoginPage;
import com.flipkart.pages.ProductDetailsPage;
import com.flipkart.pages.ProductsPage;
import com.flipkart.util.XLSReader;
//@Listeners(TestListener.class)
public class WomenProductPageTest extends TestBase {
	
	LoginPage loginPage;
	AccountsPage accountsPage;
	ProductsPage productsPage;
	ProductDetailsPage productDetailsPage;
	String sheetName ="ProductDetailsPageTest";
	
	WebDriver driver;
	
	WomenProductPageTest(){
		super();
	}
	
	@BeforeMethod
	public void init() {
		driver = getDriverInstance();
		loginPage = new LoginPage(driver);
		accountsPage = loginPage.loginToApplication(prop.getProperty("username"), prop.getProperty("password"));
		productsPage = accountsPage.navigateToProductsPage();
	}
	@DataProvider
	public Object[][] getProduceDetails(){
		
		return XLSReader.getExcelTestData(sheetName);
	}
	
	
	@Test(dataProvider = "getProduceDetails", priority=1)
	public void verifyProductDetailsPage(String ProductName, String ProductPrice ) {
		 Assert.assertEquals(productsPage.totalProductsImage(), 7);
		productDetailsPage = productsPage.clickProductImage(ProductName);
		Assert.assertEquals(productDetailsPage.getSelectedProductName(), ProductName); 
		Assert.assertTrue(productDetailsPage.isAddToCartButtonVisible());
		Assert.assertEquals(productDetailsPage.verifyProductImage(),ProductName);
		Assert.assertEquals(productDetailsPage.getProductPrice(), ProductPrice);
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
