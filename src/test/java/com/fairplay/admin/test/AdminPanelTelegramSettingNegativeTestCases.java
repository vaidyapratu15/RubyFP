package com.fairplay.admin.test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminPanelTelegramSettingNegativeTestCases  extends Admin_BaseTest{
	
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
	
	
	//@Test(priority = 1)
	public void verifyInvalidTelegramLink() {
		String actualData = telegram.clickOnSystem();
		soft.assertEquals(actualData, "System Button Is Clickable..!!!");
		
		String actualData1 = telegram.clickOnSystemConfig();
		soft.assertEquals(actualData1, "System Config Button Is Clickable..!!!");
		
		String actualData2 = telegram.clickOnTelegramSetting();
		soft.assertEquals(actualData2, "Telegram Setting Button Is Clickable..!!!");
		
		String link = "gfdhfgdhjfgbdfhvbfbvhfdgb";
		String actualData3 = telegram.setTelegramLink(link);
		soft.assertEquals(actualData3, "Invalid Link");
		
		Boolean actualBtn = telegram.clickOnAddLink();
		soft.assertEquals(actualBtn, false, " ");
		
	}
	
	@Test(priority = 2)
	public void verifyNullTelegramLink() {
		String actualData = telegram.clickOnSystem();
		soft.assertEquals(actualData, "System Button Is Clickable..!!!");
		
		String actualData1 = telegram.clickOnSystemConfig();
		soft.assertEquals(actualData1, "System Config Button Is Clickable..!!!");
		
		String actualData2 = telegram.clickOnTelegramSetting();
		soft.assertEquals(actualData2, "Telegram Setting Button Is Clickable..!!!");
		
		String link = "       ";
		String actualData3 = telegram.setTelegramLink(link);
		soft.assertEquals(actualData3, "Telegram Link is required");
		
		Boolean actualBtn = telegram.clickOnAddLink();
		soft.assertEquals(actualBtn, false, " ");
		
	}

}
