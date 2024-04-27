package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class InstantWithdrawalAddBank_DeleteBankTc extends BaseTest {

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
	public void getWalletAmt() throws InterruptedException {
		loginpage.profileClick();
		withdrawalpage.getWalletDetails();
	}
	
	@Test(priority = 4)
	public void verifyInstantWithdraw() throws InterruptedException {
		String actualData = instantwithdrawalpage.withdrawNow();
		soft.assertEquals(actualData, "WithdrawNow Is Clickable..!!!");
		
		String actualData1 = instantwithdrawalpage.instantWithdraw();
		soft.assertEquals(actualData1, "InstantWithdraw Is Clickable..!!!");
	}

	@Test(priority = 5)
	public void verifyGetOtp() throws Exception {
		String actualData = instantwithdrawalpage.instantAddBankDetails();
		soft.assertEquals(actualData, "AddNow Is Clickable..!!!");
		String actualData1 = instantwithdrawalpage.otpPopup();
		soft.assertEquals(actualData1, GetGsonFairplayTestData.getConfigData().getOtpPopup());
		String otp = instantwithdrawalpage.getOtp();
		String actualData2 = instantwithdrawalpage.setOtp(otp);
		soft.assertEquals(actualData2, null);
	}

	@Test(priority = 6)
	public void verifyAddBankAccount() throws InterruptedException, FileNotFoundException {
		String accountno = instantwithdrawalpage.getAccountNumber();
		instantwithdrawalpage.setAccountNumber(accountno);
		instantwithdrawalpage.confirmAccountNumber(accountno);
		String ifsc = instantwithdrawalpage.getIfscCode();
		instantwithdrawalpage.setIfscCode(ifsc);
		String name = instantwithdrawalpage.getAccountholderName();
		instantwithdrawalpage.setAccountHolderName(name);
		String actualData = instantwithdrawalpage.addAccount();
		soft.assertEquals(actualData, GetGsonFairplayTestData.getConfigData().getBankAdded());
	}

	@Test(priority = 7)
	public void getBankDetailBeforeDeleting() throws InterruptedException {
		instantwithdrawalpage.accountDetails();
	}
	
	@Test(priority = 8)
	public void verifyDeleteAccount() throws Throwable {
		instantwithdrawalpage.clickDeleteBtn();
		instantwithdrawalpage.deleteOtpPopUp();
		String otp = instantwithdrawalpage.getOtp();
		instantwithdrawalpage.setOtp(otp);
		instantwithdrawalpage.yes();
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
	}

}
