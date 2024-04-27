package com.fairplay.main;

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

public class FairplayChangeMobileNumberPage extends BaseClass {

	ActionClass obj = new ActionClass();
	RandomizeData rand = new RandomizeData();
	ExplicitWaits ewait = new ExplicitWaits();
	FluentWaitsClass fwaits = new FluentWaitsClass();

	@FindBy(xpath = "//div[contains(text(),'Change Mobile No')]")
	WebElement changeNumber;

	@FindBy(xpath = "//input[@type = 'number']")
	WebElement newNumber;

	@FindBy(xpath = "//div[@class = 'v-messages__message message-transition-enter-to']")
	WebElement errorMsgForNewNumber;

	@FindBy(xpath = "//button//span[contains(text(), 'Next')]")
	WebElement nextBtn;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//following::input)[1]")
	WebElement oldOTP;

	@FindBy(xpath = "(//div[@class = 'v-messages__wrapper'])[2]//div")
	WebElement errorMsgForOldOtp;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//following::input)[2]")
	WebElement newOTP;

	@FindBy(xpath = "(//div[@class = 'v-messages__wrapper'])[3]//div")
	WebElement errorMsgForNewOtp;

	@FindBy(xpath = "//button//span[contains(text(),'Submit')]")
	WebElement submit;

	@FindBy(xpath = "//div[@role = 'status']")
	WebElement popup;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])[1]")
	WebElement closePopup;

	@FindBy(xpath = ("//i[@class='v-icon notranslate mdi mdi-reload theme--dark white--text']"))
	WebElement refresh;

	@FindBy(xpath = "((//div[@class = 'v-select__slot'])[3]//div)[4]")
	WebElement selCountryCode;

	@FindBy(xpath = "//div[@role = 'listbox']//div")
	List<WebElement> listCountryCode;

	private String username;
	private String oldotp;
	private String newotp;

	@SuppressWarnings("static-access")
	public FairplayChangeMobileNumberPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnChangeNumber() {
		try {
			obj.jSForScroll(driver, changeNumber);
			changeNumber.click();
		} catch (Exception e) {
			extentTest.info("Change Number Is Not Clickable..!!!");
		}
	}

	public void contryCode(String code) {
		selCountryCode.click();
		for (WebElement countrycode : listCountryCode) {
			String codetext = countrycode.getText();
			if (codetext.equals(code)) {
				extentTest.info("Country code is : " + countrycode.getText());
				ewait.elementToBeClickable(driver, countrycode, 4);
				countrycode.click();
			}
		}
	}

	public String getNewNumber() {
		username = rand.randomizeNumber();
		return username;
	}

	public String setNewNumber(String username) {
		this.username = username;
		int length = username.length();
		if (length < 8) {
			newNumber.sendKeys(username);
			extentTest.info("New Number :" + username);
			String errorMsg = errorMsgForNewNumber.getText();
			extentTest.info("Verification Message For Number : " + errorMsg);
			return errorMsg;
		} else {
			try {
				newNumber.sendKeys(username);
				extentTest.info("New Number :" + username);
				return null;
			} catch (Exception e) {
				extentTest.info("Not Able To Enter Number..!!!");
				refresh.click();
				return "Not Able To Enter Number..!!!";
			}
		}
	}

	public Boolean clickNext() throws InterruptedException {
		Thread.sleep(1000);
		try {
			ewait.elementToBeClickable(driver, nextBtn, 4);
			nextBtn.click();
			extentTest.info("Next Button Is Clickable..!!!");
			return true;
		} catch (Exception e) {
			extentTest.info("Next Button Is Not Clickable..!!!");
			try {
				refresh.click();
			} catch (Exception ex) {
				extentTest.info("Refresh Button Is Not Clickable..!!!");
			}
			return false;
		}
	}

	public String getOldOtp() throws Exception {

		String apiUrl = GetGsonFairplayTestData.getConfigData().getUserAPIUrl();

		String requestBody = "{\"email\":\"0174802571@fairplay.club\"}";

		String accessToken = GetGsonFairplayTestData.getToken().getAccessToken();

		String desiredKey = GetGsonFairplayTestData.getConfigData().getData();

		String desiredKey1 = GetGsonFairplayTestData.getConfigData().getMobileVerification();

		oldotp = OtpExtractor.sendPostRequest(apiUrl, accessToken, requestBody, desiredKey, desiredKey1);

		return oldotp;
	}

	public String setOldOtp(String oldotp) {
		this.oldotp = oldotp;
		int length = oldotp.length();
		if (length < 6) {
			try {
				oldOTP.sendKeys(oldotp);
				extentTest.info("Old OTP :" + oldotp);
				String errorMsg = errorMsgForOldOtp.getText();
				extentTest.info("Verification Message For OTP : " + errorMsg);
				return errorMsg;
			} catch (Exception e) {
				extentTest.info("Not Able To Enter Old OTP..!!!");
				return "Not Able To Enter Old OTP..!!!";
			}
		} else if (length > 6) {
			try {
				oldOTP.sendKeys(oldotp);
				extentTest.info("Old OTP :" + oldotp);
				String errorMsg = errorMsgForOldOtp.getText();
				extentTest.info("Verification Message For OTP : " + errorMsg);
				return errorMsg;
			} catch (Exception e) {
				extentTest.info("Not Able To Enter Old OTP..!!!");
				return "Not Able To Enter Old OTP..!!!";
			}
		} else {
			try {
				oldOTP.sendKeys(oldotp);
				extentTest.info("Old OTP :" + oldotp);
				extentTest.info("Verification Message For OTP : " + "User Entered 6 Digit OTP..!!!");
				return "User Entered 6 Digit OTP..!!!";
			} catch (Exception e) {
				extentTest.info("Not Able To Enter Old OTP..!!!");
				return "Not Able To Enter Old OTP..!!!";
			}
		}
	}

	public String getNewOtp() throws Exception {

		String apiUrl = GetGsonFairplayTestData.getConfigData().getUserAPIUrl();

		String requestBody = "{\"email\":\"0174802571@fairplay.club\"}";

		String accessToken = GetGsonFairplayTestData.getToken().getAccessToken();

		String desiredKey = GetGsonFairplayTestData.getConfigData().getData();

		String desiredKey1 = GetGsonFairplayTestData.getConfigData().getNewMobileNoOtp();

		newotp = OtpExtractor.sendPostRequest(apiUrl, accessToken, requestBody, desiredKey, desiredKey1);

		return newotp;
	}

	public String setNewOtp(String newotp) {
		this.newotp = newotp;
		int length = newotp.length();
		if (length < 6) {
			try {
				newOTP.sendKeys(newotp);
				extentTest.info("New OTP :" + newotp);
				String errorMsg = errorMsgForNewOtp.getText();
				extentTest.info("Verification Message For OTP : " + errorMsg);
				return errorMsg;
			} catch (Exception e) {
				extentTest.info("Not Able To Enter New OTP..!!!");
				return "Not Able To Enter New OTP..!!!";
			}
		} else if (length > 6) {
			try {
				newOTP.sendKeys(newotp);
				extentTest.info("New OTP :" + newotp);
				String errorMsg = errorMsgForNewOtp.getText();
				extentTest.info("Verification Message For OTP : " + errorMsg);
				System.out.println(errorMsg);
				return errorMsg;
			} catch (Exception e) {
				extentTest.info("Not Able To Enter New OTP..!!!");
				return "Not Able To Enter New OTP..!!!";
			}
		} else {
			try {
				newOTP.sendKeys(newotp);
				extentTest.info("New OTP :" + newotp);
				extentTest.info("Verification Message For OTP : " + "User Entered 6 Digit OTP..!!!");
				return "User Entered 6 Digit OTP..!!!";
			} catch (Exception e) {
				extentTest.info("Not Able To Enter New OTP..!!!");
				return "Not Able To Enter New OTP..!!!";
			}
		}

	}

	public Boolean submit() {
		try {
			submit.click();
			extentTest.info("Submit Button Is Clickable..!!!");
			return true;
		} catch (Exception e) {
			extentTest.info("Submit Button Is Not Clickable..!!!");
			try {
				refresh.click();
			} catch (Exception ex) {
				extentTest.info("Refresh Button Is Not Clickable..!!!");
			}
			return false;

		}
	}

	@SuppressWarnings("finally")
	public String popup() {
		ewait.visibilityOf(driver, popup, 4);
		String popupMsg = popup.getText();
		extentTest.info("Verification Message: " + popupMsg);
		closePopup.click();
		try {
			if (popupMsg.equals(GetGsonFairplayTestData.getConfigData().getExistingNumber())
					|| popupMsg.equals("Invalid Otp")) {
				try {
					refresh.click();
				} catch (Exception e) {
					extentTest.info("Refresh Button Is Not Clickable..!!!");
				}
			}
		} catch (Exception e) {
			extentTest.info("Not Able To get Text From Popup..!!!");
		} finally {
			return popupMsg;
		}
	}

}
