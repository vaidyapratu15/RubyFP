package com.fairplay.test;

import org.testng.annotations.Test;
import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class DepositeCompleteFlow extends BaseTest {
	

	@Test(priority = 1, groups = { "sanity" })
	public void login() throws IOException {
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
		org.testng.Assert.assertNotEquals(afterLogin, null);
	}

	@Test(priority = 3, groups = { "sanity" })
	public void verifyDeposit() throws InterruptedException {
		loginpage.profileClick();
		deposit.checkAmount();
		deposit.depositNow();
	}
	
	@Test(priority = 4, groups = { "sanity" })
	public void verifyDepositThroughNetBanking() throws InterruptedException {
		String actualtitle2 = deposit.depositThroughNetBanking("601", "gfgfdgfghh");
		org.testng.Assert.assertEquals(actualtitle2, "Cashier | fairplay.club INR");
	}
	
	@Test(priority = 5)
	public void upi_transaction_details() throws InterruptedException {
		deposit.checkTransactions();
		deposit.getTransactionDetails();
		Thread.sleep(5000);
	}
	
	@Test(priority = 6)
	public void afterTransactionWalletAmt() throws InterruptedException {
		loginpage.profileClick();
		deposit.checkAmount();
		Thread.sleep(1000);
		driver.navigate().refresh();
	}
	
	@Test(priority = 7)
	public void upi_transaction_status() {
		deposit.checkTransactions();
		deposit.getTransactionDetails();
	}
	
	
//	@Test(priority = 8)
//	public void checkAccessTokenAfterLogout() {
//		String afterLogout = Utilities.getAccessToken(driver);
//		org.testng.Assert.assertEquals(afterLogout, null);
//	}

}
