package com.TutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	
	WebDriver driver;
	
	@FindBy(id="input-email")
	private WebElement emailfield;
	
	@FindBy(id="input-password")
	private WebElement passwordfield;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginbutton;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement emailpasswordnotmatchwarning;
	
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enteremailaddress(String emailtext) {
		emailfield.sendKeys(emailtext);
	}
	public void enterpassword(String passwordtext) {
		passwordfield.sendKeys(passwordtext);
	}
	public void clickonlogin() {
		loginbutton.click();
	}
	
	public String retrievewarningtext() {
		String warningtext=emailpasswordnotmatchwarning.getText();
		return warningtext;
	}
	
	
	
}
