package com.fairplay.admin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fairplay.library.ActionClass;
import com.fairplay.library.ExplicitWaits;
import com.fairplay.library.FluentWaitsClass;
import com.fairplay.library.RandomizeData;

public class AdminPanelTelegramSetting extends Admin_BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "//li//button[@class = 'btn dropdown-toggle']")
	WebElement system;

	@FindBy(xpath = "//li//ul[@class = 'dropdown-menu show']//a")
	WebElement systemConfig;

	@FindBy(xpath = "(//div//h3[contains(text(),'Telegram Settings')])")
	WebElement telegramSetting;

	@FindBy(xpath = "//label[@class = 'custom-toggle checked']")
	WebElement custumToggleOn;

	@FindBy(xpath = "//label[@class = 'custom-toggle unchecked']")
	WebElement custumToggleOff;

//	@FindBy(xpath = "(//label[@class = 'custom-toggle unchecked'])[2]")
//	WebElement custumToggleOff;
//	
//	@FindBy(xpath = "(//label[@class = 'custom-toggle checked'])[2]")
//	WebElement custumToggleOn;

	@FindBy(css = "class.custom-toggle-slider rounded-circle")
	WebElement hgfg;

	@FindBy(xpath = "(//div//input[@class = 'form-control form-control-alternative'])[1]")
	WebElement enterTelegramLink;

	@FindBy(xpath = "(//div[@class = 'mt-2 text-danger validation-text'])[1]")
	WebElement telegramLinkIsReq;

	@FindBy(xpath = "(//div[@class = 'mt-2 text-danger validation-text'])[2]")
	WebElement invalidTelegramLink;

	@FindBy(xpath = "(//div[@class = 'd-flex align-items-center']//button[@type ='button'])[1]")
	WebElement addLinkBtn;

	@FindBy(xpath = "(//div[@class = 'col-12 col-md-12 py-1 d-flex align-items-center link-height']//span//span//span)[1]")
	WebElement addedLink;

	@FindBy(xpath = "(//div[@class = 'col-12 col-md-12 py-1 d-flex align-items-center link-height']//span//span//span)[2]")
	WebElement cancleAddedLink;

	@FindBy(xpath = "(//div[@class = 'text-center']//button[@type ='button'])[1]")
	WebElement updateBtn;

	@FindBy(xpath = "(//div[@class = 'col-12 col-md-12 d-flex py-1 bg min-height'])[1]//span[@class = 'badge text-lowercase m-1 p-1.5 d-flex align-items-center badge-success']")
	List<WebElement> updatedLinks;

	@FindBy(xpath = "//div[@class = 'v-toaster']")
	WebElement toasterMsg;

	public AdminPanelTelegramSetting(WebDriver driver) {
		Admin_BaseClass.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private String telegramLink;

	public String clickOnSystem() {
		try {
			system.click();
			extentTest.info("System Button Is Clickable..!!!");
			return "System Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("System Button Is Not Clickable..!!!");
			return "System Button Is Not Clickable..!!!";
		}
	}

	public String clickOnSystemConfig() {
		try {
			systemConfig.click();
			extentTest.info("System Config Button Is Clickable..!!!");
			return "System Config Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("System Config Button Is Not Clickable..!!!");
			return "System Config Button Is Not Clickable..!!!";
		}
	}

	public String clickOnTelegramSetting() {
		try {
			telegramSetting.click();
			extentTest.info("Telegram Setting Button Is Clickable..!!!");
			return "Telegram Setting Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("Telegram Setting Button Is Not Clickable..!!!");
			return "Telegram Setting Button Is Not Not Clickable..!!!";
		}
	}

	public void verifyCustumToggleOn() {
		try {
			Boolean btn = custumToggleOn.isDisplayed();
			if (btn == true) {
				extentTest.info("Telegram Is On");

				custumToggleOn.click();

				extentTest.info("Turned Off Telegram");

			}
		} catch (Exception e) {
			extentTest.info("Telegram Toggle Is Not Clickable.");
		}
	}

	public void verifyCustumToggleOff() {
		try {
			Boolean btn = custumToggleOff.isDisplayed();
			if (btn == true) {
				extentTest.info("Telegram Is Off");

				custumToggleOff.click();

				extentTest.info("Turned ON Telegram");
			}
		} catch (Exception e) {
			extentTest.info("Telegram Toggle Is Not Clickable.");
		}
	}

	public String getTelegramLink() {
		telegramLink = rand.randomizeTelegramLink();
		return telegramLink;
	}

	@SuppressWarnings("finally")
	public String setTelegramLink(String telegramLink) {
		this.telegramLink = telegramLink;
		try {
			enterTelegramLink.sendKeys(telegramLink);
			extentTest.info("Newly Added Telegram Link Is : " + telegramLink);
			try {
				String errorMsg = telegramLinkIsReq.getText();
				extentTest.info("Error Message : " + errorMsg);
				return errorMsg;
			} finally {
				return "Telegram Link Added Suceesfully...!!!";
			}
		} catch (Exception e) {
			try {
				String errorMsg = telegramLinkIsReq.getText();
				extentTest.info("Error Message : " + errorMsg);
				return errorMsg;
			} finally {
				return "Not Able To Add Telegram Link...!!!";
			}
		}
	}

	public Boolean clickOnAddLink() {
		Boolean btn = addLinkBtn.isEnabled();
		if (btn == true) {
			addLinkBtn.click();
			extentTest.info("Add Link Button Is Enabled...!!!");
		} else {
			extentTest.info("Add Link Button Is Disabled...!!!");
		}
		return btn;
	}

	public String verifyAddedLink() {
		String link = addedLink.getText();
		if (link.equals(telegramLink)) {
			extentTest.info("Link Added Successfully...!!!");
			return "Link Added Successfully...!!!";
		} else {
			extentTest.info("Link Din't Added Successfully...!!!");
			return "Link Din't Added Successfully...!!!";
		}
	}

	public String verifyCancleAddedLink() {
		try {
			cancleAddedLink.click();
			extentTest.info("Able To Cancle Added Link..!!!");
			return "Able To Cancle Added Link..!!!";
		} catch (Exception e) {
			extentTest.info("Not Able To Cancle Added Link..!!!");
			return "Not Able To Cancle Added Link..!!!";
		}
	}

	public String update() {
		try {
			updateBtn.click();
			extentTest.info("Update Button Is Clickable..!!!");
			return "Update Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("Update Button Is Not Clickable..!!!");
			return "Update Button Is Not Clickable..!!!";
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public void verifyUpdatedLink() {
		try {
			int updatedLinksCount = updatedLinks.size();
			extentTest.info("Link Count : " + updatedLinksCount);

			for (WebElement link : updatedLinks) {
				extentTest.info("Telegram Link Details : " + link.getText());
				if (link.equals(telegramLink)) {
					extentTest.info("Telegram Link Updated...!!!");
				}
			}
		} catch (Exception e) {
			extentTest.info("Telegram Link Din't Updated...!!!");
		}

	}

	@SuppressWarnings("finally")
	public String getToasterMsg() {
		String toasterMessage = toasterMsg.getText();
		try {
			extentTest.info("Toaster Message Is : " + toasterMessage);
		} catch (Exception e) {
			extentTest.info("Not Able To Get Toaster Message...!!!");
		} finally {
			return toasterMessage;
		}

	}

}
