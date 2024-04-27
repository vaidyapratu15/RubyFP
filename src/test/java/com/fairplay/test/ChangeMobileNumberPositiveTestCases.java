package com.fairplay.test;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class ChangeMobileNumberPositiveTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 1)
	public void login() throws IOException {
		loginpage.setUsername(GetGsonFairplayTestData.getData().getUsername());
		loginpage.setPassword(GetGsonFairplayTestData.getData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		loginpage.downloadAppPopUp();
		
		loginpage.walletAmount(driver);
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getAccessToken(driver);
		extentTest.info("Access Token Before Logout : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3)
	public void verifyChangeMobileNumberBtn() {
		loginpage.profileClick();
		changepass.clickOnChangeNumber();
	}

	@Test(priority = 4)
	public void verifyChangeMobileNumber() throws Exception {

		String number = changepass.getNewNumber();
		String actualMsg = changepass.setNewNumber(number);
		soft.assertEquals(actualMsg, null);

		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);

		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getVerifyOTP());

		String oldotp = changepass.getOldOtp();
		String actualMsg2 = changepass.setOldOtp(oldotp);
		soft.assertEquals(actualMsg2, "User Entered 6 Digit OTP..!!!");

		String newotp = changepass.getNewOtp();
		String actualMsg3 = changepass.setNewOtp(newotp);
		soft.assertEquals(actualMsg3, "User Entered 6 Digit OTP..!!!");

		Boolean actualBtn1 = changepass.submit();
		soft.assertEquals(actualBtn1, true);
		Thread.sleep(1000);
		String actualMsg4 = changepass.popup();
		soft.assertEquals(actualMsg4, GetGsonFairplayTestData.getConfigData().getMobileNoChanged());

		GetGsonFairplayTestData.writeJson(number, GetGsonFairplayTestData.getData().getPassword());
	}

	@Test(priority = 5)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 6)
	public void checkAccessTokenAfterLogout() {
		String afterLogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterLogout);
		soft.assertEquals(afterLogout, null);
		soft.assertAll();
	}

}
