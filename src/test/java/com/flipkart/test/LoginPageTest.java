package com.flipkart.test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.flipkart.util.TestUtil;
import com.flipkart.util.XLSReader;

//@Listeners(TestListener.class)
public class LoginPageTest extends TestBase {
	
	WebDriver driver;
	LoginPage loginPage;
	AccountsPage accountsPage;
	String sheetName="LoginPageTest"; 
	
	public LoginPageTest(){
		super();
	}
	@BeforeMethod
	public void setUp(){
		driver = getDriverInstance();
		loginPage = new LoginPage(driver);	
		
	}
	
	
	//@Test(priority=0)
	public void findBrokenLinksAndImages() throws Exception{
		List <WebElement> linkList = driver.findElements(By.tagName("a"));
		for(WebElement link: linkList) {
			try {
				TestUtil.FindBrokenLinks(link); 
			}catch(java.lang.ClassCastException c) {
				
			}
		}
		System.out.println("Found Total: "+ TestUtil.totalBrokenLinksCount +" Broken links in the WebPage");
		
		List <WebElement> imageList = driver.findElements(By.tagName("img"));
		for(WebElement image: imageList) {
			try {
				TestUtil.FindBrokenLinks(image); 
			}catch(java.lang.ClassCastException c) {
				
			}
		}
		System.out.println("Found Total: "+TestUtil.totalBrokenLinksCount +" BrokenImages in the WebPage.");
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, "Login - My Store");
	}
	
	@Test(priority=2)
	public void logoValidationTest(){
		boolean flag = loginPage.validateLogoImage();
		Assert.assertTrue(flag);
	}

	@DataProvider
	public Object[][] getLoginTestData() {
		return XLSReader.getExcelTestData(sheetName);
	}
	
	@Test(dataProvider = "getLoginTestData", priority=3)
	public void loginTest(String userName,String password){
		accountsPage = loginPage.loginToApplication(userName, password);
		if(userName.equalsIgnoreCase("dineshrenganathan@solutionchamps.com")) {
			Assert.assertEquals(accountsPage.getAccountsPageTitle(), "My account - My Store");
		}
		else {
			Assert.assertEquals(accountsPage.getAccountsPageTitle(), "My account - My Store");
			//Assert.assertEquals(accountsPage.getAccountsPageTitle(), "Login - My Store");
		}
		
	}
	
		
	@AfterMethod
	public void tearDown(){
	  driver.quit();
	}
	
	
}
