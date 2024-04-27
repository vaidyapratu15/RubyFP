package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

import java.io.IOException;

public class EditProfileNegativeTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();
	
	@Test(priority = 1)
	public void login() throws IOException {
		loginpage.setUsername(GetGsonFairplayTestData.getData().getUsername());
		loginpage.setPassword(GetGsonFairplayTestData.getData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);
		
		loginpage.downloadAppPopUp();
		
		loginpage.walletAmount(driver);
		soft.assertAll();
	}

	@Test(priority = 2)
	public void checkAccessTokenAfterLogin() {
		String afterLogin = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3)
	public void editProfileForFirstOTPLessThan6Digit() throws IOException, InterruptedException {
		loginpage.profileClick();
		editprofile.editProfile();
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg1 = editprofile.setOtp("65");
		soft.assertEquals(actualMsg1, "OTP must be 6 digits");
	}

	@Test(priority = 4)
	public void editProfileForFirstWrongOTP() throws IOException, InterruptedException {
		// loginpage.profileClick();
		// editprofile.editProfile();
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg1 = editprofile.setOtp("655454654545");
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getOtpVerificationFail());
	}

	@Test(priority = 5)
	public void editProfileForFirstOTPContainsLetters() throws IOException, InterruptedException {
		// loginpage.profileClick();
		// editprofile.editProfile();
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg1 = editprofile.setOtp("df");
		soft.assertEquals(actualMsg1, "Not Able To Enter First OTP..!!!");
	}

	@Test(priority = 6)
	public void editProfileForAgeValidation() throws Exception {
		//loginpage.profileClick();
		//editprofile.editProfile();
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		System.out.println(otp);
		editprofile.setOtp(otp);
		editprofile.editDOB("2021", "OCT", "1");
		String actualMsg1 = editprofile.update();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getAgePopup());
	}

	@Test(priority = 7)
	public void editProfileForSecondOTPLessThan6Digit() throws Exception {
		//loginpage.profileClick();
		//editprofile.editProfile();
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		editprofile.setOtp(otp);
		editprofile.editName("Testing");
		String actualMsg1 = editprofile.update();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg2 = editprofile.setSecondOtp("76");
		soft.assertEquals(actualMsg2, "OTP must be 6 digits");
	}

	@Test(priority = 8)
	public void editProfileForSecondOTPGreaterThan6Digit() throws Exception {
		// loginpage.profileClick();
		// editprofile.editProfile();
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		editprofile.setOtp(otp);
		editprofile.editName("Testing");
		String actualMsg1 = editprofile.update();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg2 = editprofile.setSecondOtp("6545433243");
		soft.assertEquals(actualMsg2, "Not Able To Enter Second OTP..!!!");
	}

	@Test(priority = 9)
	public void editProfileForSecondOTPContainsLetters() throws Exception {
		//loginpage.profileClick();
		//editprofile.editProfile();
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		editprofile.setOtp(otp);
		editprofile.editName("Testing");
		String actualMsg1 = editprofile.update();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String actualMsg2 = editprofile.setSecondOtp("fgcgvcvbb");
		soft.assertEquals(actualMsg2, "Not Able To Enter Second OTP..!!!");
	}

	@Test(priority = 10)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 11)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

}
