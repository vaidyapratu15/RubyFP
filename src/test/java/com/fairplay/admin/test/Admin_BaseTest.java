package com.fairplay.admin.test;

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
import com.fairplay.admin.AdminLogin;
import com.fairplay.admin.AdminLogout;
import com.fairplay.admin.AdminPanelTelegramSetting;
import com.fairplay.admin.AdminPanelWhatsAppSetting;
import com.fairplay.admin.Admin_BaseClass;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Admin_BaseTest extends Admin_BaseClass{
	

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
		driver.get(GetGsonFairplayTestData.getConfigData().getAdminPanelUrl());
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("FpAdmin.html");
		sparkReporter_all.config().setReportName("FpAdmin report");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all);
	}

	@BeforeClass
	public void pageObjects() {
        adminlogin = new AdminLogin(driver);
        telegram = new AdminPanelTelegramSetting(driver);
        whatsApp = new AdminPanelWhatsAppSetting(driver);
        adminlogout = new AdminLogout(driver);
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
		Desktop.getDesktop().browse(new File("FpAdmin.html").toURI());
	}
//	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}
