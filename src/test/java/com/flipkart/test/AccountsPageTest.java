package com.flipkart.test;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.flipkart.base.TestBase;
import com.flipkart.pages.AccountsPage;
import com.flipkart.pages.LoginPage;
import com.flipkart.util.XLSReader;
//@Listeners(TestListener.class)
public class AccountsPageTest extends TestBase {

	WebDriver driver;
	
	LoginPage loginPage;
	AccountsPage accountsPage;
	
	
	public AccountsPageTest(){
		super();
	}
	@BeforeMethod()
	public void setUp(){
		driver = getDriverInstance();
		loginPage = new LoginPage(driver);	
		accountsPage = loginPage.loginToApplication(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getAccountsTestData(){
		return XLSReader.getExcelTestData("AccountsPageTest");
	}
	
	
	@Test(priority=1)
	public void verifyHomePageTitle() {
		String title = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(title, "My account - My Store");
	}

	
	@Test(dataProvider ="getAccountsTestData", priority=2)
	public void verifyTabItemsInAccountsPage(String TabName_1, String TabName_2, String TabName_3, String TabName_4, String TabName_5) {
		
		System.out.println(accountsPage.totalTabItemsCount());
		Assert.assertEquals(accountsPage.totalTabItemsCount(), 4);
		Assert.assertTrue(accountsPage.verifyTabItem(TabName_1));
		Assert.assertTrue(accountsPage.verifyTabItem(TabName_2));
		Assert.assertTrue(accountsPage.verifyTabItem(TabName_3));
		Assert.assertTrue(accountsPage.verifyTabItem(TabName_4));
		Assert.assertTrue(accountsPage.verifyTabWishList(TabName_5));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
