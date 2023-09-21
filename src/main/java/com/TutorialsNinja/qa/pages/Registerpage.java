package com.TutorialsNinja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registerpage {

	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstnamefield;
	
	@FindBy(id="input-lastname")
	private WebElement lastnamefield;
	
	@FindBy(id="input-email")
	private WebElement emailfield;
	
	@FindBy(id="input-telephone")
	private WebElement telephonefield;
	
	@FindBy(id="input-password")
	private WebElement passwordfield;
	
	@FindBy(id="input-confirm")
	private WebElement passwordconfirmfield;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsletterfield;
	
	@FindBy(name="agree")
	private WebElement privacypolicyfield;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continuebutton;
	
	@FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
	private WebElement accountcreationtextelement;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateemailwarning;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement privacypolicywarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstnamewarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastnamewarning;

	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailwarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephonewarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordwarning;
	
	public Registerpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String firstnamewarning() {
		String firstnamewarningtext=firstnamewarning.getText();
		return firstnamewarningtext;
	}
	
	public String lastnamewarning() {
		String lastnamewarningtext=lastnamewarning.getText();
		return lastnamewarningtext;
	}
	
	public String emailwarning() {
		String emailwarningtext=emailwarning.getText();
		return emailwarningtext;
	}
	
	public String telephonewarning() {
		String telephonewarningtext=telephonewarning.getText();
		return telephonewarningtext;
	}
	
	public String passwordwarning() {
		String passwordwarningtext=passwordwarning.getText();
		return passwordwarningtext;
	}
	
	
	
	public String privacypolicywarningtext() {
		String privacytext=privacypolicywarning.getText();
		return privacytext;
	}
	
	public void enterfirstname(String firstnametext) {
		firstnamefield.sendKeys(firstnametext);
	}
	public void enterlastname(String lastnametext) {
		lastnamefield.sendKeys(lastnametext);
	}
	public void enteremail(String emailtext) {
		emailfield.sendKeys(emailtext);
	}
	public void entertelephone(String telephonetext) {
		telephonefield.sendKeys(telephonetext);
	}
	public void enterpassword(String passwordtext) {
		passwordfield.sendKeys(passwordtext);
	}
	public void enterpasswordconfirm(String passwordconfirmtext) {
		passwordconfirmfield.sendKeys(passwordconfirmtext);
	}
	public void selectnewsletter() {
		newsletterfield.click();
	}
	public void selectprivacypolicy() {
		privacypolicyfield.click();
	}
	public void clickoncontinuebutton() {
		continuebutton.click();
	}
	public String duplicatemailwarningtext() {
		String duplicatetext=duplicateemailwarning.getText();
		return duplicatetext;
	}
	
	
	
}
