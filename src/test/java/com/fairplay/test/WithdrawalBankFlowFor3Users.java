package com.fairplay.test;

import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fairplay.library.MongoDatabase;
import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class WithdrawalBankFlowFor3Users extends BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 1, invocationCount = 2)
	public void addBankUser1() throws Exception {
		loginpage.setUsername(GetGsonFairplayTestData.getUserData().getUsername());
		loginpage.setPassword(GetGsonFairplayTestData.getUserData().getPassword());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		String afterLogin = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);

		loginpage.profileClick();
		withdrawalpage.withdrawNow();

		withdrawalpage.addBankDetails();
		withdrawalpage.otpPopup();
		String otp = withdrawalpage.getOtp();
		withdrawalpage.setOtp(otp);

		withdrawalpage.setAccountNumber("576767676761");
		withdrawalpage.confirmAccountNumber("576767676761");
		withdrawalpage.setIfscCode("YESB0000014");
		withdrawalpage.setAccountHolderName("Tejal");
		String actualvalue = withdrawalpage.addAccount();
		String expextedvalue = GetGsonFairplayTestData.getConfigData().getBankAdded();
		soft.assertEquals(actualvalue, expextedvalue);

		loginpage.profileClick();
		loginpage.logout();

		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();

	}

	// @Test(priority = 2)
	public void addBankUser2() throws Exception {
		loginpage.setUsername(GetGsonFairplayTestData.getUserData().getUsername1());
		loginpage.setPassword(GetGsonFairplayTestData.getUserData().getPassword1());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		String afterLogin = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);

		loginpage.profileClick();
		withdrawalpage.withdrawNow();

		withdrawalpage.addBankDetails();
		withdrawalpage.otpPopup();
		String otp = withdrawalpage.getOtp();
		withdrawalpage.setOtp(otp);

		withdrawalpage.setAccountNumber("576767676761");
		withdrawalpage.confirmAccountNumber("576767676761");
		withdrawalpage.setIfscCode("YESB0000014");
		withdrawalpage.setAccountHolderName("Tejal");
		String actualvalue = withdrawalpage.addAccount();
		String expextedvalue = GetGsonFairplayTestData.getConfigData().getBankAdded();
		soft.assertEquals(actualvalue, expextedvalue);

		loginpage.profileClick();
		loginpage.logout();

		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();

	}

	// @Test(priority = 3)
	public void addBankUser3() throws Exception {
		loginpage.setUsername(GetGsonFairplayTestData.getUserData().getUsername2());
		loginpage.setPassword(GetGsonFairplayTestData.getUserData().getPassword2());

		Boolean actualbtn = loginpage.login();
		soft.assertEquals(actualbtn, true);

		String afterLogin = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Login : " + afterLogin);
		soft.assertNotEquals(afterLogin, null);

		loginpage.downloadAppPopUp();

		loginpage.walletAmount(driver);

		loginpage.profileClick();
		withdrawalpage.withdrawNow();

		withdrawalpage.addBankDetails();
		withdrawalpage.otpPopup();
		String otp = withdrawalpage.getOtp();
		withdrawalpage.setOtp(otp);

		withdrawalpage.setAccountNumber("576767676761");
		withdrawalpage.confirmAccountNumber("576767676761");
		withdrawalpage.setIfscCode("YESB0000014");
		withdrawalpage.setAccountHolderName("Tejal");
		String actualvalue = withdrawalpage.addAccount();
		String expextedvalue = GetGsonFairplayTestData.getConfigData().getExistingBank();
		soft.assertEquals(actualvalue, expextedvalue);

		loginpage.profileClick();
		loginpage.logout();

		String afterlogout = Utilities.getAccessToken(driver);
		extentTest.info("Access Token After Logout : " + afterlogout);
		soft.assertEquals(afterlogout, null);
		soft.assertAll();

	}

	// @Test(priority = 4)
	public void checkSuspectedUser() throws IOException {
		Boolean expextedvalue = MongoDatabase.getSuspectedField(GetGsonFairplayTestData.getDBData().getFpDatabase(),
				GetGsonFairplayTestData.getDBData().getCollection(), GetGsonFairplayTestData.getDBData().getKey(),
				"+91".concat(GetGsonFairplayTestData.getUserData().getUsername()),
				GetGsonFairplayTestData.getDBData().getFpField());
		org.testng.Assert.assertEquals(true, expextedvalue);
		extentTest.info("Suspected User1 : " + expextedvalue);

		Boolean expextedvalue1 = MongoDatabase.getSuspectedField(GetGsonFairplayTestData.getDBData().getFpDatabase(),
				GetGsonFairplayTestData.getDBData().getCollection(), GetGsonFairplayTestData.getDBData().getKey(),
				"+91".concat(GetGsonFairplayTestData.getUserData().getUsername1()),
				GetGsonFairplayTestData.getDBData().getFpField());
		org.testng.Assert.assertEquals(true, expextedvalue1);
		extentTest.info("Suspected User2 : " + expextedvalue1);

		Boolean expextedvalue2 = MongoDatabase.getSuspectedField(GetGsonFairplayTestData.getDBData().getFpDatabase(),
				GetGsonFairplayTestData.getDBData().getCollection(), GetGsonFairplayTestData.getDBData().getKey(),
				"+91".concat(GetGsonFairplayTestData.getUserData().getUsername2()),
				GetGsonFairplayTestData.getDBData().getFpField());
		org.testng.Assert.assertEquals(false, expextedvalue2);
		extentTest.info("Suspected User3 : " + expextedvalue2);

	}

}
