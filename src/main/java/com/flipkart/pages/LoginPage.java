package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.flipkart.base.TestBase;

public class LoginPage  {
	WebDriver driver;
	
	//Locators
	By userNameField = By.id("email");
    By passwordField = By.id("passwd");
    By loginButton =By.id("SubmitLogin");
    By logoImage = By.cssSelector("img.logo.img-responsive");
    
    
    //Constructor
    public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
  //Set user name in textbox

    public void setUserName(String strUserName){

        driver.findElement(userNameField).sendKeys(strUserName);

    }
    
    
  //Set password in password textbox

    public void setPassword(String strPassword){

         driver.findElement(passwordField).sendKeys(strPassword);

    }
    
  //Click on login button

    public void clickLogin(){

            driver.findElement(loginButton).click();

    }

    	public String getLoginPageTitle(){

        return driver.getTitle();

       }
    	
    	public boolean validateLogoImage() {
    		return driver.findElement(logoImage).isDisplayed();
    	}

    	public AccountsPage loginToApplication(String strUserName,String strPasword){

            //Fill user name

            this.setUserName(strUserName);

            //Fill password

            this.setPassword(strPasword);

            //Click Login button

            this.clickLogin();
			return new AccountsPage(driver);        
        }
}


