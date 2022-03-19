package com.flipkart.test;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.AccountsPage;
import com.flipkart.pages.LoginPage;
import com.flipkart.pages.ProductDetailsPage;
import com.flipkart.pages.ProductsPage;
import com.flipkart.pages.ShoppingCartPage;
import com.flipkart.util.XLSReader;
//@Listeners(TestListener.class)
public class ShoppingCartPageTest extends TestBase {
	WebDriver driver;
	LoginPage loginPage;
	AccountsPage accountsPage;
	ProductsPage productsPage;
	ProductDetailsPage productDetailsPage;
	ShoppingCartPage shoppingCartPage;
    String sheetName="ShoppingCartPageTest";
	public ShoppingCartPageTest() {
		super();
	}
	
	@BeforeMethod
	public void initialize() {
		driver = getDriverInstance();
		loginPage = new LoginPage(driver);
		accountsPage = loginPage.loginToApplication(prop.getProperty("username"), prop.getProperty("password"));
		productsPage = accountsPage.navigateToProductsPage();
		
	}
	
	@DataProvider
	public Object[][] getShoppingCartDetails(){
		
		return XLSReader.getExcelTestData(sheetName);
	}
	
	@Test(dataProvider = "getShoppingCartDetails",priority=1)
	public void verifyShoppingCartPage(String ProductName, String ProductPrice, String SuccessMessage, String Quantity) {
		
		
		productDetailsPage = productsPage.clickProductImage(ProductName);
		shoppingCartPage = productDetailsPage.addProductToShoppingCart();
		Assert.assertEquals(shoppingCartPage.getSucessMessage(), SuccessMessage);
		Assert.assertEquals(shoppingCartPage.getProductName(), ProductName);
		Assert.assertEquals(shoppingCartPage.getProductQuantity(), Integer.parseInt(Quantity));
		Assert.assertEquals(shoppingCartPage.getProductPrice(), ProductPrice);
		Assert.assertTrue(shoppingCartPage.verifyContinueShoppingButton());
		Assert.assertTrue(shoppingCartPage.verifyProceedtoCheckButton());
		
	}
	
	
	@AfterMethod
	public void tearDown() {
	    driver.quit();
	}
	
	
}
