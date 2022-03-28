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
import com.flipkart.pages.OrderDetailsPage;
import com.flipkart.pages.OrderPaymentPage;
import com.flipkart.pages.OrderShippingPage;
import com.flipkart.pages.ProductDetailsPage;
import com.flipkart.pages.ProductsPage;
import com.flipkart.pages.ShoppingCartPage;
import com.flipkart.util.XLSReader;
//@Listeners(TestListener.class)
public class OrderShippingPageTest extends TestBase {
	WebDriver driver;
	LoginPage loginPage;
	AccountsPage accountsPage;
	ProductsPage productsPage;
	ProductDetailsPage productDetailsPage;
	ShoppingCartPage shoppingCartPage;
	OrderDetailsPage orderDetailsPage;
	OrderShippingPage orderShippingPage;
	OrderPaymentPage orderPaymentPage;
	String sheetName="OrderShippingPageTest";
	
	OrderShippingPageTest(){
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
		
		
	}
	
	@DataProvider
	public Object[][] getOrderShippingDetails(){
		
		return XLSReader.getExcelTestData(sheetName);
	}
	
	@Test(dataProvider = "getOrderShippingDetails",priority=1)
	public void VerifyOrderDetails(String ProductName, String ProductPrice, String Quantity, String ActiveColor, String Availability) {
		Assert.assertTrue(orderDetailsPage.isProductNameDisplayedInProductIamge(ProductName));
		Assert.assertTrue(orderDetailsPage.verifyProceedToCheckOutButton());
		Assert.assertTrue(orderDetailsPage.getActiveOrderStepColor().contains(ActiveColor));
		Assert.assertEquals(orderDetailsPage.getItemAvailable(), Availability);
		String unit_Price=orderDetailsPage.getUnitPrice().replace("$", "");
		String total_quanity =orderDetailsPage.getQuanityInputField();
		String shipping_Price =orderDetailsPage.getTotalShippingPrice().replace("$", ""); 
		String tax_Amount = orderDetailsPage.getTotal_tax().replace("$", "");
		float unitPrice = Float.parseFloat(unit_Price);
		float quanity = Float.parseFloat(total_quanity);
		float shippingPrice = Float.parseFloat(shipping_Price);
		float taxAmount = Float.parseFloat(tax_Amount);
		float item_total = unitPrice*quanity;
		float totalPriceWithShippingCost = item_total+shippingPrice;
		float total_price=totalPriceWithShippingCost+(totalPriceWithShippingCost*taxAmount)/100;
		String totalProductPrice = "$"+String.valueOf(item_total);
		String totalPriceWithTax = "$"+String.valueOf(total_price);
		String total_Price_After_Shipping_cost = "$"+String.valueOf(totalPriceWithShippingCost);
		Assert.assertEquals(orderDetailsPage.getCartTotalPrice(), totalProductPrice);
		Assert.assertEquals(orderDetailsPage.getTotalProductPrice(), totalProductPrice);
		Assert.assertEquals(orderDetailsPage.getTotal_price_without_tax(), total_Price_After_Shipping_cost);
		Assert.assertEquals(orderDetailsPage.getTotal_price(), totalPriceWithTax);
	}
	
	//@Test(priority=2)
	public void proceedToOrderPaymentPage() {
		orderShippingPage = orderDetailsPage.navigateToOrderShippingPage();
		orderPaymentPage = orderShippingPage.proceedToOrderPaymentPage();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
