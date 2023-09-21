package com.tutorialsninja;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TutorialsNinja.qa.pages.Accountpage;
import com.TutorialsNinja.qa.pages.Loginpage;
import com.TutorialsNinja.qa.pages.homepage;
import com.tutorials.base.Base;
import com.tutorials.utils.Utilities;

public class LoginTest extends Base{
	public LoginTest() {
		super();
	}
	public WebDriver driver;
	Loginpage lpage;
	
	@BeforeMethod
	public void setup() {
		driver=initializebrowserandopenapplication(prop.getProperty("browser"));
		homepage hpage=new homepage(driver);
		hpage.clickonmyaccount();   //driver.findElement(By.xpath("//span[text()='My Account']")).click();
		lpage=hpage.clickonlogin();   	//driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1,dataProvider="supplytestdata")
	public void verifyloginwithvalidcredentials(String email,String password) {
		lpage.enteremailaddress(email);
		lpage.enterpassword(password);
		lpage.clickonlogin();
		Accountpage apage=new Accountpage(driver);
		Assert.assertTrue(apage.verifyloginstatus(),"edit your account is not displayed");
	}
	
	@DataProvider
	public Object[][] supplytestdata() {
		Object[][] data= Utilities.gettestdatafromexcel("login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyloginwithInvalidcredentials() {
		lpage.enteremailaddress(Utilities.generateemailwithtimestamp());
		lpage.enterpassword(dataprop.getProperty("invalidpassword"));
		lpage.clickonlogin();
		
		String actualwarning=lpage.retrievewarningtext();
		String expectedwarning=dataprop.getProperty("emailpasswordnomatchwarning");
		Assert.assertTrue(actualwarning.contains(expectedwarning),"Expected warning message not displayed");
	} 
	
	@Test(priority=3)
	public void verifyloginwithinvalidemailandvalidpassword() {
		lpage.enteremailaddress(Utilities.generateemailwithtimestamp());
		lpage.enterpassword(prop.getProperty("validpassword"));
		lpage.clickonlogin();
		
		String actualwarning=lpage.retrievewarningtext();
		String expectedwarning=dataprop.getProperty("emailpasswordnomatchwarning");
		Assert.assertTrue(actualwarning.contains(expectedwarning),"Expected warning message not displayed");
	}
	
	@Test(priority=4)
	public void verifyloginwithvalidemailandinvalidpassword() {
		lpage.enteremailaddress(prop.getProperty("validemail"));
		lpage.enterpassword(dataprop.getProperty("invalidpassword"));
		lpage.clickonlogin();
		String actualwarning=lpage.retrievewarningtext();
		String expectedwarning=dataprop.getProperty("emailpasswordnomatchwarning");
		Assert.assertTrue(actualwarning.contains(expectedwarning),"Expected warning message not displayed");
	}
	
	@Test(priority=5)
	public void verifyloginwithoutcredentials() {
		/*driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");*/
		lpage.clickonlogin();
		String actualwarning=lpage.retrievewarningtext();
		String expectedwarning=dataprop.getProperty("emailpasswordnomatchwarning");
		Assert.assertTrue(actualwarning.contains(expectedwarning),"Expected warning message not displayed");
	}

}
