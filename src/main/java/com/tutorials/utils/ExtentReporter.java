package com.tutorials.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports generateextentreport() {
		ExtentReports exreports=new ExtentReports();
		File extentreportfile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentreports.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentreportfile);
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("Tutorials Ninja Test Automation Results");
		sparkreporter.config().setDocumentTitle("TN reports");
		exreports.attachReporter(sparkreporter);
		
		Properties configprop=new Properties();
		File configpropfile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\TutorialsNinja\\qa\\config\\config.properties");
		try {
		FileInputStream fis=new FileInputStream(configpropfile);
		configprop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		exreports.setSystemInfo("App url", configprop.getProperty("url"));
		exreports.setSystemInfo("Browser name", configprop.getProperty("browser"));
		exreports.setSystemInfo("email", configprop.getProperty("validemail"));
		exreports.setSystemInfo("operaating system", System.getProperty("os.name"));
		exreports.setSystemInfo("username",System.getProperty("user.name") );
		exreports.setSystemInfo("java version", System.getProperty("java.version"));
		
		return exreports;
	}
}
