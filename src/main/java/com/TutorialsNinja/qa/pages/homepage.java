package com.TutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homepage {
	WebDriver driver;
	//objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myaccountdropdown;
	
	@FindBy(linkText="Login")
	private WebElement loginoption;
	
	@FindBy(linkText="Register")
	private WebElement registeroption;
	
	@FindBy(name="search")
	private WebElement searchproduct;
	
	@FindBy(xpath="//div[@id='search']/descendant::button")
	private WebElement searchbutton;
	
	public homepage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this); //this=homepage.class
	}
	
	//actions
	public void clickonmyaccount() {
		myaccountdropdown.click();
	}
	
	public Loginpage clickonlogin() {
		loginoption.click();
		return new Loginpage(driver);
	}
	
	public Registerpage clickonregister() {
		registeroption.click();
		return new Registerpage(driver);
	}
	
	public void searchproduct(String searchtext) {
		searchproduct.sendKeys(searchtext);
	}
	
	public void clickonsearch() {
		searchbutton.click();
	}
	
	
}
