package com.fairplay.main;

import java.io.FileNotFoundException;
import java.util.List;
//import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fairplay.library.ActionClass;
import com.fairplay.library.ExplicitWaits;
import com.fairplay.library.FluentWaitsClass;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

/*
 * @Author: Tejal Gavade.
 * @Since : November 2022
 * @Discription : For Negative credentials user should not able to do login and all respective error message should able to fetch.
 *                For Positive credentials user should able to login successfully.
 */

public class FairplayLoginPage extends BaseClass {

	ActionClass act = new ActionClass();
	FluentWaitsClass wait = new FluentWaitsClass();
	ExplicitWaits ewait = new ExplicitWaits();

	@FindBy(xpath = ("(//div[@class='v-text-field__slot'])[1]//input"))
	WebElement username;

	@FindBy(xpath = ("//div[text() ='Username must be of 5 characters or more']"))
	WebElement userErr;

	@FindBy(xpath = ("(//div[@class='v-text-field__slot'])[2]//input"))
	WebElement password;

	@FindBy(xpath = ("//div[text() ='Password must be of 6 characters or more']"))
	WebElement pwdErr;

	@FindBy(xpath = "//div[text() ='Password cannot contain spaces!']")
	WebElement passErr;

	@FindBy(xpath = ("//button[@type='submit']"))
	WebElement loginBtn;

	@FindBy(xpath = ("//div//div[@role='status']"))
	WebElement popup;

	@FindBy(xpath = "//div[contains(text(),'Logged in successfully.')]")
	WebElement loginPopup;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])")
	WebElement close;

	@FindBy(xpath = "//div[contains(text(),'Logged in successfully.')]")
	WebElement successfulMsg;

	@FindBy(xpath = "//span[@class = 'user-balance white--text']")
	WebElement walletAmt;

	@FindBy(xpath = "//span[contains(text(), 'Profile')]")
	WebElement profile;

	@FindBy(xpath = "//div[@class='font-w letter-space pcard-text f14']")
	WebElement profileText;

	@FindBy(xpath = "//span[contains(text(), 'Logout')]")
	WebElement logout;

	@FindBy(xpath = ("//i[@class='v-icon notranslate mdi mdi-reload theme--dark white--text']"))
	WebElement refresh;

	@FindBy(xpath = "(//div[@class='v-select__selections'])[1]")
	WebElement select_lang;

	@FindBy(xpath = "(//div[@role='listbox'])[1]//div")
	List<WebElement> langauge;

	@FindBy(xpath = "(//div[@class='v-select__slot'])[2]")
	WebElement selCountryCode;

	@FindBy(xpath = "(//div[@role = 'listbox'])//div")
	List<WebElement> listCountryCode;

	@FindBy(xpath = "(//div[contains(text(),'Connect with us on')])[1]")
	WebElement connectOnWhatsApp;

	@FindBy(xpath = "(//span[contains(text(),'Whatsapp')])[1]")
	WebElement whatsAppNow;
	
	@FindBy(xpath = "(//span[contains(text(),'Telegram')])[1]")
	WebElement telegram;
	
	@FindBy(xpath = "//div[@class = 'download-app-popup']//div[@class = 'v-responsive__content']")
	WebElement appDownloadPopUp;
	
	@FindBy(xpath = "//div[@class = 'download-app-popup']//button")
	WebElement cancle_appDownloadPopUp;

	private String userName;
	private String passWord;

	public FairplayLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void contryCode(String code) {
		selCountryCode.click();
		for (WebElement countrycode : listCountryCode) {
			String codetext = countrycode.getText();
			if (codetext.equals(code)) {
				extentTest.info("Country code is : " + countrycode.getText());
				countrycode.click();
				ewait.elementToBeClickable(driver, countrycode, 4);
			}
		}
	}

	public String getUserName() throws FileNotFoundException {
		driver.get(GetGsonFairplayTestData.getUserData().getUsername());
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
		username.sendKeys(userName);
		extentTest.info("Username : " + userName);
	}

	public String getPassword() throws FileNotFoundException {
		driver.get(GetGsonFairplayTestData.getUserData().getPassword());
		return passWord;
	}

	public void setPassword(String passWord) {
		this.passWord = passWord;
		int pwd = passWord.length();
		password.sendKeys(passWord);
		extentTest.info("Password : " + passWord);
		try {
			if (pwd < 6) {
				ewait.visibilityOf(driver, pwdErr, 2);
				extentTest.info(pwdErr.getText());
			} else {
				if (passWord.contains(" ")) {
					ewait.visibilityOf(driver, passErr, 2);
					extentTest.info(passErr.getText());
				}
			}
		} catch (Exception e) {
			extentTest.info("Password is valid");
		}
	}

	@SuppressWarnings("finally")
	public Boolean login() {
		Boolean btn = loginBtn.isEnabled();
		loginBtn.click();
		try {
			ewait.visibilityOf(driver, popup, 2);
			String msg = popup.getText();
			Thread.sleep(1000);
			extentTest.info("Verification message :" + msg);
			close.click();
		} finally {
			extentTest.info("Login button is enable : " + btn.toString());
			return btn;
		}
	}

//	public String popup(WebDriver driver) throws InterruptedException {
//		ewait.visibilityOf(driver, popup, 8);
//		String errormsg = popup.getText();
//		Thread.sleep(2000); 
//		close.click();
//		extentTest.info(errormsg);
//		return errormsg;
//	}

	public void walletAmount(WebDriver driver) {
		try {
			String amount = walletAmt.getText();
			extentTest.info("Wallet Amount : " + amount);
		} catch (Exception e) {
			extentTest.info("Not able to fetch Wallet Amount");
		}
	}

	public void profileClick() {
		try {
			act.jSForScroll(driver, profile);
			Thread.sleep(1000);
			ewait.visibilityOf(driver, profile, 2);
			Thread.sleep(1000);
			profile.click();
			// extentTest.info("User : " + profileText.getText());
		} catch (Exception e) {
			extentTest.info("Profile button is not clickable");
		}
	}

	public Boolean checkConnectOnWhatsApp() {
		try {
			Boolean data = connectOnWhatsApp.isDisplayed();
			extentTest.info("Connect On WhatsApp Is Visible : " + data);
			return true;
		} catch (Exception e) {
			extentTest.info("Connect On WhatsApp Not Is Visible...!!!");
			return false;
		}
	}

	public Boolean checkWhatsApp() {
		try {
			Boolean data = whatsAppNow.isDisplayed();
			extentTest.info("whatsAppNow Is Visible : " + data);
			return true;
		} catch (Exception e) {
			extentTest.info("whatsAppNow Is Not Visible...!!!");
			return false;
		}
	}
	
	public Boolean checkTelegram() {
		try {
			Boolean data = telegram.isDisplayed();
			extentTest.info("Telegram Is Visible : " + data);
			return true;
		} catch (Exception e) {
			extentTest.info("Telegram Is Not Visible...!!!");
			return false;
		}
	}
	
	public void downloadAppPopUp() {
		try {
			Boolean data = appDownloadPopUp.isDisplayed();
			if(data == true) {
				extentTest.info("Download App PopUp Is Visible..!!!");
				cancle_appDownloadPopUp.click();
			}
		}catch (Exception e) {
			extentTest.info("Download App PopUp Is Not Visible..!!!");
		}
	}

	public void refresh() {
		refresh.click();
	}

	public void logout() {
		act.jSForScroll(driver, logout);
		logout.click();
		extentTest.info(driver.getTitle());
	}

}
