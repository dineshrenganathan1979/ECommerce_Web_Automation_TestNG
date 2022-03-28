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
public class OrderConfirmationPageTest extends TestBase {
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
	String sheetName="OrderConfirmationPageTest";
	
	OrderConfirmationPageTest(){
		super();
	}
	
	@BeforeMethod
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
		orderConfirmationPage = orderPaymentPage.navigateToOrderConfirmationPage();
	}
	
	@DataProvider
	public Object[][] getOrderConfimationPageDetails(){
		
		return XLSReader.getExcelTestData(sheetName);
	}
	
	@Test(dataProvider = "getOrderConfimationPageDetails",priority=1)
	public void VerifyPaymentDetails(String OrderConfimationPageLabel, String OrderConfimationSuccessMessage, String OrderConfirmationText, String TotalAmountOfProduct) {
		Assert.assertEquals(orderConfirmationPage.getOrderConfimationPageLabelText(), OrderConfimationPageLabel);
		Assert.assertEquals(orderConfirmationPage.getOrderConfimationSuccessMessage(), OrderConfimationSuccessMessage);
		Assert.assertEquals(orderConfirmationPage.getorderConfirmationText(), OrderConfirmationText);
		Assert.assertEquals(orderConfirmationPage.getOrderAmount(), TotalAmountOfProduct);
		Assert.assertTrue(orderConfirmationPage.getOrderID().isDisplayed());
		String[] orderConfimationDetails = orderConfirmationPage.getOrderID().getText().split("Do not forget to include your order reference");
		String orderID= orderConfimationDetails[1].substring(0, 10);
		System.out.println("The Order ID is: "+orderID);
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
