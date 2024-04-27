package com.fairplay.admin.test;

import org.testng.annotations.Test;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class AdminLoginTestCase extends Admin_BaseTest {
	
	@Test
	public void verifyLoginPositiveTc() throws Exception {
		String username = adminlogin.getUsername();
		adminlogin.setUsername(username);

		adminlogin.clickNext();

		Thread.sleep(10000);
		
		String otp = adminlogin.getOtp();
		adminlogin.setOtp(otp);

		String password = adminlogin.getPassword();
		adminlogin.setPassword(password);

		adminlogin.login();

		Thread.sleep(10000);
		
		String beforelogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token Before Logout : " + beforelogout);
		org.testng.Assert.assertNotEquals(beforelogout, null);
		
		Thread.sleep(10000);
		
		GetGsonFairplayTestData.writeAccessToken(beforelogout);
	}

}
