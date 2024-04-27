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

public class FairplayForgotPassword extends BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "//a[contains(text(),'Forgot Password?')]")
	WebElement forgotPassword;

	@FindBy(xpath = "//span[contains(text(),'Forgot Password ?')]")
	WebElement forgotPasswordLoginPage;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[1]//input")
	WebElement enterNumber;

	@FindBy(xpath = "(//div[@class='v-select__selections'])[1]")
	WebElement selCountryCode;

	@FindBy(xpath = "(//div[@role = 'listbox'])//div")
	List<WebElement> listCountrCode;

	@FindBy(xpath = "(//button//span[normalize-space()='Forgot Password'])[1]")
	WebElement forgotPasswordButton;

	@FindBy(xpath = "//div//div[@role = 'status']")
	WebElement popup;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])[1]")
	WebElement closePopup;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[2]//input")
	WebElement enterOtp;

	@FindBy(xpath = "//div[@class='v-messages__message message-transition-enter-to']")
	WebElement errorMsgForOtp;

	//// div[@class='v-messages__message message-transition-enter-to']

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[3]//input")
	WebElement newPassword;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[3]")
	WebElement errorNewPassword;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[4]//input")
	WebElement confirmPassword;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[4]")
	WebElement errorConfirmPassword;

	@FindBy(xpath = "(//form//button[@type='button'])[3]")
	WebElement changePassword;

	@FindBy(xpath = "//span[contains(text(),'Resend Code')]")
	WebElement resendOtp;

	@FindBy(xpath = "//span[@class = 'sms-not-valid']")
	WebElement errorMsg;

	public FairplayForgotPassword(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private String username;
	private String otp;
	private String pwd;

	public void forgotPassword() {
		try {
			forgotPassword.click();
		} catch (Exception e) {
			extentTest.info("Forgot Password Is Not Clickable...!!!");
		}
	}

	public void forgotPasswordLoginPage() {
		try {
			forgotPasswordLoginPage.click();
		} catch (Exception e) {
			extentTest.info("Forgot Password On loginPage Is Not Clickable...!!!");
		}
	}

	public void contryCode(String code) {
		selCountryCode.click();
		for (WebElement countrycode : listCountrCode) {
			String codetext = countrycode.getText();
			if (codetext.equals(code)) {
				extentTest.info("Country code is : " + countrycode.getText());
				countrycode.click();
				ewait.elementToBeClickable(driver, countrycode, 4);
			}
		}
	}

	public String getUsernumber() throws IOException {
		username = GetGsonFairplayTestData.getData().getUsername();
		return username;
	}

	public void setUsernumber(String usernumber) {
		this.username = usernumber;
		try {
			enterNumber.sendKeys(usernumber);
			extentTest.info("Mobile Number : " + usernumber);
		} catch (Exception e) {
			extentTest.info("Not Able To Enter UserNumber..!!!");
		}
	}

	public void forgotPasswordButton() {
		try {
			extentTest.info("Forgot Password Is Clickable : " + forgotPasswordButton.isDisplayed());
			forgotPasswordButton.click();
			ewait.visibilityOf(driver, popup, 4);
		} catch (Exception e) {
			extentTest.info("Forgot Password Button Is Not Clickable..!!!");
		}
	}

	@SuppressWarnings("finally")
	public String popup() {
		ewait.visibilityOf(driver, popup, 4);
		String popuptext = popup.getText();
		try {
			if (!(popuptext.equals("OTP sent Successfully"))) {
				extentTest.info("Verification Message : " + popuptext);
				Thread.sleep(1000);
				closePopup.click();
			} else {
				extentTest.info("Verification Message : " + popuptext);
				Thread.sleep(1000);
				closePopup.click();
			}
		} catch (Exception e) {
			extentTest.info("Popup Is Not Visible...!!!");
		} finally {
			return popuptext;
		}
	}

	public String getOtp() throws Exception {
		String apiUrl = GetGsonFairplayTestData.getConfigData().getUserAPIUrl();

		String requestBody = "{\"email\":\"0174802571@fairplay.club\"}";

		String accessToken = GetGsonFairplayTestData.getToken().getAccessToken();

		String desiredKey = GetGsonFairplayTestData.getConfigData().getData();

		String desiredKey1 = GetGsonFairplayTestData.getConfigData().getMobileVerification();

		otp = OtpExtractor.sendPostRequest(apiUrl, accessToken, requestBody, desiredKey, desiredKey1);
		System.out.println(otp);
		return otp;
	}

	public String setOtp(String otp) {
		this.otp = otp;
		int length = otp.length();
		if (length < 6) {
			try {
				enterOtp.sendKeys(otp);
				extentTest.info("OTP : " + otp);
				extentTest.info("OTP Error Msg : " + errorMsgForOtp.getText());
				return errorMsgForOtp.getText();
			} catch (Exception e) {
				extentTest.info("OTP Error Msg : " + "Not Able To Enter OTP..!!!");
				int i = 0;
				do {
					break;
				} while (i == 0);
				return "Not Able To Enter OTP..!!!";
			}
		} else if (length > 6) {
			try {
				enterOtp.sendKeys(otp);
				extentTest.info("OTP : " + otp);
				extentTest.info("OTP Error Msg : " + errorMsgForOtp.getText());
				return errorMsgForOtp.getText();
			} catch (Exception e) {
				extentTest.info("OTP Error Msg : " + "Not Able To Enter OTP..!!!");
				int i = 0;
				do {
					break;
				} while (i == 0);
				return "Not Able To Enter OTP..!!!";
			}
		} else {
			try {
				enterOtp.sendKeys(otp);
				extentTest.info("OTP : " + otp);
				return null;
			} catch (Exception e) {
				int i = 0;
				do {
					break;
				} while (i == 0);
				return "Not Able To Enter OTP..!!!";
			}
		}

//		enterOtp.sendKeys(otp);
//		extentTest.info("OTP : " + otp);
//		try {
//			extentTest.info("OTP Error Msg : " + errorMsgForOtp.getText());
//		} catch (Exception e) {
//			int i = 0;
//			do {
//				continue;
//			} while (i == 0);
//		}
	}

	public void clearOtp() {
		try {
			obj.doubleClick_delete(driver, enterOtp);
		} catch (Exception e) {
			extentTest.info("Not Able To Clear The OTP Field..!!!");
		}
	}

	public String getStrongPassword() throws IOException {
		try {
			pwd = rand.randomizeStrongPassword();
		} catch (Exception e) {
			extentTest.info("Not Able To Enter The Password..!!!");
		}
		return pwd;
	}

	public String getWeakPassword() throws IOException {
		try {
			pwd = rand.randomizeWeakPassword();
		} catch (Exception e) {
			extentTest.info("Not Able To Enter The Password..!!!");
		}
		return pwd;
	}

	public String setPassword(String pwd) {
		this.pwd = pwd;
		newPassword.sendKeys(pwd);
		extentTest.info("New Password : " + pwd);
		try {
			String errorMsg = errorNewPassword.getText();
			extentTest.info("Password error message : " + errorMsg);
			return errorMsg;
		} catch (Exception e) {
			int i = 0;
			do {
				continue;
			} while (i == 0);
			return null;
		}
	}

	public String setConfirmPassword(String pwd) {
		this.pwd = pwd;
		confirmPassword.sendKeys(pwd);
		extentTest.info("Confirm Password : " + pwd);
		try {
			String errorMsg = errorConfirmPassword.getText();
			extentTest.info("Confirm Password error message : " + errorMsg);
			return errorMsg;
		} catch (Exception e) {
			int i = 0;
			do {
				continue;
			} while (i == 0);
			return null;
		}
	}

	@SuppressWarnings("finally")
	public Boolean changePassword() {
		boolean btn = changePassword.isEnabled();
		extentTest.info("Change Password Is Enabled : " + btn);
		try {
			ewait.elementToBeClickable(driver, changePassword, 4);
			changePassword.click();
			extentTest.info("Change Password Button Is Clickable..!!!");
		} catch (Exception e) {
			extentTest.info("Change Password Button Is Not Clickable..!!!");
		} finally {
			return btn;
		}
	}

	@SuppressWarnings("finally")
	public String succPopup() {
		ewait.visibilityOf(driver, popup, 4);
		String popupMsg = popup.getText();
		try {
			if (!(popupMsg.equals("Password Changed Successfully"))) {
				extentTest.info("Verification Message : " + popupMsg);
				closePopup.click();
			} else {
				extentTest.info("Verification Message : " + popupMsg);
				closePopup.click();
			}
		} catch (Exception e) {
			extentTest.info("Popup Is Not Visible...!!!");
		} finally {
			return popupMsg;
		}
	}

	// @SuppressWarnings("deprecation")
	public void resendCode() {

		try {
			resendOtp.click();
			// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			try {
				extentTest.info("Error Message : " + errorMsg.getText());
			} catch (Exception e) {
				int i = 0;
				do {
					continue;
				} while (i == 0);
			}
		} catch (Exception e) {
			extentTest.info("Resend OTP Is Not Clickable..!!!");
		}
	}

}
