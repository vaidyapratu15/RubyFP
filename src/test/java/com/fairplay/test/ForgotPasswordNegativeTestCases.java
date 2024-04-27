package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

import java.io.IOException;

public class ForgotPasswordNegativeTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();
	
	@Test(priority = 1)
	public void verifyForgotPwdForWrongNumber() throws IOException, InterruptedException {
		forgotpassword.forgotPassword();
		Thread.sleep(1000);
		forgotpassword.setUsernumber("987654325678976543");

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getUserNotFound());

		driver.navigate().refresh();
		Thread.sleep(2000);
		
	}

	@Test(priority = 2)
	public void verifyForgotPwdForWrongOTP() throws IOException, InterruptedException {
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.setOtp("675443");
		soft.assertEquals(actualMsg1, null);

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		Boolean actualBtn = forgotpassword.changePassword();
		soft.assertEquals(actualBtn, true);

		String actualMsg2 = forgotpassword.succPopup();
		soft.assertEquals(actualMsg2, GetGsonFairplayTestData.getConfigData().getIncorrectVerificationCode());

		Thread.sleep(1000);
		driver.navigate().refresh();
		
	}

	@Test(priority = 3)
	public void verifyForgotPwdForOTPLessThan6Digit() throws IOException, InterruptedException {
	
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		org.testng.Assert.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.setOtp("123");
		org.testng.Assert.assertEquals(actualMsg1, "OTP must be 6 digits");

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		Boolean actualBtn = forgotpassword.changePassword();
		org.testng.Assert.assertEquals(actualBtn, false);
		Thread.sleep(1000);
		driver.navigate().refresh();
	
	}

	@Test(priority = 4)
	public void verifyForgotPwdForOTPContainsOnlyLetters() throws IOException, InterruptedException {

		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		org.testng.Assert.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.setOtp("vcbvcbdvcgdfvd");
		org.testng.Assert.assertEquals(actualMsg1, "Not Able To Enter OTP..!!!");

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);
		
		Boolean actualBtn = forgotpassword.changePassword();
		org.testng.Assert.assertEquals(actualBtn, false);

		Thread.sleep(1000);
		driver.navigate().refresh();
		
	}

	@Test(priority = 5)
	public void verifyForgotPwdForOTPGreaterThan6Digit() throws IOException, InterruptedException {
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.setOtp("12365644545");
		soft.assertEquals(actualMsg1, null);

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		Boolean actualBtn = forgotpassword.changePassword();
		soft.assertEquals(actualBtn, true);

		String actualMsg2 = forgotpassword.succPopup();
		soft.assertEquals(actualMsg2, GetGsonFairplayTestData.getConfigData().getIncorrectVerificationCode());

		Thread.sleep(1000);
		driver.navigate().refresh();
		
	}

	@Test(priority = 6)
	public void verifyForgotPwdForUnmatchedPassword() throws Exception {
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());

		String actualMsg1 = forgotpassword.getOtp();
		soft.assertEquals(actualMsg1, null);

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		String actualErrorMsg = forgotpassword.setConfirmPassword("Pass@123");
		soft.assertEquals(actualErrorMsg, "Password and Confirm Password must match");

		Boolean actualBtn = forgotpassword.changePassword();
		soft.assertEquals(actualBtn, false);
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(2000);
	
	}

	// @Test(priority = 7)
	public void verifyForgotPwdForWrongOTPWithResendCode() throws Exception {
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		forgotpassword.popup();

		forgotpassword.setOtp("123456");

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		forgotpassword.changePassword();
		forgotpassword.succPopup();

		forgotpassword.resendCode();
		forgotpassword.popup();

		String otp = forgotpassword.getOtp();
		forgotpassword.setOtp(otp);

		forgotpassword.changePassword();
		forgotpassword.succPopup();
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(2000);
	}

}
