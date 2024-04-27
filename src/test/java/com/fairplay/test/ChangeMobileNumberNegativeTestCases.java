package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class ChangeMobileNumberNegativeTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();
	
	@Test(priority = 1)
	public void login() throws IOException {
		loginpage.setUsername(GetGsonFairplayTestData.getData().getUsername());
		loginpage.setPassword(GetGsonFairplayTestData.getData().getPassword());
		
		Boolean actualBtn = loginpage.login();
		soft.assertEquals(actualBtn, true);

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
	public void changeMobileNumberForInvalidMobileNumber() throws InterruptedException, IOException {
		String actualMsg = changepass.setNewNumber("7249");
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getInvalidMobileNo());
		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, false);
		Thread.sleep(1000);
	}

	@Test(priority = 5)
	public void changeMobileNumberForExistingNumber() throws InterruptedException, IOException {
		String actualMsg = changepass.setNewNumber(GetGsonFairplayTestData.getData().getUsername());
		soft.assertEquals(actualMsg, null);
		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);
		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getExistingNumber());
	}
	
	
	@Test(priority = 6)
	public void changeMobileNumberForIncorrectOTP1() throws InterruptedException, IOException {
		String number = changepass.getNewNumber();
		String actualMsg = changepass.setNewNumber(number);
		soft.assertEquals(actualMsg, null);
		
		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);
		
		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getVerifyOTP());

		String actualMsg2 = changepass.setOldOtp("456556456565");
		soft.assertEquals(actualMsg2, "");
		
		String actualMsg3 = changepass.setNewOtp("33454545");
		soft.assertEquals(actualMsg3, "");

		Boolean actualBtn1 = changepass.submit();
		soft.assertEquals(actualBtn1, true); 
		Thread.sleep(1000);
		String actualMsg4 = changepass.popup();
		soft.assertEquals(actualMsg4, GetGsonFairplayTestData.getConfigData().getInvalidOTP());
	}

	@Test(priority = 7)
	public void changeMobileNumberForOTPIncludingLetters() throws InterruptedException, IOException {
		String number = changepass.getNewNumber();
		String actualMsg = changepass.setNewNumber(number);
		soft.assertEquals(actualMsg, null);
		
		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);
		
		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getVerifyOTP());

		String actualMsg2 = changepass.setOldOtp("cxdbcvbvc");
		soft.assertEquals(actualMsg2, "Not Able To Enter Old OTP..!!!");
		
		String actualMsg3 = changepass.setNewOtp("bxc");
		soft.assertEquals(actualMsg3, "Not Able To Enter New OTP..!!!");

		Boolean actualBtn1 = changepass.submit();
		soft.assertEquals(actualBtn1, false);
		Thread.sleep(1000);
	}

	
	@Test(priority = 8)
	public void changePwdForInvalidOTP() throws InterruptedException, IOException {
		String number = changepass.getNewNumber();
		String actualMsg = changepass.setNewNumber(number);
		soft.assertEquals(actualMsg, null);
		
		Boolean actualBtn = changepass.clickNext();
		soft.assertEquals(actualBtn, true);
		Thread.sleep(1000);
		
		String actualMsg1 = changepass.popup();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getVerifyOTP());

		String actualMsg2 = changepass.setOldOtp("123");
		soft.assertEquals(actualMsg2, "OTP must be 6 digits");
		Thread.sleep(1000);
		String actualMsg3 = changepass.setNewOtp("6565");
		soft.assertEquals(actualMsg3, "OTP must be 6 digits");

		Boolean actualBtn1 = changepass.submit();
		soft.assertEquals(actualBtn1, false);
		Thread.sleep(1000);
	}

	
	//@Test(priority = 8)
	public void verifyChangeNumberAE() throws Exception {
		changepass.contryCode("+971");
		String number = changepass.getNewNumber();
		changepass.setNewNumber(number);
		changepass.clickNext();
		Thread.sleep(1000);
		changepass.popup();

		String oldotp = changepass.getOldOtp();
		changepass.setOldOtp(oldotp);

		String newotp = changepass.getNewOtp();
		changepass.setNewOtp(newotp);

		changepass.submit();
		Thread.sleep(1000);
		changepass.popup();
	}
	
	//@Test(priority = 9)
	public void verifyChangeNumberIN() throws Exception {
		String number = changepass.getNewNumber();
		changepass.setNewNumber(number);
		changepass.clickNext();
		Thread.sleep(1000);
		changepass.popup();

		String oldotp = changepass.getOldOtp();
		changepass.setOldOtp(oldotp);

		String newotp = changepass.getNewOtp();
		changepass.setNewOtp(newotp);
		changepass.submit();
		Thread.sleep(1000);
		changepass.popup();
	}
	
	@Test(priority = 10)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 11)
	public void checkAccessTokenAfterLogout() {
		String afterLogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token Before Logout : " + afterLogout);
		soft.assertEquals(afterLogout, null);
		soft.assertAll();
		
	}

}
