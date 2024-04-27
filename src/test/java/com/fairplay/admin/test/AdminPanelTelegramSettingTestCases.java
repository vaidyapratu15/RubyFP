package com.fairplay.admin.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminPanelTelegramSettingTestCases extends Admin_BaseTest {

	SoftAssert soft = new SoftAssert();

	@Test(priority = 0)
	public void verifyLoginPositiveTc() throws Exception {
		String username = adminlogin.getUsername();
		adminlogin.setUsername(username);

		adminlogin.clickNext();

		String otp = adminlogin.getOtp();
		adminlogin.setOtp(otp);

		String password = adminlogin.getPassword();
		adminlogin.setPassword(password);

		adminlogin.login();
	}

	@Test(priority = 1)
	public void clickOnSystemBtn() {
		String actualData = telegram.clickOnSystem();
		soft.assertEquals(actualData, "System Button Is Clickable..!!!");
	}

	@Test(priority = 2)
	public void clickOnSystemConfigBtn() {
		String actualData = telegram.clickOnSystemConfig();
		soft.assertEquals(actualData, "System Config Button Is Clickable..!!!");
	}

	//@Test(priority = 3)
	public void clickOnTelegramSettingBtn() {
		String actualData = telegram.clickOnTelegramSetting();
		soft.assertEquals(actualData, "Telegram Setting Button Is Clickable..!!!");
	}

	@Test(priority = 4)
	public void clickOnCustomToggle() {
		telegram.verifyCustumToggleOff();
		//telegram.verifyCustumToggleOn();
	}

	//@Test(priority = 5)
	public void verifyTelegramLink() {
		String link = telegram.getTelegramLink();
		String actualData = telegram.setTelegramLink(link);
		soft.assertEquals(actualData, "Telegram Link Added Suceesfully...!!!");
	}

	//@Test(priority = 6)
	public void clickOnAddLinkBtn() {
		Boolean actualBtn = telegram.clickOnAddLink();
		soft.assertEquals(actualBtn, true, " ");
	}

	//@Test(priority = 7)
	public void verifyAddedLink() {
		String actualData = telegram.verifyAddedLink();
		soft.assertEquals(actualData, "Link Added Successfully...!!!");
	}

	@Test(priority = 8)
	public void verifyUpdateBtn() {
		String actualData = telegram.update();
		soft.assertEquals(actualData, "Update Button Is Clickable..!!!");
	}

	//@Test(priority = 9)
	public void verifyUpdatedLink() {
		telegram.verifyUpdatedLink();
	}

	@Test(priority = 10)
	public void verifyToasterMessage() {
		String actualData = telegram.getToasterMsg();
		soft.assertEquals(actualData, "Telegram settings updated successfully");
	}
}
