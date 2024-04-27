package com.fairplay.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class InstantWithdrawalPositiveTestCases extends BaseTest  {
	
	SoftAssert soft = new SoftAssert();

	@Test(priority = 1)
	public void login() throws IOException {
		loginpage.setUsername(GetGsonFairplayTestData.getData().getUsername());
		loginpage.setPassword(GetGsonFairplayTestData.getData().getPassword());
//		loginpage.setUsername("8767227546");
//		loginpage.setPassword("Pass@1234");

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
		instantwithdrawalpage.getWalletDetails();
	}

	@Test(priority = 4)
	public void verifyInstantWithdraw() throws InterruptedException {
		String actualData = instantwithdrawalpage.withdrawNow();
		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");
		Thread.sleep(1000);
		String actualData1 = instantwithdrawalpage.instantWithdraw();
		soft.assertEquals(actualData1, "InstantWithdraw Is Clickable..!!!");
	}

	@Test(priority = 5)
	public void WithdrawValidAmt() throws InterruptedException, FileNotFoundException {
		instantwithdrawalpage.enterAmt("1000");
		String actualData = instantwithdrawalpage.proceedWithdrawal();
		soft.assertEquals(actualData, "Proceed Button Is Clickable..!!!");
		Thread.sleep(2000);
		String actualData1 = instantwithdrawalpage.popUp();
		soft.assertEquals(actualData1, GetGsonFairplayTestData.getConfigData().getTransInitiated());
	}

	@Test(priority = 6)
	public void checkWithdrawalReq() throws InterruptedException {
		// loginpage.profileClick();
		//withdrawalpage.myTransactions();
		Thread.sleep(1000);
		String actualData = instantwithdrawalpage.checkWithdrawalReq();
		soft.assertEquals(actualData, "withdrawal_req Button Is Clickable..!!!");
	}

	@Test(priority = 7)
	public void getWalletAmtAfterWithdrawal() throws InterruptedException {
		loginpage.profileClick();
		instantwithdrawalpage.getWalletDetails();
	}

	@Test(priority = 8)
	public void cancelWithdrawalReq() throws InterruptedException, FileNotFoundException {
		String actualData = instantwithdrawalpage.cancelWithdrawalReq();
		soft.assertEquals(actualData, "Cancel Withdrawal_req Button Is Clickable..!!!");
		Thread.sleep(1000);
		String actualData1 = instantwithdrawalpage.popUp();
		soft.assertEquals(actualData1, GetGsonFairplayTestData.getConfigData().getTransCancelled());
		Thread.sleep(1000);
	}

	@Test(priority = 9)
	public void getWalletAmtAfterCancellingWithdrawal() throws InterruptedException {
		loginpage.profileClick();
		Thread.sleep(1000);
		instantwithdrawalpage.getWalletDetails();
	}

	@Test(priority = 10)
	public void logout() {
		//loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 11)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
	}

}
