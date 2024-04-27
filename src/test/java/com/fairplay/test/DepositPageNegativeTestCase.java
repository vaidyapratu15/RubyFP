package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.AWTException;
import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class DepositPageNegativeTestCase extends BaseTest{

	SoftAssert soft = new SoftAssert();
	
	@Test(priority = 1)
	public void login() throws IOException {
		loginpage.setUsername(GetGsonFairplayTestData.getData().getUsername());
		loginpage.setPassword(GetGsonFairplayTestData.getData().getPassword());
		
		Boolean actualbtn = loginpage.login();
		org.testng.Assert.assertEquals(actualbtn, true);

		loginpage.downloadAppPopUp();
		
		loginpage.walletAmount(driver);
	}
	
	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3)
	public void verifyDeposit() throws InterruptedException {
		loginpage.profileClick();
		deposit.depositNow();
	}

	@Test(priority = 4)
	public void verifyNegativeDepositThroughUPI() throws InterruptedException {
		deposit.depositThroughUPI("100", "sdfghjuio876tre");
	}

	@Test(priority = 5)
	public void verifyNegativeDepositThroughNetBanking() throws InterruptedException {
		deposit.depositThroughNetBanking("101", "hgfdsfghjk");
	}

	@Test(priority = 6)
	public void verifyNegativeDepositThroughBankDeposit() throws InterruptedException, IOException, AWTException {
		deposit.depositThroughBankDeposit("101");
	}

	@Test(priority = 7)
	public void verifyNegativeDepositThroughAstropay() throws InterruptedException {
		deposit.depositThroughAstropay("100", "876543567iuyghfdsx");
	}
	
	@Test(priority = 8)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 9)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

	
//	@AfterMethod
//	public void onTestFailure(ITestResult result) throws InterruptedException {
//		if (result.getStatus() == ITestResult.FAILURE) {
//			String testcasename = result.getMethod().getMethodName();
//			Utilities.captureScreenshot(driver, testcasename + ".jpg");
//		}
//	}
}
