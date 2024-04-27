package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class WithdrawalNegativeTestCases extends BaseTest {

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
		org.testng.Assert.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3)
	public void verifyWithdraw() throws InterruptedException {
		loginpage.profileClick();
		withdrawalpage.withdrawNow();
	}

	@Test(priority = 4)
	public void verifyWithdrawForWrongOTP() throws IOException, InterruptedException {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = withdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg1 = withdrawalpage.setOtp("123678");
		soft.assertEquals(actualMsg1, null);
		withdrawalpage.setAccountNumber("123123121");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("KKBK0000263");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		String actualMsg3 = withdrawalpage.addAccount();
		soft.assertEquals(actualMsg3, GetGsonFairplayTestData.getConfigData().getOtpVerificationFail());
		Thread.sleep(1000);
		
	}

	@Test(priority = 5)
	public void verifyWithdrawForOTPLessThan6Digit() throws IOException, InterruptedException {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = withdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg1 = withdrawalpage.setOtp("123");
		soft.assertEquals(actualMsg1, "OTP Must Be 6 Digits");
		withdrawalpage.setAccountNumber("123123121");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("KKBK0000263");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		String actualMsg3 = withdrawalpage.addAccount();
		soft.assertEquals(actualMsg3, "Due to Invalid Cred Not Able To Create Account..!!!");
		Thread.sleep(1000);
		
	}

	@Test(priority = 6)
	public void verifyWithdrawForOTPContainsOnlyLetters() throws IOException, InterruptedException {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = withdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg1 = withdrawalpage.setOtp("jdcbvbfbvdbvv");
		soft.assertEquals(actualMsg1, "Not Able To Enter OTP..!!!");
		withdrawalpage.setAccountNumber("123123121");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("KKBK0000263");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		String actualMsg3 = withdrawalpage.addAccount();
		soft.assertEquals(actualMsg3, "Due to Invalid Cred Not Able To Create Account..!!!");
		Thread.sleep(1000);
	
	}

	@Test(priority = 7)
	public void verifyWithdrawForInvalidCred() throws Exception {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = withdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = withdrawalpage.getOtp();
		String actualMsg1 = withdrawalpage.setOtp(otp);
		soft.assertEquals(actualMsg1, null);
		withdrawalpage.setAccountNumber("1231231");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("123456789");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		withdrawalpage.addAccount();
		Thread.sleep(1000);
		
	}

	@Test(priority = 8)
	public void verifyWithdrawForInvalidIFSC() throws Exception {
		String actual = withdrawalpage.addBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualData = withdrawalpage.otpPopup();
		soft.assertEquals(actualData, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = withdrawalpage.getOtp();
		String actualMsg1 = withdrawalpage.setOtp(otp);
		org.testng.Assert.assertEquals(actualMsg1, null);
		withdrawalpage.setAccountNumber("123123121");
		withdrawalpage.confirmAccountNumber("123123121");
		withdrawalpage.setIfscCode("KOBK0000266");
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		withdrawalpage.addAccount();
		Thread.sleep(1000);
	}

	
	@Test(priority = 9)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 10)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}
}
