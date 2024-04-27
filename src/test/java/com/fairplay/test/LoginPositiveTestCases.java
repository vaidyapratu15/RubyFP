package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.IOException;
import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class LoginPositiveTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test
	public void getBrowserLink() {
		extentTest.info("Link : " + driver.getCurrentUrl());
	}

	// @Test(priority = 1)
	public void verifyLoginPositiveTcThroughContryCode() throws IOException, InterruptedException {
		loginpage.contryCode("AE - United Arab Emirates (+971)");

		loginpage.setUsername(GetGsonFairplayTestData.getUserData().getUsername());
		loginpage.setPassword(GetGsonFairplayTestData.getUserData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		Thread.sleep(1000);
		String afterLogin = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);

		loginpage.walletAmount(driver);

		loginpage.profileClick();

		loginpage.logout();

		loginpage.downloadAppPopUp();

		Thread.sleep(1000);
		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();

	}

	@Test(priority = 2)
	public void verifyLoginPositiveTc() throws IOException, InterruptedException {
		loginpage.setUsername(GetGsonFairplayTestData.getData().getUsername());
		loginpage.setPassword(GetGsonFairplayTestData.getData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		loginpage.downloadAppPopUp();

		Thread.sleep(1000);
		String afterLogin = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);

		loginpage.walletAmount(driver);

		loginpage.profileClick();

		loginpage.logout();

		Thread.sleep(1000);

		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();

	}

	@AfterMethod
	public void onTestFailure(ITestResult result) throws InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			driver.navigate().refresh();
		}
	}
}
