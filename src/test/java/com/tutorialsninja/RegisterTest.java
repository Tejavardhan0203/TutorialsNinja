package com.tutorialsninja;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TutorialsNinja.qa.pages.Registerpage;
import com.TutorialsNinja.qa.pages.homepage;
import com.tutorials.base.Base;
import com.tutorials.utils.Utilities;

public class RegisterTest extends Base{
	public RegisterTest() {
		super();
	}
	public WebDriver driver;
	Registerpage rpage;
	@BeforeMethod
	public void setup() {
		driver=initializebrowserandopenapplication(prop.getProperty("browser"));
		homepage hpage=new homepage(driver);
		hpage.clickonmyaccount();
		rpage = hpage.clickonregister();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	@Test(priority=1)
	public void verifregisteringwithmandatoryfields() {
		rpage.enterfirstname(dataprop.getProperty("firstname"));
		rpage.enterlastname(dataprop.getProperty("lastname"));
		rpage.enteremail(Utilities.generateemailwithtimestamp());
		rpage.entertelephone(dataprop.getProperty("telephone"));
		rpage.enterpassword(prop.getProperty("validpassword"));
		rpage.enterpasswordconfirm(prop.getProperty("validpassword"));
		rpage.selectprivacypolicy();
		rpage.clickoncontinuebutton();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifyregisteringbyprovidingallfields() {
		rpage.enterfirstname(dataprop.getProperty("firstname"));
		rpage.enterlastname(dataprop.getProperty("lastname"));
		rpage.enteremail(Utilities.generateemailwithtimestamp());
		rpage.entertelephone(dataprop.getProperty("telephone"));
		rpage.enterpassword(prop.getProperty("validpassword"));
		rpage.enterpasswordconfirm(prop.getProperty("validpassword"));
		rpage.selectnewsletter();
		rpage.selectprivacypolicy();
		rpage.clickoncontinuebutton();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).isDisplayed());
	}
	
	@Test(priority=3)
	public void verifyregisteringwithexistingemail() {
		rpage.enterfirstname(dataprop.getProperty("firstname"));
		rpage.enterlastname(dataprop.getProperty("lastname"));
		rpage.enteremail(prop.getProperty("validemail"));
		rpage.entertelephone(dataprop.getProperty("telephone"));
		rpage.enterpassword(prop.getProperty("validpassword"));
		rpage.enterpasswordconfirm(prop.getProperty("validpassword"));
		rpage.selectnewsletter();
		rpage.selectprivacypolicy();
		rpage.clickoncontinuebutton();
		
		String actualwarning=rpage.duplicatemailwarningtext();
		Assert.assertTrue(actualwarning.contains(dataprop.getProperty("duplicatemailwarning")),"warning message is not displayed");
	}

	@Test(priority=4)
	public void verifyregisteringwithoutfillinganyfields() {
		rpage.clickoncontinuebutton();
		
		String actualprivacypolicy=rpage.privacypolicywarningtext();
		Assert.assertTrue(actualprivacypolicy.contains(dataprop.getProperty("privacypolicywarning")),"warning message is not displayed");
		
		String actualfirstnamewarning=rpage.firstnamewarning();
		Assert.assertTrue(actualfirstnamewarning.contains(dataprop.getProperty("firstnamewarning")),"firstname warning message is not displayed");
		
		String actuallastnamewarning=rpage.lastnamewarning();
		Assert.assertTrue(actuallastnamewarning.contains(dataprop.getProperty("lastnamewarning")),"lastname warning message is not displayed");
		
		String actualemailwarning=rpage.emailwarning();
		Assert.assertEquals(actualemailwarning,dataprop.getProperty("emailwarning"),"email warning message is not displayed");
		
		String telephonewarning=rpage.telephonewarning();
		Assert.assertEquals(telephonewarning,dataprop.getProperty("telephonewarning"),"telephone warning message is not displayed");
		
		String passwordwarning=rpage.passwordwarning();
		Assert.assertEquals(passwordwarning,dataprop.getProperty("passwordwarning"),"password warning message is not displayed");
		
		
	}
	
}
