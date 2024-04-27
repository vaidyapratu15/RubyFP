package com.fairplay.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class TurnOverTestCases extends BaseTest{

	@Test(priority = 1, groups = { "sanity" })
	public void login() throws IOException {
		loginpage.setUsername(GetGsonFairplayTestData.getData().getUsername());
		loginpage.setPassword(GetGsonFairplayTestData.getData().getPassword());
		
		Boolean actualbtn = loginpage.login();
		org.testng.Assert.assertEquals(actualbtn, true);

		loginpage.walletAmount(driver);
	}
	
	@Test(priority = 2, groups = { "sanity" })
	public void clickOnAwaitingBonus() throws InterruptedException {
		loginpage.profileClick();
		turnover.clickOnAwaitingBonus();
	}
	
	@Test(priority = 3)
	public void verifyCheckBonusAmount() throws InterruptedException {
		turnover.countBonus();
		turnover.checkBonusAmt();
		driver.navigate().refresh();
		turnover.countBonus();
	}
	
}
