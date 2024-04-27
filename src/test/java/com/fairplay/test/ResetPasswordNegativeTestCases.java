package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class ResetPasswordNegativeTestCases extends BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 1)
	public void verifyLogin() throws IOException {
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
	public void verifyProfileClick() {
		loginpage.profileClick();
	}

	@Test(priority = 4)
	public void verifyResetPwdBtn() {
		resetpage.ResetPwd();
	}

	@Test(priority = 5)
	public void verifyResetPwd_Unmatched() throws IOException {
		resetpage.oldPwd(GetGsonFairplayTestData.getData().getPassword());

		String newpassword = GetGsonFairplayTestData.getConfigData().getNewPwdUnmatched();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonFairplayTestData.getConfigData().getConfirmPwdUnmatched();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, "Reset button is disabled");

	}

	@Test(priority = 6)
	public void verifyResetPwd_ContainsSpace() throws IOException {

		resetpage.oldPwd(GetGsonFairplayTestData.getData().getPassword());

		String newpassword = GetGsonFairplayTestData.getConfigData().getPwdContainsSpace();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonFairplayTestData.getConfigData().getPwdContainsSpace();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, "Reset button is disabled");
	}

	@Test(priority = 7)
	public void verifyResetPwd_Uppercase() throws IOException, InterruptedException {
		resetpage.oldPwd(GetGsonFairplayTestData.getUserData().getPassword());

		String newpassword = GetGsonFairplayTestData.getConfigData().getPwdUppercase();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonFairplayTestData.getConfigData().getPwdUppercase();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonFairplayTestData.getConfigData().getWeakPwd());
		Thread.sleep(60000);

	}

	@Test(priority = 8)
	public void verifyResetPwd_Lowercase() throws IOException, InterruptedException {

		resetpage.oldPwd(GetGsonFairplayTestData.getUserData().getPassword());

		String newpassword = GetGsonFairplayTestData.getConfigData().getPwdLowercase();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonFairplayTestData.getConfigData().getPwdLowercase();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonFairplayTestData.getConfigData().getWeakPwd());
		Thread.sleep(60000);

	}

	@Test(priority = 8)
	public void verifyResetPwd_Numbers() throws IOException, InterruptedException {

		resetpage.oldPwd(GetGsonFairplayTestData.getUserData().getPassword());

		String newpassword = GetGsonFairplayTestData.getConfigData().getPwdNumbers();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonFairplayTestData.getConfigData().getPwdNumbers();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonFairplayTestData.getConfigData().getWeakPwd());
		Thread.sleep(60000);

	}

	@Test(priority = 9)
	public void verifyResetPwd_OldPassword() throws IOException, InterruptedException {

		resetpage.oldPwd(GetGsonFairplayTestData.getConfigData().getPwdOldPassword());

		String newpassword = GetGsonFairplayTestData.getConfigData().getNewPassword();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonFairplayTestData.getConfigData().getNewPassword();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonFairplayTestData.getConfigData().getUnmatchedOldPwd());
		Thread.sleep(60000);

	}

	@Test(priority = 10)
	public void verifyResetPwd_OldNewSame() throws IOException, InterruptedException {
		resetpage.oldPwd(GetGsonFairplayTestData.getData().getPassword());

		String newpassword = GetGsonFairplayTestData.getData().getPassword();
		resetpage.setNewPwd(newpassword);

		String oldpassword = GetGsonFairplayTestData.getData().getPassword();
		resetpage.setConfirmNewPwd(oldpassword);

		String msg = resetpage.resetPassword();
		soft.assertEquals(msg, GetGsonFairplayTestData.getConfigData().getUnmatchedPwd());
	}

	@Test(priority = 11)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}

	@Test(priority = 12)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

}
