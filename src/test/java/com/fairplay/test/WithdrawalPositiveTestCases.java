package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class WithdrawalPositiveTestCases extends BaseTest {

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
		org.testng.Assert.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3)
	public void getWalletAmt() throws InterruptedException {
		loginpage.profileClick();
		withdrawalpage.getWalletDetails();
	}

	@Test(priority = 4)
	public void verifyWithdraw() throws InterruptedException {
		String actualData = withdrawalpage.withdrawNow();
		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");
	}

	@Test(priority = 5)
	public void WithdrawValidAmt() throws InterruptedException, FileNotFoundException {
		withdrawalpage.enterAmt("1000");
		String actualData = withdrawalpage.proceedWithdrawal();
		soft.assertEquals(actualData, "Proceed Button Is Clickable..!!!");
		Thread.sleep(1000);
		String actualData1 = withdrawalpage.popUp();
		soft.assertEquals(actualData1, GetGsonFairplayTestData.getConfigData().getTransInitiated());
	}

	@Test(priority = 6)
	public void checkWithdrawalReq() throws InterruptedException {
		// loginpage.profileClick();
		//withdrawalpage.myTransactions();
		Thread.sleep(1000);
		String actualData = withdrawalpage.checkWithdrawalReq();
		soft.assertEquals(actualData, "withdrawal_req Button Is Clickable..!!!");
	}

	@Test(priority = 7)
	public void getWalletAmtAfterWithdrawal() throws InterruptedException {
		loginpage.profileClick();
		withdrawalpage.getWalletDetails();
	}

	@Test(priority = 8)
	public void cancelWithdrawalReq() throws InterruptedException, FileNotFoundException {
		String actualData = withdrawalpage.cancelWithdrawalReq();
		soft.assertEquals(actualData, "Cancel Withdrawal_req Button Is Clickable..!!!");
		Thread.sleep(1000);
		String actualData1 = withdrawalpage.popUp();
		soft.assertEquals(actualData1, GetGsonFairplayTestData.getConfigData().getTransCancelled());
		Thread.sleep(1000);
	}

	@Test(priority = 9)
	public void getWalletAmtAfterCancellingWithdrawal() throws InterruptedException {
		loginpage.profileClick();
		withdrawalpage.getWalletDetails();
	}

	@Test(priority = 10)
	public void logout() {
		//loginpage.profileClick();
		loginpage.logout();
	}
	
	//@Test(priority = 11)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}
}
