package com.fairplay.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.AWTException;
import java.io.IOException;

import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class DepositPagePositiveTestCases extends BaseTest {
	
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
	public void verifyDeposit() throws InterruptedException {
		loginpage.profileClick();
		deposit.checkAmount();
		deposit.depositNow();
	}
	
	@Test(priority = 4)
	public void verifydepositThroughFPBook() throws InterruptedException {
		String actualtitle = deposit.depositThroughFPBook();
		soft.assertEquals(actualtitle, "Share on WhatsApp");
	}

	@Test(priority = 5)
	public void verifyDepositThroughUPI() throws InterruptedException {
		String actualtitle = deposit.depositThroughUPI("500", "fdgsdcgsdfrwe");
		soft.assertEquals(actualtitle, "Invalid Request");
	}
	
	@Test(priority = 6)
	public void upi_transaction_details() throws InterruptedException {
		deposit.checkTransactions();
		Thread.sleep(1000);
		deposit.getTransactionDetails();
		loginpage.profileClick();
		deposit.depositNow();
	}

	@Test(priority = 7)
	public void verifyDepositThroughNetBanking() throws InterruptedException {
		String actualtitle2 = deposit.depositThroughNetBanking("601", "TEJ1234");
		soft.assertEquals(actualtitle2, "Cashier | fairplay.club INR");
	}
	
	@Test(priority = 8)
	public void netBanking_transaction_details() {
		deposit.checkTransactions();
		deposit.getTransactionDetails();
		loginpage.profileClick();
		deposit.depositNow();
	}
	
	@Test(priority = 9)
	public void verifyDepositThroughBankDeposit() throws InterruptedException, IOException, AWTException {
		deposit.depositThroughBankDeposit("701");
	}
	
	@Test(priority = 10)
	public void bankDepposit_transaction_details() {
		deposit.getTransactionDetails();
		loginpage.profileClick();
		deposit.depositNow();
	}

	@Test(priority = 11)
	public void verifyDepositThroughAstropay() throws InterruptedException {
		String actualtitle4 = deposit.depositThroughAstropay("800", "876543567iuyghfdsx");
		soft.assertEquals(actualtitle4, "AstroPay OneTouch - Deposit");	
	}
	
	@Test(priority = 12)
	public void astropay_transaction_details() {
		deposit.checkTransactions();
		deposit.getTransactionDetails();
		loginpage.profileClick();
		deposit.depositNow();
	}

	//@Test(priority = 13)
	public void verifydepositThroughCrypto() throws InterruptedException {
		String actualtitle5 = deposit.depositThroughCrypto();
		soft.assertEquals(actualtitle5, "Select Currency - iPint");	
	}
	
	//@Test(priority = 14)
	public void crypto_transaction_details() {
		deposit.checkTransactions();
		deposit.getTransactionDetails();
		loginpage.profileClick();
		deposit.depositNow();
	}

//	@AfterMethod
//	public void onTestFailure(ITestResult result) throws InterruptedException {
//		if (result.getStatus() == ITestResult.FAILURE) {
//			String testcasename = result.getMethod().getMethodName();
//			Utilities.captureScreenshot(driver, testcasename + ".jpg");
//		}
//	}
	

}
