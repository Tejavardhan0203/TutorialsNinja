package com.tutorials.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorials.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	public Base() {
		
		prop= new Properties();
		File propfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialsNinja\\qa\\config\\config.properties");
		
		dataprop=new Properties();
		File datapropfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialsNinja\\testdata\\testdata.properties");
		try {
		FileInputStream fis2=new FileInputStream(datapropfile);
		dataprop.load(fis2);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		try {
		FileInputStream fis=new FileInputStream(propfile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	public WebDriver initializebrowserandopenapplication(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}
}
