package com.fairplay.main;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Turnover extends BaseClass {

	@FindBy(xpath = "//span[@class='font-weight-bold white--text']")
	WebElement awaitingBonus;

	@FindBy(xpath = "((//div[@class='card-rounded mx-auto bonus-card v-card v-sheet theme--dark'])[1]//span)[1]")
	WebElement bonusAmt;

	@FindBy(xpath = "(//div[@class='d-flex justify-space-between caption'])[1]//div[2]")
	WebElement tunoverAmt;

	@FindBy(xpath = "(//span[@aria-haspopup='true'][normalize-space()='cancel bonus'])[1]")
	WebElement cancleBonus;
	
	@FindBy(xpath = "//button//span[contains(text(),'Yes')]")
	WebElement yes;
	
	@FindBy(xpath = "(//button//span[contains(text(),'No')])[3]")
	WebElement no;
	
	@FindBy(xpath = "//div[@class = 'row py-1 no-gutters justify-center']//div[@class = 'col-sm-6 col-md-6 col-lg-4 col-12 pa-1']")
	List<WebElement> bonuslist;

	@SuppressWarnings("static-access")
	public Turnover(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnAwaitingBonus() {
		try {
			awaitingBonus.click();
			extentTest.info("Awaiting Bonus Is Clickable...!!!");
		} catch (Exception e) {
			extentTest.info("Awaiting Bonus Is Not Clickable...!!!");
		}
	}

	@SuppressWarnings("unused")
	public void countBonus() {
		int count = 0;
		for(WebElement bonuscount : bonuslist) {
			count = count + 1;
		}
		System.out.println(count);
	}
	
	public void checkBonusAmt() throws InterruptedException {
		String amt = bonusAmt.getText();
		int length = amt.length();
		extentTest.info("Bonus Amount Is : " +amt);
		if (length >= 2) {
			String turnover = tunoverAmt.getText();
			extentTest.info("TurnOver Amount Is : " +turnover);
			int length2 = turnover.length();
			System.err.println(length2);
			if (length2 <= 3) {
				playCardGame();
			} else {
				cancleBonus.click();
				Thread.sleep(1000);
				yes.click();
			}
		} else {
			cancleBonus.click();
			Thread.sleep(1000);
			yes.click();
		}
		
		extentTest.info("Try Next Bonus..!!!");
	}

	public void playCardGame() {
		driver.get("https://kheljaa.com/live-cards/98789");
		extentTest.info("Window URL : " +driver.getCurrentUrl());
	}

}
