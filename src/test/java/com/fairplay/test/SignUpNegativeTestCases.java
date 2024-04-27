package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class SignUpNegativeTestCases extends BaseTest{

	SoftAssert soft = new SoftAssert();
	
	@Test
	public void getBrowserLink() {
		extentTest.info("Link : " + driver.getCurrentUrl());
	}
	
	@Test(priority = 1)
	public void verifySignupWithWeakPwd() throws IOException, InterruptedException {
		signuppage.joinNow();
		
		signuppage.scrollTillRegister();
		Thread.sleep(1000);
		
		String name = signuppage.getName();
		signuppage.setName(name);
		
		String number = signuppage.getNumber();
		signuppage.setNumber(number);

		String password = signuppage.getWeakPassword();
		signuppage.setConfirmPassword(password);

		boolean btn = signuppage.clickRegister();
		soft.assertEquals(btn, true);

		String popupMsg = signuppage.popup();
		String expectedPopUp = GetGsonFairplayTestData.getConfigData().getEasyPasswordMsg();
		soft.assertEquals(popupMsg, expectedPopUp);
		
	}

	@Test(priority = 2)
	public void verifySignupWithInvalidUsername() throws IOException, InterruptedException {
		signuppage.scrollTillRegister();
		Thread.sleep(1000);

		signuppage.setName("T");
		
		String number = signuppage.getNumber();
		signuppage.setNumber(number);

		String password = signuppage.getStrongPassword();
		signuppage.setConfirmPassword(password);

		boolean btn = signuppage.clickRegister();
		org.testng.Assert.assertEquals(btn, true);

		String popupMsg = signuppage.popup();
		String expectedPopUp = GetGsonFairplayTestData.getConfigData().getWeakUsername();
		soft.assertEquals(popupMsg, expectedPopUp);
		
		
	}
	
	@Test(priority = 3)
	public void verifySignupWithAlreadyExistingNumber() throws IOException, InterruptedException {
		signuppage.scrollTillRegister();
		Thread.sleep(1000);
		
		String name = signuppage.getName();
		signuppage.setName(name);

		signuppage.setNumber("7249153969");

		String password = signuppage.getStrongPassword();
		signuppage.setConfirmPassword(password);

		boolean btn = signuppage.clickRegister();
		soft.assertEquals(btn, true);

		String popupMsg = signuppage.popup();
		String expectedPopUp = GetGsonFairplayTestData.getConfigData().getExistingUser();
		soft.assertEquals(popupMsg, expectedPopUp);
		
		
	}
	
	@Test(priority = 4)
	public void verifySignupWithUnmatchedPwd() throws IOException, InterruptedException {
		signuppage.scrollTillRegister();
		Thread.sleep(1000);
		
		String name = signuppage.getName();
		signuppage.setName(name);
		
		String number = signuppage.getNumber();
		signuppage.setNumber(number);

		signuppage.getStrongPassword();
		signuppage.setConfirmPassword("Pass@123");

		boolean btn = signuppage.clickRegister();
		soft.assertEquals(btn, false);
	}
	
	@Test(priority = 5)
	public void verifySignupWithInvalidOTP() throws IOException, InterruptedException {
		signuppage.scrollTillRegister();
		Thread.sleep(1000);
		
		String name = signuppage.getName();
		signuppage.setName(name);
		
		String number = signuppage.getNumber();
		signuppage.setNumber(number);

		String password = signuppage.getStrongPassword();
		signuppage.setConfirmPassword(password);

		boolean btn = signuppage.clickRegister();
		soft.assertEquals(btn, true);

		String popupMsg = signuppage.popup();
		String expectedPopUp = GetGsonFairplayTestData.getConfigData().getUserRegistered();
		soft.assertEquals(popupMsg, expectedPopUp);

		String actualMsg = signuppage.setOtp("1234567376454654");
		soft.assertEquals(actualMsg, "OTP must be of 6 characters only");
		
		boolean btn2 = signuppage.clickConfirm();
		soft.assertEquals(btn2, false);
		
	}
	
	@Test(priority = 6)
	public void verifySignupForOTPIncludingLetters() throws IOException, InterruptedException {
		signuppage.scrollTillRegister();
		Thread.sleep(1000);
		
		String name = signuppage.getName();
		signuppage.setName(name);
		
		String number = signuppage.getNumber();
		signuppage.setNumber(number);

		String password = signuppage.getStrongPassword();
		signuppage.setConfirmPassword(password);

		boolean btn = signuppage.clickRegister();
		soft.assertEquals(btn, true);

		String popupMsg = signuppage.popup();
		String expectedPopUp = GetGsonFairplayTestData.getConfigData().getUserRegistered();
		soft.assertEquals(popupMsg, expectedPopUp);

		String actualMsg = signuppage.setOtp("jbcxzhxgvcxgcvdgvc");
		soft.assertEquals(actualMsg, "Not Able To Enter OTP..!!!");
		
		boolean btn2 = signuppage.clickConfirm();
		soft.assertEquals(btn2, false);
		
	}

	
	@Test(priority = 7)
	public void verifySignupForIncorrectOTP() throws IOException, InterruptedException {
		signuppage.scrollTillRegister();
		Thread.sleep(1000);
		
		String name = signuppage.getName();
		signuppage.setName(name);
		
		String number = signuppage.getNumber();
		signuppage.setNumber(number);

		String password = signuppage.getStrongPassword();
		signuppage.setConfirmPassword(password);

		boolean btn = signuppage.clickRegister();
		soft.assertEquals(btn, true);

		String popupMsg = signuppage.popup();
		String expectedPopUp = GetGsonFairplayTestData.getConfigData().getUserRegistered();
		soft.assertEquals(popupMsg, expectedPopUp);

		String actualMsg = signuppage.setOtp("675554");
		soft.assertEquals(actualMsg, "");
		
		boolean btn2 = signuppage.clickConfirm();
		soft.assertEquals(btn2, true);
		
		String successfulMessage = signuppage.signup();
		String expected = GetGsonFairplayTestData.getConfigData().getIncorrectOtp();
		soft.assertEquals(successfulMessage, expected);
		soft.assertAll();
		
	}

	@AfterMethod
	public void onTestFailure(ITestResult result) throws InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String testcasename = result.getMethod().getMethodName();
			Utilities.captureScreenshot(driver, testcasename + ".jpg");
		}
		driver.navigate().refresh();
		Thread.sleep(1000);
	}

	
}
