package com.TutorialsNinja.qa.Listners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorials.utils.ExtentReporter;

public class MyListeners implements ITestListener {

	ExtentReports exreport;
	ExtentTest extenttest;
	@Override
	public void onStart(ITestContext context) {
		exreport=ExtentReporter.generateextentreport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testname=result.getName();
		extenttest = exreport.createTest(testname);
		extenttest.log(Status.INFO,testname+"test started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname=result.getName();
		extenttest.log(Status.PASS,testname+"test successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname=result.getName();
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"\\Screenshots\\"+testname+".png");
		try {
			FileUtils.copyFile(src, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL,testname+"test got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname=result.getName();
		extenttest.log(Status.INFO, result.getThrowable());
		extenttest.log(Status.FAIL,testname+"test got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		exreport.flush();
	}

}
