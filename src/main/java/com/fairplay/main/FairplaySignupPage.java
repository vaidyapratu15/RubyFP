package com.fairplay.main;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fairplay.library.ActionClass;
import com.fairplay.library.ExplicitWaits;
import com.fairplay.library.FluentWaitsClass;
import com.fairplay.library.OtpExtractor;
import com.fairplay.library.RandomizeData;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

/*
 * @Author: Tejal Gavade.
 * @Since : December 2022
 * @Discription : For Negative credentials user should not able to do login and all respective error message should able to fetch.
 *                For Positive credentials user should able to login successfully.
 */

public class FairplaySignupPage extends BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "//body/div/div/div[@data-app='true']/div/div/header[@data-booted='true']/div/div/span/form[@novalidate='novalidate']/a[1]")
	WebElement joinNowBtn;

//	@FindBy(xpath = "//div[@class = 'v-card v-sheet theme--dark paddWhats']")
//	WebElement whatsApp;

	@FindBy(xpath = "//div[@justify]//div[contains(text(),'Now create an account easily on whatsapp!')]")
	WebElement whatsApp;

	@FindBy(xpath = "//div[@class = 'pos-abos whatsappicon']")
	WebElement whatsAppIcon;

	@FindBy(xpath = "//div[@class = 'pos-abos Telegramicon']")
	WebElement telegramIcon;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[1]")
	WebElement enterName;

	@FindBy(xpath = " //div[contains(text(),'name should be atleast 2 characters')]")
	WebElement errMsgName;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[2]")
	WebElement enterNumber;

	@FindBy(xpath = "//div[contains(text(),'Mobile Number is not valid')]")
	WebElement errorMsgNumber;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[3]")
	WebElement enterPassword;

	@FindBy(xpath = "(//div[@class = 'v-messages__wrapper'])[4]")
	WebElement errMsgPwd;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[4]")
	WebElement confirmPassword;

	@FindBy(xpath = "(//div[@class = 'v-messages__wrapper'])[5]")
	WebElement errMsgConfirmPwd;

//	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[6]")
//	WebElement enterAffiliateCode;

	@FindBy(xpath = "//input[@placeholder='Affiliate Code']")
	WebElement enterAffiliateCode;

	@FindBy(xpath = "//input[@placeholder='Referral Code']")
	WebElement referralCode;

	@FindBy(xpath = "//button//span[contains(text(),'Register')]")
	WebElement register;

	// https://kheljaa.com/registration2/
	// https://kheljaa.com/registration
	// https://kheljaa.com/quick-signup-2/

	@FindBy(xpath = "//input[@type='number']")
	WebElement enterOtp;

	@FindBy(xpath = "//div[@class='v-messages__message message-transition-enter-to']")
	WebElement errorMsgForOtp;

	// for https://kheljaa.com/fp-registration/
	@FindBy(xpath = "(//div//input[@id='input-39'])[1]")
	WebElement enterOtp1;

	@FindBy(xpath = "//button[@id='confirmBtn']")
	WebElement confirmBtn;

	@FindBy(xpath = "//div[contains(text(),'Signed-Up Successfully.')]")
	WebElement successfulMsg;

	@FindBy(xpath = "//div[contains(text(),'Incorrect OTP, Please Provide Valid OTP!!!')]")
	WebElement otpPopup;

	@FindBy(xpath = "//div[@role = 'status']")
	WebElement popup;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])[1]")
	WebElement closePopup;

	@FindBy(xpath = "//div[@class = 'v-messages__message pl-left']")
	WebElement errMsg;

	@FindBy(xpath = "(//button//span[contains(text(), 'Accept and Continue')])[2]")
	WebElement acceptCookie;

	@FindBy(xpath = "//div[@class = 'v-input--selection-controls__ripple']")
	WebElement clickCheckBox;

	@FindBy(xpath = "(//button//span[contains(text(), 'Accept and Continue')])[1]")
	WebElement acceptAndContinue;

	@FindBy(xpath = "//div[@class = 'title']")
	WebElement title;

	@FindBy(xpath = "//span[contains(text(),'Profile')]")
	WebElement profile;

	@FindBy(xpath = "(((//div[contains(text(),'Bonus')])[1])//following::div)[1]")
	WebElement bonus;
	
	@FindBy(xpath = "//div[@class='v-list v-sheet theme--light v-list--dense']//div[@class = 'v-list-item theme--light']")
	List<WebElement> walletDetails;

	@FindBy(xpath = "//span[contains(text(), 'Logout')]")
	WebElement logout;

	@FindBy(xpath = "(//div[@class='v-select__selections'])[1]")
	WebElement selCountryCode;

	@FindBy(xpath = "(//div[@role = 'listbox'])//div")
	List<WebElement> listCountryCode;

	public FairplaySignupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private String name;
	private String number;
	private String pwd;
	private String otp;
	private String query;
	public static String affiliatecode;

	String[] affiliatesCodeArray = { "dgcas", "dg_quicksb", "dg_quickCas", "dg_sb", "new_casdg", "ZEB", "test_dg", "dg",
			"dg_fpta", "dg_fptb", "dg_fptc", "dg_fptp", "dg_fptd", "dg_fpte", "dg_fptf", "dg_fptg", "dg_fpth",
			"dg_fptj", "dg_fptz", "dg_fptx", "dg_fptv", "dg_fptn", "dg_fptm", "dg_fptl", "dg_fptk", "dg_fptq",
			"dg_fptw", "dg_fptr", "dg_fptt", "dg_fptu", "dg_fpt", "dg_fpti" };


	@SuppressWarnings({ "finally" })
	public String checkUrl() {
		String affiliatecode = null;
		String url = driver.getCurrentUrl();
		extentTest.info("URL : " + url);
		try {
			if (url.contains("btag")) {
				int index = url.indexOf("=") + 1;
				affiliatecode = url.substring(index);
			}
		} catch (Exception e) {
			affiliatecode = null;
			int i = 0;
			do {
				break;
			} while (i == 0);
		} finally {
			FairplaySignupPage.affiliatecode = affiliatecode;
			joinNow();
			try {
				for (int i = 0; i < affiliatesCodeArray.length; i++) {
					if (affiliatecode.equals(affiliatesCodeArray[i])) {
						extentTest.info(affiliatecode + " " + "Is Client Provided Affiliate Code..!!!");
						int j = 0;
						do {
							break;
						} while (j == 0);
					}
				}
			} finally {
				return affiliatecode;
			}
		}
	}
	
	public void joinNow() {
		try {
			joinNowBtn.click();
		} catch (Exception e) {
			extentTest.info("JoinNow Is Not Visible..!!!");
		}
	}

	public void scrollTillRegister() {
		ewait.visibilityOf(driver, register, 6);
		obj.jSForScroll(driver, register);
	}

	public Boolean checkWhatsApp() {
		try {
			Boolean data = whatsApp.isDisplayed();
			extentTest.info("WhatsApp Is Displayed On Registration Page : " + data);
			return true;
		} catch (Exception e) {
			extentTest.info("WhatsApp Is Not Displayed On Registration Page...!!!");
			return false;
		}
	}

	public Boolean checkWhatsAppIcon() {
		try {
			Boolean data = whatsAppIcon.isDisplayed();
			extentTest.info("WhatsAppIcon Is Displayed On Registration Page : " + data);
			return true;
		} catch (Exception e) {
			extentTest.info("WhatsAppIcon Is Not Displayed On Registration Page...!!!");
			return false;
		}
	}

	public Boolean checkTelegramIcon() {
		try {
			Boolean data = telegramIcon.isDisplayed();
			extentTest.info("TelegramIcon Is Displayed On Registration Page : " + data);
			return true;
		} catch (Exception e) {
			extentTest.info("TelegramIcon Is Not Displayed On Registration Page...!!!");
			return false;
		}
	}

	public String getName() {
		name = rand.randomizeName();
		return name;
	}

	public void setName(String name) {
		this.name = name;
		enterName.sendKeys(name);
		extentTest.info("Username : " + name);
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

	public String getNumber() {
		number = rand.randomizeNumber();
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
		enterNumber.sendKeys(number);
		extentTest.info("Number : " + number);
	}

	@SuppressWarnings("finally")
	public String getStrongPassword() throws IOException {
		pwd = rand.randomizeStrongPassword();
		enterPassword.sendKeys(pwd);
		extentTest.info("Password : " + pwd);
		try {
			extentTest.info("Password error message : " + errMsgPwd.getText());
		} catch (Exception e) {
			int i = 0;
			do {
				continue;
			} while (i == 0);
		} finally {
			return pwd;
		}
	}

	@SuppressWarnings("finally")
	public String getWeakPassword() throws IOException {
		pwd = rand.randomizeWeakPassword();
		enterPassword.sendKeys(pwd);
		extentTest.info("Password : " + pwd);
		try {
			extentTest.info("Password error message : " + errMsgPwd.getText());
		} catch (Exception e) {
			int i = 0;
			do {
				continue;
			} while (i == 0);
		} finally {
			return pwd;
		}
	}

	public void setConfirmPassword(String pwd) {
		this.pwd = pwd;
		confirmPassword.sendKeys(pwd);
		extentTest.info("Confirm Password : " + pwd);
		try {
			extentTest.info("Confirm Password error message : " + errMsgConfirmPwd.getText());
		} catch (Exception e) {
			int i = 0;
			do {
				continue;
			} while (i == 0);
		}
	}

	public void checkAffiliateField(String code) {
		try {
			Boolean data = enterAffiliateCode.isDisplayed();
			if (data.equals(true)) {
				//String code = signuppage.getAffiliateCode();
				signuppage.setAffiliateCode(code);
			}
		} catch (Exception e) {
			extentTest.info("Affiliate Field Is Disabled..!!!");
		}
	}

	public String getAffiliateCode(String code) {
		return code;
	}

	public void setAffiliateCode(String code) {
		//System.out.println(code);
		enterAffiliateCode.sendKeys(code);
		extentTest.info("Affiliate Code : " + code);
		try {
			ewait.elementToBeClickable(driver, referralCode, 2);
			referralCode.click();
			extentTest.info("Referral Filed Is Clickable..!!!");
		} catch (Exception e) {
			extentTest.info("Referral Field Is Not Clickable..!!!");
		}
	}

	public Boolean clickRegister() throws IOException {
		Boolean btn = register.isEnabled();
		try {
			ewait.elementToBeClickable(driver, register, 4);
			register.click();
			extentTest.info("Register button is enabled : " + btn);
			return btn;
		} catch (Exception e) {
			extentTest.info("Register Button Is Enabled : " + "False");
			return false;
		}
	}

	@SuppressWarnings("finally")
	public String popup() {
		ewait.visibilityOf(driver, popup, 6);
		String popupMsg = popup.getText();
		try {
			String expectedPopUp = GetGsonFairplayTestData.getConfigData().getUserRegistered();
			if (popupMsg.equals(expectedPopUp)) {
				extentTest.info("Verification message : " + popupMsg);
				Thread.sleep(1000);
				closePopup.click();
			} else {
				extentTest.info("Verification message : " + popupMsg);
				Thread.sleep(1000);
				closePopup.click();
				int i = 0;
				do {
					break;
				} while (i == 0);
			}
		} catch (Exception e) {
			extentTest.info("Verification message : " + "Popup Is Not Visible");
		} finally {
			return popupMsg;
		}
	}

	public String getQuery(String number, String mailhandle) {
		this.number = number;
		query = number + mailhandle;
		String reqBody = "{\"email\" : " + "\"" + query + "\"}";
		System.out.println(reqBody);
		return reqBody;
	}

	public String getQueryAE(String number, String mailhandle) {
		this.number = number;
		query = number + mailhandle;
		String reqBody = "{\"email\" : " + "\"" + query + "\"}";
		System.out.println(reqBody);
		return reqBody;
	}

	public String setQuery(String reqBody) throws Exception {

		String apiUrl = GetGsonFairplayTestData.getConfigData().getUserAPIUrl();

		String requestBody = reqBody;
		System.out.println(requestBody);

		String accessToken = GetGsonFairplayTestData.getToken().getAccessToken();

		String desiredKey = GetGsonFairplayTestData.getConfigData().getData();

		String desiredKey1 = GetGsonFairplayTestData.getConfigData().getMobileVerification();

		otp = OtpExtractor.sendPostRequest(apiUrl, accessToken, requestBody, desiredKey, desiredKey1);
		System.out.println(otp);
		return otp;
	}

	public void affiliateCode(String reqBody) throws Exception {
		String apiUrl = GetGsonFairplayTestData.getConfigData().getUserAPIUrl();

		String requestBody = reqBody;
		System.out.println(requestBody);

		String accessToken = GetGsonFairplayTestData.getToken().getAccessToken();

		String desiredKey = GetGsonFairplayTestData.getConfigData().getData();

		String desiredKey1 = GetGsonFairplayTestData.getConfigData().getAffiliateCode();

		String code = OtpExtractor.sendPostRequest(apiUrl, accessToken, requestBody, desiredKey, desiredKey1);
		System.out.println(code);
		extentTest.info("Affiliate Code Stored In DataBase Is : " + code);
	}

	public String setOtp(String otp) {
		this.otp = otp;
		try {
			enterOtp.sendKeys(otp);
			extentTest.info("OTP : " + otp);
			try {
				extentTest.info("Error Message For OTP : " + errorMsgForOtp.getText());
				return errorMsgForOtp.getText();
			} catch (Exception e) {
				extentTest.info("OTP Is Valid..!!!");
				return "OTP Is Valid..!!!";
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Enter OTP..!!!");
			return "Not Able To Enter OTP..!!!";
		}
	}

	public String setOtp1(String otp) {
		this.otp = otp;
		try {
			enterOtp1.sendKeys(otp);
			extentTest.info("OTP : " + otp);
			try {
				extentTest.info("Error Message For OTP : " + errorMsgForOtp.getText());
				return errorMsgForOtp.getText();
			} catch (Exception e) {
				extentTest.info("OTP Is Valid..!!!");
				return "OTP Is Valid..!!!";
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Enter OTP..!!!");
			return "Not Able To Enter OTP..!!!";
		}
	}

	public Boolean clickConfirm() throws IOException {
		Boolean btn = confirmBtn.isEnabled();
		try {
			ewait.elementToBeClickable(driver, confirmBtn, 4);
			confirmBtn.click();
			extentTest.info("ConfirmBtn Button Is Enabled : " + btn);
			return btn;
		} catch (Exception e) {
			extentTest.info("ConfirmBtn Button Is Enabled : " + "False");
			return false;
		}
	}

	@SuppressWarnings("finally")
	public String signup() throws InterruptedException {
		//ewait.visibilityOf(driver, popup, 4);
		String popupMsg = popup.getText();
		try {
			String expectedPopup = GetGsonFairplayTestData.getConfigData().getSignUpSuccessufulMsg();
			if (popupMsg.equals(expectedPopup)) {
				extentTest.info("Verification Message For SignUp :" + popupMsg);
				Thread.sleep(1000);
				closePopup.click();
			} else {
				extentTest.info("Verification Message For SignUp :" + popupMsg);
				Thread.sleep(1000);
				closePopup.click();
				int i = 0;
				do {
					break;
				} while (i == 0);
			}
//		} catch (Exception e) {
//			extentTest.info("PopUp Is Not Visible..!!!");
		} finally {
			return popupMsg;
		}

	}

	public Boolean acceptCookie() {
		Boolean btn = acceptCookie.isEnabled();
		try {
			ewait.elementToBeClickable(driver, acceptCookie, 2);
			acceptCookie.click();
			extentTest.info("Accept cookie is clicked : " + btn);
			return true;
		} catch (Exception e) {
			extentTest.info("Accept cookie is clicked : False");
			return false;
		}
	}

	public Boolean clickCheckBox() {
		try {
			ewait.elementToBeClickable(driver, clickCheckBox, 2);
			clickCheckBox.click();
			extentTest.info("CheckBox is clicked");
			return true;
		} catch (Exception e) {
			extentTest.info("CheckBox is clicked : False");
			return false;
		}
	}

	public Boolean acceptAndContinue() {
		Boolean btn = acceptAndContinue.isEnabled();
		try {
			ewait.elementToBeClickable(driver, acceptAndContinue, 2);
			acceptAndContinue.click();
			extentTest.info("acceptAndContinue is clicked : " + btn);
			return true;
		} catch (Exception e) {
			extentTest.info("acceptAndContinue is clicked : False");
			return false;
		}
	}

	public Boolean profileClick() {
		try {
			profile.click();
			extentTest.info("Profile Button : " + profile.getText());
			return true;
		} catch (Exception e) {
			extentTest.info("Profile Button Is Not Clickable");
			return false;
		}
	}

	public void getWalletDetails() {
		for (WebElement Amt : walletDetails) {
			extentTest.info(Amt.getText());
		}
	}
	
	public void bonus() {
		try {
			String bonusvalue = bonus.getText();
			extentTest.info("Bonus : " + bonusvalue);
		} catch (Exception e) {
			extentTest.info("Bonus is Not Availabe");
		}
	}

	public Boolean logout() {
		obj.jSForScroll(driver, logout);
		try {
			logout.click();
			String logout = driver.getTitle();
			extentTest.info(logout);
			return true;
		} catch (Exception e) {
			extentTest.info("User Is Not Able To Do Logout..!!!");
			return false;
		}
	}

}
