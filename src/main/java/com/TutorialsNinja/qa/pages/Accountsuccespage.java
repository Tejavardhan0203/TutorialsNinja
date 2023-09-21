package com.TutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Accountsuccespage {

	WebDriver driver;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement accountsuccesheading;
	
	public Accountsuccespage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String retrieveaccountsuccestext() {
		String successtext=accountsuccesheading.getText();
		return successtext;
	}
	
}
