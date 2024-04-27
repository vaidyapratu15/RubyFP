package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class ForgotPasswordPositiveTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 1)
	public void verifyForgotPassword() throws Exception {
		forgotpassword.forgotPassword();
		String number = forgotpassword.getUsernumber();
		forgotpassword.setUsernumber(number);

		forgotpassword.forgotPasswordButton();
		String actualMsg = forgotpassword.popup();
		soft.assertEquals(actualMsg, GetGsonFairplayTestData.getConfigData().getOtpPopup());

		String otp = forgotpassword.getOtp();
		forgotpassword.setOtp(otp);

		String password = forgotpassword.getStrongPassword();
		forgotpassword.setPassword(password);
		forgotpassword.setConfirmPassword(password);

		Boolean actualBtn = forgotpassword.changePassword();
		soft.assertEquals(actualBtn, true);

		String actualMsg1 = forgotpassword.succPopup();
		soft.assertEquals(actualMsg1, GetGsonFairplayTestData.getConfigData().getPwdChanged());

		GetGsonFairplayTestData.writeJson(number, password);

		driver.navigate().refresh();
		
	}

}
