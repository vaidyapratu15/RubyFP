package com.fairplay.admin.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminPanelWhatsAppSettingTestCases extends Admin_BaseTest {

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
		String actualData = whatsApp.clickOnSystem();
		soft.assertEquals(actualData, "System Button Is Clickable..!!!");
	}

	@Test(priority = 2)
	public void clickOnSystemConfigBtn() {
		String actualData = whatsApp.clickOnSystemConfig();
		soft.assertEquals(actualData, "System Config Button Is Clickable..!!!");
	}

	@Test(priority = 3)
	public void clickOnTelegramSettingBtn() {
		String actualData = whatsApp.clickOnwhatsAppSetting();
		soft.assertEquals(actualData, "WhatsApp Setting Button Is Clickable..!!!");
	}

	@Test(priority = 4)
	public void verifyToggleButton() {
		// whatsApp.verifyCustumToggleOn();
		whatsApp.verifyCustumToggleOff();
	}
}
