package com.fairplay.test;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class ResetPasswordPositiveTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();
	
	@Test(priority = 1, groups = { "sanity" })
	public void verifyLogin() throws IOException {
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
		soft.assertNotEquals(afterLogin, null);
	}
	
	@Test(priority = 3)
	public void verifyProfileClick() {
		loginpage.profileClick();
	}

	@Test(priority = 4)
	public void verifyResetPwdBtn() {
		resetpage.ResetPwd();
	}

	@Test(priority = 5)
	public void verifyResetPwd() throws IOException, InterruptedException {

		resetpage.oldPwd(GetGsonFairplayTestData.getData().getPassword());

		String newpassword = resetpage.getPwd();
		resetpage.setPwd(newpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonFairplayTestData.getConfigData().getPwdChanged());

		Thread.sleep(1000);
		
		GetGsonFairplayTestData.writeJson(GetGsonFairplayTestData.getData().getUsername(), newpassword);

	}

	@Test(priority = 6)
	public void logout() {
		loginpage.downloadAppPopUp();
		loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 7)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

}
