package com.fairplay.main;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.fairplay.library.ActionClass;
import com.fairplay.library.ExplicitWaits;
import com.fairplay.library.FluentWaitsClass;
import com.fairplay.library.RandomizeData;
import com.fairplay.library.Utilities;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class FairplayDepositPage extends BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "(//span[contains(text(),'Deposit Now')])[1]")
	WebElement depositeNow;

	@FindBy(xpath = "(//button[@class = 'v-expansion-panel-header white darken-4 pa-0 rounded'])")
	List<WebElement> modesOfDeposit;

	// upi
	@FindBy(xpath = "(//div//button//div[@class = 'card-rounded white pl-3 pr-4 v-card v-sheet theme--dark'])[1]")
	WebElement upi;

	@FindBy(xpath = "(//input[@placeholder = 'Coupon Code'])[1]")
	WebElement promoUPI;

	@FindBy(xpath = "((//div[@class='v-text-field__slot'])//input[@placeholder = 'Amount*'])[1]")
	WebElement enterAmountUPI;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[3]")
	WebElement errorMsgUPI;

	@FindBy(xpath = "(//span[contains(text(),'Proceed To Pay')])[1]")
	WebElement payUPI;

	@FindBy(xpath = "//a[contains(text(), 'Deposit 1 Lakh & above Click here !!!')]")
	WebElement pay1lackAbove;

	// netbanking
	@FindBy(xpath = "(//div[@class = 'card-rounded white pl-3 pr-4 v-card v-sheet theme--dark'])[2]")
	WebElement netBanking;

	@FindBy(xpath = "(//input[@placeholder = 'Coupon Code'])[2]")
	WebElement promoNET;

	@FindBy(xpath = "((//div[@class='v-text-field__slot'])//input[@placeholder = 'Amount*'])[2]")
	WebElement enterAmountNET;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[5]")
	WebElement errorMsgNET;

	@FindBy(xpath = "(//span[contains(text(),'Proceed To Pay')])[2]")
	WebElement payNET;

	// fpbook
	@FindBy(xpath = "//div//div//button[@class = 'v-expansion-panel-header white darken-4 pa-0 rounded']//div[@class = 'card-rounded white pl-3 pr-4 v-card v-sheet theme--dark blink-2']")
	WebElement fairplaybook;

	@FindBy(xpath = "(//span[contains(text(),'Click Here')])[1]")
	WebElement payFPBOOK;

	// bankdeposit
	@FindBy(xpath = "(//div[@class = 'card-rounded white pl-3 pr-4 v-card v-sheet theme--dark'])[3]")
	WebElement bankDeposit;

	@FindBy(xpath = "((//div[@class='v-text-field__slot'])//input[@placeholder = 'Amount*'])[3]")
	WebElement enterAmountBANK;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[6]")
	WebElement errorMsgBANK;

	@FindBy(xpath = "(//span[contains(text(),'next')])[1]")
	WebElement payBANK;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot'])[5]")
	WebElement uploadImage;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[6]//input")
	WebElement utrID;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[7]//input")
	WebElement promoCode;

	@FindBy(xpath = "//button//span[contains(text(),'submit')]")
	WebElement submit;

	// astro
	@FindBy(xpath = "(//div[@class = 'card-rounded white pl-3 pr-4 v-card v-sheet theme--dark'])[4]")
	WebElement astroPay;

	@FindBy(xpath = "(//input[@placeholder = 'Coupon Code'])[3]")
	WebElement promoASTRO;

	@FindBy(xpath = "((//div[@class='v-text-field__slot'])//input[@placeholder = 'Amount*'])[4]")
	WebElement enterAmountASTRO;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[8]")
	WebElement errorMsgASTRO;

	@FindBy(xpath = "(//span[contains(text(),'Proceed To Pay')])[3]")
	WebElement payASTRO;

	// crypto
	@FindBy(xpath = "(//div[@class = 'card-rounded white pl-3 pr-4 v-card v-sheet theme--dark'])[5]")
	WebElement crypto;

	@FindBy(xpath = "(//span[contains(text(),'Pay via Crypto Currency')])[1]")
	WebElement payCRYPTO;

	// other
	@FindBy(xpath = "//div//div[@role = 'status']")
	WebElement popup;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])[1]")
	WebElement closePopup;

	@FindBy(xpath = "(//div[contains(text(),'My Transactions')])[1]")
	WebElement myTransactions;

	@FindBy(xpath = "(//div[@class = 'row no-gutters'])[3]")
	WebElement statusOfDeposit;

	@FindBy(xpath = "(//div[@class = 'row no-gutters'])[4]//div[@class = 'd-flex col col-6']")
	List<WebElement> depositTransactionDetails;

	@FindBy(xpath = "(//div[@class = 'row no-gutters'])[4]//div[@class = 'mb-1 col col-6']")
	WebElement reqDate;

	@FindBy(xpath = "//span[contains(text(), 'Profile')]")
	WebElement profile;

	@FindBy(xpath = "//div[@class='drawer-user-balance']//div[@class='v-list v-sheet theme--light v-list--dense']//div[@class = 'v-list-item theme--light']")
	List<WebElement> checkAmount;
	
	@FindBy(xpath = "//div[contains(text(),'No Transactions Found!')]")
	WebElement noTransactionFound;

	@FindBy(xpath = "(//div[@aria-label = 'Referral Whatsapp Link']//div[@class='v-responsive__sizer'])[1]")
	WebElement banner;

	@FindBy(xpath = "(//button[@type='button'])[11]")
	WebElement fpbook;

	public FairplayDepositPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public static String upi_Title;
	public static String netbanking_Title;
	public static String astropay_Title;
	public static String fp_Title;
	public static String crypto_Title;

	public void checkAmount() {
		for (WebElement amt : checkAmount) {
			String value = amt.getText();
			extentTest.info(value);
		}
	}

	public String depositNow() {
		try {
			obj.jSForClick(driver, depositeNow);
			extentTest.info("Deposite Now Is Clickable..!!!");
			return "Deposite Now Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("Deposite Now Is Not Clickable..!!!");
			return "Deposite Now Is Clickable..!!!";
		}
	}

	public int sizeOfDeposit() throws InterruptedException {
		int size = modesOfDeposit.size();
		extentTest.info("Types of Deposit : " + size);
		for (WebElement mode : modesOfDeposit) {
			ewait.visibilityOf(driver, mode, 4);
			obj.jSForClick(driver, mode);
			mode.click();
			Thread.sleep(1000);
		}
		return size;
	}

//	public void checkVisibilityOfFPBook() {
//		try {
//			Boolean value = fpbook.isDisplayed();
//			extentTest.info("FpBook Is Displayed On Deposit Page : " + value);
//		} catch (Exception e) {
//			extentTest.info("FpBook Is Not Displayed On Deposit Page...!!!");
//		}
//	}

	public Boolean checkVisibilityOfBanner() {
		try {
			Boolean value = banner.isDisplayed();
			extentTest.info("WhatsApp Banner Is Displayed On Deposit Page : " + value);
			return true;
		} catch (Exception e) {
			extentTest.info("WhatsApp Banner Is Not Displayed On Deposit Page..!!! ");
			return false;
		}
	}

	public void promoCode(String promo) {
		try {
			extentTest.info("Promo code : " + promo);
			ewait.visibilityOf(driver, popup, 2);
			extentTest.info("PopUp Message : " + popup.getText());
			closePopup.click();
		} catch (Exception e) {
			extentTest.info("Promo code is valid : " + promo);
		}
	}

	public String getPopUp() {
		try {
			ewait.visibilityOf(driver, popup, 2);
			String popupMsg = popup.getText();
			extentTest.info("PopUp Message : " + popupMsg);
			closePopup.click();
			return popupMsg;
		} catch (Exception e) {
			extentTest.info("PopUp Is Not Visible...!!!");
			return null;
		}
	}

	public void verifyAmount(WebElement ele) {
		try {
			ewait.visibilityOf(driver, ele, 2);
			String text = ele.getText();
			extentTest.info("Error MSG :" + text);
		} catch (Exception e) {
			extentTest.info("Entered Amount Is Valid..!!!");
		}
	}

	public void checkTransactions() {
		try {
			if ((!upi_Title.equals(null)) || (!netbanking_Title.equals(null)) || (!astropay_Title.equals(null))
					|| (!fp_Title.equals(null)) || (!crypto_Title.equals(null))) {
				try {
					obj.jSForScroll(driver, profile);
					ewait.visibilityOf(driver, profile, 2);
				// ewait.elementToBeClickable(driver, profile, 2);
					obj.jSForClick(driver, profile);
				} catch (Exception e) {
					extentTest.info("Profile Button Is Not Clickable..!!!");
				}
				try {
					obj.jSForScroll(driver, myTransactions);
					myTransactions.click();
				} catch (Exception e) {
					extentTest.info("Transaction Button Is Not Clickable..!!!");
				}
			} else {
				int i = 0;
				do {
					break;
				} while (i == 0);
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Check The Transaction Details..!!!");
		}
	}

	public void getTransactionDetails() {
		try {
			if ((!upi_Title.equals(null)) || (!netbanking_Title.equals(null)) || (!astropay_Title.equals(null))
					|| (!fp_Title.equals(null)) || (!crypto_Title.equals(null))) {
				extentTest.info(statusOfDeposit.getText());
				for (WebElement transaction : depositTransactionDetails) {
					extentTest.info(transaction.getText());
				}
				extentTest.info(reqDate.getText());
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Get The Transaction Details..!!!");
		}
	}

	@SuppressWarnings("finally")
	public String depositThroughUPI(String amt, String promo) throws InterruptedException {
		String title1 = null;
		ewait.elementToBeClickable(driver, upi, 2);
		obj.jSForClick(driver, upi);
		int length = amt.length();
		if (length < 6) {
			try {
				promoUPI.sendKeys(promo);
				Thread.sleep(1000);
				enterAmountUPI.sendKeys(amt);
				extentTest.info("Entered Amount is : " + amt);
				Thread.sleep(1000);
				promoCode(promo);
				Thread.sleep(1000);
				verifyAmount(errorMsgUPI);
				Thread.sleep(1000);
				title1 = proceedPaymentThroughUPI();
			} catch (Exception e) {
				extentTest.info("Unable to Make Payment..!!");
			} finally {
				FairplayDepositPage.upi_Title = title1;
				extentTest.info("Window Title :" + upi_Title);
				return upi_Title;
			}
		} else {
			extentTest.info(pay1lackAbove.getText());
			pay1lackAbove.click();
			try {
				promoUPI.sendKeys(promo);
				Thread.sleep(1000);
				enterAmountUPI.sendKeys(amt);
				extentTest.info("Entered Amount is : " + amt);
				Thread.sleep(1000);
				promoCode(promo);
				Thread.sleep(1000);
				verifyAmount(errorMsgUPI);
				Thread.sleep(1000);
				title1 = proceedPaymentThroughUPI();
			} catch (Exception e) {
				extentTest.info("Unable to Make Payment..!!");
			} finally {
				FairplayDepositPage.upi_Title = title1;
				extentTest.info("Window Title :" + upi_Title);
				return upi_Title;
			}
		}
	}

	@SuppressWarnings("finally")
	public String proceedPaymentThroughUPI() throws InterruptedException {
		String parentWindowHandle = driver.getWindowHandle();
		obj.jSForClick(driver, payUPI);
		Thread.sleep(2000);
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String windowHandle : windowHandles) {
				if (!windowHandle.equals(parentWindowHandle)) {
					driver.switchTo().window(windowHandle);
					Thread.sleep(2000);
					extentTest.info("Page URL : " + driver.getCurrentUrl());
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			extentTest.info("Page didnt got redirected..!!!");
		} finally {
			String title = driver.getTitle();
			if (!title.equals("Online Betting Site | Sports Betting in India | Fairplay")) {
				extentTest.info("Page redirected to : " + title);
				driver.close();
				driver.switchTo().window(parentWindowHandle);
				Thread.sleep(1000);
				return title;
			} else {
				int i = 0;
				do {
					break;
				} while (i == 0);
				return null;
			}
		}
	}

	@SuppressWarnings("finally")
	public String depositThroughNetBanking(String amt, String promo) throws InterruptedException {
		String title2 = null;
		obj.jSForClick(driver, netBanking);
		try {
			promoNET.sendKeys(promo);
			Thread.sleep(1000);
			enterAmountNET.sendKeys(amt);
			extentTest.info("Entered Amount is : " + amt);
			verifyAmount(errorMsgNET);
			Thread.sleep(1000);
			promoCode(promo);
			Thread.sleep(1000);
			title2 = proceedPaymentThroughNetBanking();
		} catch (Exception e) {
			extentTest.info("Unable to Make Payment..!!");
		} finally {
			FairplayDepositPage.netbanking_Title = title2;
			extentTest.info("Window Title :" + netbanking_Title);
			return netbanking_Title;
		}
	}

	@SuppressWarnings("finally")
	public String proceedPaymentThroughNetBanking() throws InterruptedException {
		String parentWindowHandle = driver.getWindowHandle();
		obj.jSForClick(driver, payNET);
		Thread.sleep(2000);
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String windowHandle : windowHandles) {
				if (!windowHandle.equals(parentWindowHandle)) {
					driver.switchTo().window(windowHandle);
					driver.findElement(By.xpath("//div//input[@name = 'first_name']")).sendKeys("Tejal");
					extentTest.info("Page URL : " + driver.getCurrentUrl());
				}
			}
		} catch (Exception e) {
			extentTest.info("Page didnt got redirected..!!!");
		} finally {
			String title = driver.getTitle();
			if (!title.equals("Online Betting Site | Sports Betting in India | Fairplay")) {
				extentTest.info("Page redirected to : " + title);
				driver.close();
				driver.switchTo().window(parentWindowHandle);
				Thread.sleep(1000);
				return title;
			} else {
				int i = 0;
				do {
					break;
				} while (i == 0);
				return null;
			}
		}
	}

	public void depositThroughBankDeposit(String amt) throws InterruptedException, IOException, AWTException {
		ewait.elementToBeClickable(driver, bankDeposit, 2);
		obj.jSForClick(driver, bankDeposit);
		try {
			enterAmountBANK.sendKeys(amt);
			extentTest.info("Entered Amount is : " + amt);
			verifyAmount(errorMsgBANK);
			Thread.sleep(1000);
			obj.jSForScroll(driver, payBANK);
			Thread.sleep(1000);
			payBANK.click();
			Thread.sleep(1000);
			String actualData = getPopUp();
			if (actualData.equals(GetGsonFairplayTestData.getConfigData().getBankDepositRestricted())) {
				int i = 0;
				do {
					break;
				} while (i == 0);
			} else {
				proceedPaymentThroughBankDeposit();
			}
		} catch (Exception e) {
			extentTest.info("Unable to Make Payment..!!");
		}
	}

	public void proceedPaymentThroughBankDeposit() throws InterruptedException, IOException, AWTException {
		obj.jSForScroll(driver, uploadImage);
		Thread.sleep(1000);
		try {
			uploadImage.click();
			Utilities.uploadImage();
			extentTest.info("Image Uploaded Successfully..!!!");
		} catch (Exception e) {
			extentTest.info("Not Able to Upload The Image..!!!");
		}

		try {
			String id = rand.randomize12DigitNumber();
			utrID.sendKeys(id);
			extentTest.info("UTR ID : " + id);
		} catch (Exception e) {
			extentTest.info("Not Able To Enter UTR ID..!!!");
		}

		try {
			ewait.elementToBeClickable(driver, submit, 2);
			Boolean btn = submit.isEnabled();
			submit.click();
			extentTest.info("Submit Button Is Enabled : " + btn);
		} catch (Exception e) {
			extentTest.info("Submit Button Is Not Clickable");
		}
	}

	@SuppressWarnings("finally")
	public String depositThroughAstropay(String amt, String promo) throws InterruptedException {
		String title3 = null;
		ewait.elementToBeClickable(driver, astroPay, 2);
		obj.jSForClick(driver, astroPay);
		try {
			promoASTRO.sendKeys(promo);
			Thread.sleep(1000);
			enterAmountASTRO.sendKeys(amt);
			extentTest.info("Entered Amount is : " + amt);
			promoCode(promo);
			Thread.sleep(1000);
			verifyAmount(errorMsgASTRO);
			Thread.sleep(1000);
			obj.jSForScroll(driver, payASTRO);
			Thread.sleep(1000);
			title3 = proceedPaymentThroughAstropay();
		} catch (Exception e) {
			extentTest.info("Unable to Make Payment..!!");
		} finally {
			FairplayDepositPage.astropay_Title = title3;
			extentTest.info("Window Title :" + astropay_Title);
			return astropay_Title;
		}
	}

	@SuppressWarnings({ "finally", "deprecation" })
	public String proceedPaymentThroughAstropay() throws InterruptedException {
		String parentWindowHandle = driver.getWindowHandle();
		ewait.elementToBeClickable(driver, payASTRO, 2);
		obj.jSForClick(driver, payASTRO);
		Thread.sleep(2000);
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String windowHandle : windowHandles) {
				if (!windowHandle.equals(parentWindowHandle)) {
					driver.switchTo().window(windowHandle);
					driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
					extentTest.info("Page URL : " + driver.getCurrentUrl());
					Thread.sleep(1000);
					driver.findElement(By.xpath("//div//input[@id = 'phone']")).sendKeys("8765768676");
				}
			}
		} catch (Exception e) {
			extentTest.info("Page didnt got redirected..!!!");
		} finally {
			String title = driver.getTitle();
			if (!title.equals("Online Betting Site | Sports Betting in India | Fairplay")) {
				extentTest.info("Page redirected to : " + title);
				driver.close();
				driver.switchTo().window(parentWindowHandle);
				Thread.sleep(1000);
				return title;
			} else {
				int i = 0;
				do {
					break;
				} while (i == 0);
				return null;
			}
		}
	}

	@SuppressWarnings("finally")
	public String depositThroughFPBook() throws InterruptedException {
		String title4 = null;
		ewait.elementToBeClickable(driver, fairplaybook, 2);
		try {
			fairplaybook.click();
			Thread.sleep(1000);
			title4 = proceedPaymentThroughFPBook();
		} catch (Exception e) {
			extentTest.info("Unable to Make Payment..!!");
		} finally {
			FairplayDepositPage.fp_Title = title4;
			extentTest.info("Window Title : " + fp_Title);
			return fp_Title;
		}
	}

	@SuppressWarnings("finally")
	public String proceedPaymentThroughFPBook() throws InterruptedException {
		String parentWindowHandle = driver.getWindowHandle();
		ewait.elementToBeClickable(driver, payFPBOOK, 2);
		obj.jSForClick(driver, payFPBOOK);
		Thread.sleep(2000);
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String windowHandle : windowHandles) {
				if (!windowHandle.equals(parentWindowHandle)) {
					driver.switchTo().window(windowHandle);
					extentTest.info("Page URL : " + driver.getCurrentUrl());
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			extentTest.info("Page didnt got redirected..!!!");
		} finally {
			String title = driver.getTitle();
			if (!title.equals("Online Betting Site | Sports Betting in India | Fairplay")) {
				extentTest.info("Page redirected to : " + title);
				driver.close();
				driver.switchTo().window(parentWindowHandle);
				Thread.sleep(1000);
				return title;
			} else {
				int i = 0;
				do {
					break;
				} while (i == 0);
				return null;
			}
		}
	}

	@SuppressWarnings("finally")
	public String depositThroughCrypto() throws InterruptedException {
		String title5 = null;
		ewait.elementToBeClickable(driver, crypto, 2);
		try {
			crypto.click();
			Thread.sleep(2000);
			title5 = proceedPaymentThroughCrypto();
		} catch (Exception e) {
			extentTest.info("Unable to Make Payment..!!");
		} finally {
			FairplayDepositPage.crypto_Title = title5;
			extentTest.info("Window Title :" + crypto_Title);
			crypto.click();
			return crypto_Title;
		}
	}

	@SuppressWarnings("finally")
	public String proceedPaymentThroughCrypto() throws InterruptedException {
		String parentWindowHandle = driver.getWindowHandle();
		ewait.elementToBeClickable(driver, payCRYPTO, 2);
		obj.jSForClick(driver, payCRYPTO);
		Thread.sleep(2000);
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			for (String windowHandle : windowHandles) {
				if (!windowHandle.equals(parentWindowHandle)) {
					driver.switchTo().window(windowHandle);
					Thread.sleep(1000);
					extentTest.info("Page URL : " + driver.getCurrentUrl());
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			extentTest.info("Page didnt got redirected..!!!");
		} finally {
			String title = driver.getTitle();
			if (!title.equals("Online Betting Site | Sports Betting in India | Fairplay")) {
				extentTest.info("Page redirected to : " + title);
				driver.close();
				driver.switchTo().window(parentWindowHandle);
				Thread.sleep(1000);
				return title;
			} else {
				int i = 0;
				do {
					break;
				} while (i == 0);
				return null;
			}
		}
	}

}
