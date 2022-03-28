package com.flipkart.test;

import org.openqa.selenium.WebDriver;
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
import com.flipkart.pages.OrderConfirmationPage;
import com.flipkart.pages.OrderDetailsPage;
import com.flipkart.pages.OrderPaymentPage;
import com.flipkart.pages.OrderShippingPage;
import com.flipkart.pages.ProductDetailsPage;
import com.flipkart.pages.ProductsPage;
import com.flipkart.pages.ShoppingCartPage;
import com.flipkart.util.XLSReader;
//@Listeners(TestListener.class)
public class OrderPaymentPageTest extends TestBase {
	WebDriver driver;
	LoginPage loginPage;
	AccountsPage accountsPage;
	ProductsPage productsPage;
	ProductDetailsPage productDetailsPage;
	ShoppingCartPage shoppingCartPage;
	OrderDetailsPage orderDetailsPage;
	OrderShippingPage orderShippingPage;
	OrderPaymentPage orderPaymentPage;
	OrderConfirmationPage orderConfirmationPage;
	String sheetName="OrderPaymentPageTest";
	
	OrderPaymentPageTest(){
		super();
	}
	
	@BeforeClass
	public void initialize() {
		driver = getDriverInstance();
		loginPage = new LoginPage(driver);
		accountsPage = loginPage.loginToApplication(prop.getProperty("username"), prop.getProperty("password"));
		productsPage = accountsPage.navigateToProductsPage();
		productDetailsPage = productsPage.clickProductImage("Faded Short Sleeve T-shirts");
		shoppingCartPage = productDetailsPage.addProductToShoppingCart();
		orderDetailsPage = shoppingCartPage.navigatetoOrderDetailsPage();
		orderShippingPage = orderDetailsPage.navigateToOrderShippingPage();
		orderPaymentPage = orderShippingPage.proceedToOrderPaymentPage();
		
	}
	
	@DataProvider
	public Object[][] getOrderPaymentDetails(){
		
		return XLSReader.getExcelTestData(sheetName);
	}
	
	@Test(dataProvider = "getOrderPaymentDetails",priority=1)
	public void VerifyPaymentDetails(String PaymentMode, String PaymentText, String TotalAmountOfProduct) {
		Assert.assertEquals(orderPaymentPage.getPaymentOption(), PaymentMode);
		Assert.assertEquals(orderPaymentPage.getPaymentText(), PaymentText);
		Assert.assertEquals(orderPaymentPage.getTotalProductAmount(), TotalAmountOfProduct);
	}
	
	//@Test(priority=2)
	public void proceedToOrderConfirmationPage() {
		orderConfirmationPage = orderPaymentPage.navigateToOrderConfirmationPage();		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
