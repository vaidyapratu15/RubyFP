package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class EditProfilePositiveTestCase extends BaseTest{

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
	public void userInfoBeforeEdit() {
		loginpage.profileClick();
		editprofile.editProfile();
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();
	}

	@Test(priority = 4)
	public void verifyFirstOtp() throws Exception {
		editprofile.editProfileBtn();
		String actualMsg = editprofile.otpPopup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		String actualMsg1 = editprofile.setOtp(otp);
		soft.assertEquals(actualMsg1, null);
	}

	@Test(priority = 5)
	public void editUserInformation() throws InterruptedException {
		extentTest.info("Edit Profile Started");
		editprofile.editDOB("1991", "OCT", "17");
		Thread.sleep(1000);
		editprofile.editName("Testing");
		Thread.sleep(1000);
		editprofile.Gender("Male");
		Thread.sleep(1000);
		extentTest.info("Profile Edited Successfully");
	}

	@Test(priority = 6, dependsOnMethods = "editUserInformation")
	public void verifySecondOtp() throws Exception {
		String actualMsg = editprofile.update();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = editprofile.getOtp();
		String actualMsg1 = editprofile.setSecondOtp(otp);
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getProfileUpdate());
	}

	@Test(priority = 7, dependsOnMethods = "verifySecondOtp")
	public void userInfoAfterEdit() {
		editprofile.scrollTillEditProfileBtn();
		editprofile.getUserInfo();		
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

}
