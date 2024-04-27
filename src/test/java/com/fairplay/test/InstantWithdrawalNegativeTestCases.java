package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class InstantWithdrawalNegativeTestCases extends BaseTest {

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
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3)
	public void verifyInstantWithdraw() throws InterruptedException {
		loginpage.profileClick();
		instantwithdrawalpage.withdrawNow();
		instantwithdrawalpage.instantWithdraw();
	}

	@Test(priority = 4)
	public void verifyInstantWithdrawForWrongOTP() throws IOException, InterruptedException {
		String actual = instantwithdrawalpage.instantAddBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = instantwithdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg1 = instantwithdrawalpage.setOtp("123678");
		soft.assertEquals(actualMsg1, null);
		instantwithdrawalpage.setAccountNumber("123123121");
		instantwithdrawalpage.confirmAccountNumber("123123121");
		instantwithdrawalpage.setIfscCode("KKBK0000263");
		String name = instantwithdrawalpage.getAccountholderName();
		instantwithdrawalpage.setAccountHolderName(name);
		String actualMsg3 = instantwithdrawalpage.addAccount();
		soft.assertEquals(actualMsg3, GetGsonFairplayTestData.getConfigData().getOtpVerificationFail());
		Thread.sleep(1000);
	}

	@Test(priority = 5)
	public void verifyInstantWithdrawForOTPLessThan6Digit() throws IOException, InterruptedException {
		String actual = instantwithdrawalpage.instantAddBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = instantwithdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg1 = instantwithdrawalpage.setOtp("123");
		soft.assertEquals(actualMsg1, "OTP Must Be 6 Digits");
		instantwithdrawalpage.setAccountNumber("123123121");
		instantwithdrawalpage.confirmAccountNumber("123123121");
		instantwithdrawalpage.setIfscCode("KKBK0000263");
		String name = instantwithdrawalpage.getAccountholderName();
		instantwithdrawalpage.setAccountHolderName(name);
		String actualMsg3 = instantwithdrawalpage.addAccount();
		soft.assertEquals(actualMsg3, "Due to Invalid Cred Not Able To Create Account..!!!");
		Thread.sleep(1000);
	}

	@Test(priority = 6)
	public void verifyInstantWithdrawForOTPContainsOnlyLetters() throws IOException, InterruptedException {
		String actual = instantwithdrawalpage.instantAddBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = instantwithdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg1 = instantwithdrawalpage.setOtp("jdcbvbfbvdbvv");
		soft.assertEquals(actualMsg1, "Not Able To Enter OTP..!!!");
		instantwithdrawalpage.setAccountNumber("123123121");
		instantwithdrawalpage.confirmAccountNumber("123123121");
		instantwithdrawalpage.setIfscCode("KKBK0000263");
		String name = instantwithdrawalpage.getAccountholderName();
		instantwithdrawalpage.setAccountHolderName(name);
		String actualMsg3 = instantwithdrawalpage.addAccount();
		soft.assertEquals(actualMsg3, "Due to Invalid Cred Not Able To Create Account..!!!");
		Thread.sleep(1000);

	}

	@Test(priority = 7)
	public void verifyInstantWithdrawForInvalidCred() throws Exception {
		String actual = instantwithdrawalpage.instantAddBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualMsg = instantwithdrawalpage.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = instantwithdrawalpage.getOtp();
		String actualMsg1 = instantwithdrawalpage.setOtp(otp);
		soft.assertEquals(actualMsg1, null);
		instantwithdrawalpage.setAccountNumber("1231231");
		instantwithdrawalpage.confirmAccountNumber("123123121");
		instantwithdrawalpage.setIfscCode("123456789");
		String name = instantwithdrawalpage.getAccountholderName();
		instantwithdrawalpage.setAccountHolderName(name);
		instantwithdrawalpage.addAccount();
		Thread.sleep(1000);
	}

	@Test(priority = 8)
	public void verifyInstantWithdrawForInvalidIFSC() throws Exception {
		String actual = instantwithdrawalpage.instantAddBankDetails();
		soft.assertEquals(actual, "AddNow Is Clickable..!!!");
		String actualData = instantwithdrawalpage.otpPopup();
		soft.assertEquals(actualData, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = instantwithdrawalpage.getOtp();
		String actualMsg1 = instantwithdrawalpage.setOtp(otp);
		org.testng.Assert.assertEquals(actualMsg1, null);
		instantwithdrawalpage.setAccountNumber("123123121");
		instantwithdrawalpage.confirmAccountNumber("123123121");
		instantwithdrawalpage.setIfscCode("KOBK0000266");
		String name = instantwithdrawalpage.getAccountholderName();
		instantwithdrawalpage.setAccountHolderName(name);
		instantwithdrawalpage.addAccount();
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
	}

}
