package com.fairplay.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class WithdrawalAddBank_DeleteBankTc extends BaseTest{
	
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
	public void getOtp() throws Exception {
		String actualData = withdrawalpage.addBankDetails();
		soft.assertEquals(actualData, "AddNow Is Clickable..!!!");
		String actualData1 = withdrawalpage.otpPopup();
		soft.assertEquals(actualData1, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = withdrawalpage.getOtp();
		String actualData2 = withdrawalpage.setOtp(otp);
		soft.assertEquals(actualData2, null);
	}

	@Test(priority = 6)
	public void verifyAddBankAccount() throws InterruptedException, FileNotFoundException {
		String accountno = withdrawalpage.getAccountNumber();
		withdrawalpage.setAccountNumber(accountno);
		withdrawalpage.confirmAccountNumber(accountno);
		String ifsc = withdrawalpage.getIfscCode();
		withdrawalpage.setIfscCode(ifsc);
		String name = withdrawalpage.getAccountholderName();
		withdrawalpage.setAccountHolderName(name);
		String actualData = withdrawalpage.addAccount();
		soft.assertEquals(actualData, GetGsonFairplayTestData.getConfigData().getBankAdded());
	}

	@Test(priority = 7)
	public void getBankDetailBeforeDeleting() throws InterruptedException {
		withdrawalpage.accountDetails();
	}

	@Test(priority = 8)
	public void verifyDeleteAccount() throws Throwable {
		withdrawalpage.clickDeleteBtn();
		withdrawalpage.deleteOtpPopUp();
		String otp = withdrawalpage.getOtp();
		withdrawalpage.setOtp(otp);
		withdrawalpage.yes();
	}
	
	@Test(priority = 9)
	public void logout() {
		loginpage.profileClick();
		loginpage.logout();
	}
	
	@Test(priority = 10)
	public void checkAccessTokenAfterLogout() {
		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();
	}

	
	

}
