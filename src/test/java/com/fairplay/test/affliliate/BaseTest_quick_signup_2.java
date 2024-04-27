package com.fairplay.test.affliliate;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;
import com.fairplay.main.BaseClass;
import com.fairplay.main.FairplayChangeMobileNumberPage;
import com.fairplay.main.FairplayDepositPage;
import com.fairplay.main.FairplayEditProfile;
import com.fairplay.main.FairplayForgotPassword;
import com.fairplay.main.FairplayInstantWithdraw;
import com.fairplay.main.FairplayLoginPage;
import com.fairplay.main.FairplayProfileSectionPage;
import com.fairplay.main.FairplayResetPassword;
import com.fairplay.main.FairplaySignupPage;
import com.fairplay.main.FairplayWithdraw;
import com.fairplay.main.Turnover;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest_quick_signup_2 extends BaseClass{

	@SuppressWarnings("deprecation")
	@BeforeSuite
	public void initializeBrowser() throws IOException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--remote-allow-origins=*");
		option.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(option);
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     	driver.get(GetGsonFairplayTestData.getConfigData().getQuick_signup_2AffUrl());	
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("quick_signup_2.html");
		sparkReporter_all.config().setReportName("quick_signup_2 report");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all);
		
	}

	@BeforeClass
	public void pageObjects() {
		loginpage = new FairplayLoginPage(driver);
		signuppage = new FairplaySignupPage(driver);
		resetpage = new FairplayResetPassword(driver);
		editprofile = new FairplayEditProfile(driver);
		withdrawalpage = new FairplayWithdraw(driver);
		instantwithdrawalpage = new FairplayInstantWithdraw(driver);
		profilesection = new FairplayProfileSectionPage(driver);
		forgotpassword = new FairplayForgotPassword(driver);
		deposit = new FairplayDepositPage(driver);
		changepass = new FairplayChangeMobileNumberPage(driver);	
		turnover = new Turnover(driver);
	}
	
	@BeforeMethod
	public void createExtentTest(Method m) {
		extentTest = extentReports.createTest(m.getName());
	}

	@AfterMethod(alwaysRun = true)
	public void checkStatus(Method m, ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(m.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName());
		}
	}

	@AfterSuite
	public void generateExtentReports() throws Exception {
		extentReports.flush();
		Desktop.getDesktop().browse(new File("quick_signup_2.html").toURI());
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}


	
}
