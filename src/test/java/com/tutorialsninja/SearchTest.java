package com.tutorialsninja;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.TutorialsNinja.qa.pages.homepage;
import com.tutorials.base.Base;

public class SearchTest extends Base{

	public SearchTest() {
		super();
	}
	public WebDriver driver;
	@BeforeMethod
	public void Setup() {
		driver=initializebrowserandopenapplication(prop.getProperty("browser"));
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifysearchwithvalidproduct() {
		homepage hpage=new homepage(driver);
		hpage.searchproduct(dataprop.getProperty("validproduct"));
		hpage.clickonsearch();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}
	
	@Test(priority=2)
	public void verifysearchwithinvalidproduct() {
		homepage hpage=new homepage(driver);
		hpage.searchproduct(dataprop.getProperty("invalidproduct"));
		hpage.clickonsearch();
		String actualmessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualmessage, dataprop.getProperty("Noproductsearchresult"),"No product message is not displayed");
	}
	
	@Test(priority=3)
	public void verifysearchwithooutanyproduct() {
		homepage hpage=new homepage(driver);
		hpage.clickonsearch();
		String actualmessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualmessage, dataprop.getProperty("Noproductsearchresult"),"No product message is not displayed");
	}
	
}
