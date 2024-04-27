package com.framework.testlibrary;

import java.awt.Desktop;
import java.io.File;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fairplay.test.BaseTest;

public class ExtendReportManager extends BaseTest {

	@BeforeClass
	public void initialiseExtentReports() {
		ExtentSparkReporter sparkReporter_all = new ExtentSparkReporter("AllTests.html");
		sparkReporter_all.config().setReportName("All Tests report");
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter_all);
	}

	@BeforeMethod(alwaysRun = true)
	public void createExtentTest(Method m, ITestContext context) {
		extentTest = extentReports.createTest(m.getName());
		//extentTest = extentReports.createTest(context.getName());
	}

	@AfterMethod(alwaysRun = true)
	public void checkStatus(Method m, ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(m.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName());
		}
		//extentTest.assignCategory(m.getAnnotation(Test.class).groups());
	}

	@AfterSuite
	public void generateExtentReports() throws Exception {
		extentReports.flush();
//		String name = m.getClass().getName();
//		File testname = new File(name);
		Desktop.getDesktop().browse(new File("AllTests.html").toURI());
	}

}
