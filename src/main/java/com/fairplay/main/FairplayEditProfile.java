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
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class FairplayEditProfile extends BaseClass {

	ActionClass act = new ActionClass();
	FluentWaitsClass wait = new FluentWaitsClass();
	ExplicitWaits ewait = new ExplicitWaits();

	@FindBy(xpath = "(//div[@class='v-list-item__title white--text font-weight-bold f14'][normalize-space()='Profile'])[1]")
	WebElement profile;

	@FindBy(xpath = "//button[@class='v-icon notranslate v-icon--link mdi mdi-account-edit theme--dark primary--text']")
	WebElement editProfile;

	@FindBy(xpath = "//div[@class = 'v-list-item__subtitle subtitle-1 text-white']//span")
	List<WebElement> userInfo;

	@FindBy(xpath = "(//div[@class ='v-text-field__slot']//input)[1]")
	WebElement enterFirstOtp;

	@FindBy(xpath = "//div//div[@class = 'v-messages__message message-transition-enter-to']")
	WebElement errorMsgForOtp;

	@FindBy(xpath = "(//div[@class ='v-text-field__slot']//input)[6]")
	WebElement enterSecondOtp;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[1]")
	WebElement editName;

	@FindBy(xpath = "(//div[@class = 'v-text-field__slot']//input)[3]")
	WebElement editDOB;

	@FindBy(xpath = "(//div[@class = 'v-input__append-inner'])[5]//div")
	WebElement editGender;

	@FindBy(xpath = "//div[@class = 'primary--text']//button")
	WebElement selYear;

	@FindBy(xpath = "(//ul[@class='v-date-picker-years'])[1]//li")
	List<WebElement> listYear;

	@FindBy(xpath = "//button//div[@class = 'v-btn__content']")
	List<WebElement> listMonth;

	@FindBy(xpath = "//tbody//tr//td//button//div[@class = 'v-btn__content']")
	List<WebElement> listDate;

	@FindBy(xpath = "(//div[@role = 'listbox'])//div[@role = 'option']")
	List<WebElement> listGender;

	@FindBy(xpath = ("//div[@role='status']"))
	WebElement popup;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])[1]")
	WebElement closePopup;

	@FindBy(xpath = "//div[contains(text(),'OTP Verification Fail, Please Provide Valid OTP or Try Again !!!')]")
	WebElement otpPopup;

	@FindBy(xpath = "//div[contains(text(),'Age should be above 18')]")
	WebElement agePopup;

	@FindBy(xpath = "(//button//span[contains(text(),'Verify')])")
	WebElement verifyOtp;

	@FindBy(xpath = "//div[@class='v-dialog v-dialog--active v-dialog--persistent']//button[1]")
	WebElement closeOtp;

	@FindBy(xpath = "//button[@class='caption font-weight-bold v-btn v-btn--text theme--dark v-size--small']//span")
	WebElement close;

	// div[@class='v-dialog v-dialog--active v-dialog--persistent']//button[1]

	@FindBy(xpath = "(//button//span[contains(text(),'OK')])")
	WebElement ok;

	@FindBy(xpath = "(//button//span[contains(text(),'Cancel')])")
	WebElement cancle;

	@FindBy(xpath = "(//button//span[contains(text(),'Update')])")
	WebElement update;

	@SuppressWarnings("static-access")
	public FairplayEditProfile(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private String otp;

	public void editProfile() {
		try {
			act.jSForScroll(driver, profile);
			profile.click();
		} catch (Exception e) {
			extentTest.info("Profile Button Is Not Clickable..!!!");
		}
	}

	public void scrollTillEditProfileBtn() {
		try {
			act.jSForScroll(driver, editProfile);
		} catch (Exception e) {
			extentTest.info("Not Able To Scroll Till Edit Profile Button..!!!");
		}
	}

	public void getUserInfo() {
		try {
			for (WebElement info : userInfo) {
				extentTest.info(info.getText());
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Get The User Information..!!!");
		}

	}

	public void editProfileBtn() {
		try {
			act.jSForScroll(driver, editProfile);
			ewait.visibilityOf(driver, editProfile, 30);
			act.jSForClick(driver, editProfile);
		} catch (Exception e) {
			extentTest.info("Profile Button Is Not Clickable :");
		}
	}

	public String otpPopup() {
		ewait.visibilityOf(driver, popup, 4);
		String popupMsg = popup.getText();
		try {
			if (popupMsg.equals("OTP sent Successfully")) {
				extentTest.info("Verification message : " + popupMsg);
				Thread.sleep(1000);
				closePopup.click();
				return popupMsg;
			} else {
				extentTest.info("Verification message : " + popupMsg);
				Thread.sleep(1000);
				closePopup.click();
				int i = 0;
				do {
					break;
				} while (i == 0);
				return popupMsg;
			}
		} catch (Exception e) {
			extentTest.info("PopUp Is Not Visible..!!!");
			return "PopUp Is Not Visible..!!!";
		}
	}

	public String getOtp() throws Exception {
		String apiUrl = GetGsonFairplayTestData.getConfigData().getUserAPIUrl();

		String requestBody = "{\"email\":\"0174802571@fairplay.club\"}";

		String accessToken = GetGsonFairplayTestData.getToken().getAccessToken();

		String desiredKey = GetGsonFairplayTestData.getConfigData().getData();

		String desiredKey1 = GetGsonFairplayTestData.getConfigData().getMobileVerification();

		otp = OtpExtractor.sendPostRequest(apiUrl, accessToken, requestBody, desiredKey, desiredKey1);
		
		return otp;
	}

	public String setOtp(String otp) throws InterruptedException {
		this.otp = otp;
		int length = otp.length();
		if (length < 6) {
			try {
				enterFirstOtp.sendKeys(otp);
				extentTest.info("First OTP :" + otp);
				String errorMsg = errorMsgForOtp.getText();
				extentTest.info("Verification Message For OTP : " + errorMsg);
				extentTest.info("Verify Button Is Disabled..!!!");
				close.click();
				return errorMsg;
			} catch (Exception e) {
				extentTest.info("Verify Button Is Disabled..!!!");
				close.click();
				extentTest.info("Not Able To Enter First OTP..!!!");
				return "Not Able To Enter First OTP..!!!";
			}
		} else if (length > 6) {
			try {
				enterFirstOtp.sendKeys(otp);
				extentTest.info("First OTP :" + otp);
				try {
					String errorMsg = errorMsgForOtp.getText();
					extentTest.info("Verification Message For OTP : " + errorMsg);
					return errorMsg;
				} catch (Exception e) {
					try {
						Thread.sleep(1000);
						verifyOtp.click();
						try {
							ewait.visibilityOf(driver, popup, 4);
							String popuptext = popup.getText();
							extentTest.info("Verification message : " + popuptext);
							Thread.sleep(1000);
							if (popuptext.equals(GetGsonFairplayTestData.getConfigData().getOtpVerificationFail())) {
								close.click();
								Thread.sleep(1000);
								closePopup.click();
							}
							return popuptext;
						} catch (Exception ex) {
							int i = 0;
							do {
								break;
							} while (i == 0);
							return null;
						}
					} catch (Exception ey) {
						extentTest.info("Not Able To Verify The OTP..!!!");
						close.click();
						return "Not Able To Verify The OTP..!!!";
					}
				}
			} catch (Exception e) {
				extentTest.info("Not Able To Enter First OTP..!!!");
				close.click();
				return "Not Able To Enter First OTP..!!!";
			}
		} else {
			try {
				enterFirstOtp.sendKeys(otp);
				Thread.sleep(1000);
				extentTest.info("First OTP :" + otp);
				try {
					verifyOtp.click();
					try {
						ewait.visibilityOf(driver, popup, 4);
						String popuptext = popup.getText();
						extentTest.info("Verification message : " + popuptext);
						Thread.sleep(1000);
						if (popuptext.equals(GetGsonFairplayTestData.getConfigData().getOtpVerificationFail())) {
							close.click();
							Thread.sleep(1000);
							closePopup.click();
						}
						return popuptext;
					} catch (Exception e) {
						int i = 0;
						do {
							break;
						} while (i == 0);
						return null;
					}
				} catch (Exception e) {
					extentTest.info("Not Able To Verify The OTP..!!!");
					close.click();
					return "Not Able To Verify The OTP..!!!";
				}
			} catch (Exception e) {
				extentTest.info("Not Able To Enter First OTP..!!!");
				close.click();
				return "Not Able To Enter First OTP..!!!";
			}
		}
	}

	public void editName(String name) {
		try {
			editName.click();
			act.doubleClick_delete(driver, editName);
			editName.sendKeys(name);
		} catch (Exception e) {
			extentTest.info("Not Able To Edit Name..!!!");
		}
	}

	public void editDOB(String year, String month, String day) throws InterruptedException {
		editDOB.click();
		Thread.sleep(1000);
		selYear.click();
		Thread.sleep(1000);
		selYear.click();
		try {
			for (WebElement selyear : listYear) {
				String yearr = selyear.getText();
				if (yearr.equals(year)) {
					Thread.sleep(1000);
					selyear.click();
					for (WebElement selmonth : listMonth) {
						String monthh = selmonth.getText();
						if (monthh.equals(month)) {
							Thread.sleep(1000);
							selmonth.click();
							for (WebElement seldate : listDate) {
								String datee = seldate.getText();
								if (datee.equals(day)) {
									seldate.click();
									Thread.sleep(1000);
									ok.click();

								} else {
									continue;
								}
							}

						} else {
							continue;
						}
					}

				} else {
					continue;
				}
			}
		} catch (Exception e) {
			// extentTest.info("Not Able To Select The Date..!!!");
		}
	}

	public void Gender(String genderr) throws InterruptedException {
		editGender.click();
		for (WebElement gender : listGender) {
			String selgender = gender.getText();
			if (selgender.equals(genderr)) {
				// ewait.elementToBeClickable(driver, gender, 4);
				gender.click();
				Thread.sleep(1000);
			} else
				continue;
		}
	}

	public String update() throws InterruptedException {
		update.click();
		String popupMsg = popup.getText();
		try {
			Thread.sleep(2000);
			if (popupMsg.equals(GetGsonFairplayTestData.getConfigData().getAgePopup())) {
				extentTest.info("Verification Message : " + popupMsg);
				closePopup.click();
				ewait.elementToBeClickable(driver, close, 4);
				close.click();
				return popupMsg;
			} else {
				extentTest.info("Verification Message : " + popupMsg);
				closePopup.click();
				return popupMsg;
			}
		} catch (Exception e) {
			ewait.elementToBeClickable(driver, close, 4);
			close.click();
			return null;
		}
	}

	public String setSecondOtp(String otp) {
		this.otp = otp;

		int length = otp.length();
		if (length < 6) {
			try {
				enterSecondOtp.sendKeys(otp);
				extentTest.info("Second OTP :" + otp);
				String errorMsg = errorMsgForOtp.getText();
				extentTest.info("Verification Message For OTP : " + errorMsg);
				extentTest.info("Verify Button Is Disabled..!!!");
				closeOtp.click();
				return errorMsg;
			} catch (Exception e) {
				extentTest.info("Verify Button Is Disabled..!!!");
				closeOtp.click();
				extentTest.info("Not Able To Enter Second OTP..!!!");
				return "Not Able To Enter Second OTP..!!!";
			}
		} else if (length > 6) {
			try {
				ewait.textToBePresentInElement(driver, enterSecondOtp, 6, otp);
				enterSecondOtp.sendKeys(otp);
				extentTest.info("Second OTP :" + otp);
				try {
					String errorMsg = errorMsgForOtp.getText();
					extentTest.info("Verification Message For OTP : " + errorMsg);
					return errorMsg;
				} catch (Exception e) {
					try {
						Thread.sleep(1000);
						verifyOtp.click();
						try {
							ewait.visibilityOf(driver, popup, 4);
							String popuptext = popup.getText();
							extentTest.info("Verification message : " + popuptext);
							Thread.sleep(1000);
							if (popuptext.equals(GetGsonFairplayTestData.getConfigData().getOtpVerificationFail())) {
								closeOtp.click();
								Thread.sleep(1000);
								closePopup.click();
							}
							return popuptext;
						} catch (Exception ex) {
							int i = 0;
							do {
								break;
							} while (i == 0);
							return null;
						}
					} catch (Exception ey) {
						extentTest.info("Not Able To Verify The OTP..!!!");
						closeOtp.click();
						return "Not Able To Verify The OTP..!!!";
					}
				}
			} catch (Exception e) {
				extentTest.info("Not Able To Enter Second OTP..!!!");
				closeOtp.click();
				return "Not Able To Enter Second OTP..!!!";
			}
		} else {
			try {
				enterSecondOtp.sendKeys(otp);
				Thread.sleep(1000);
				extentTest.info("Second OTP :" + otp);
				try {
					verifyOtp.click();
					try {
						ewait.visibilityOf(driver, popup, 4);
						String popuptext1 = popup.getText();
						extentTest.info("Verification message : " + popuptext1);
						Thread.sleep(1000);
						if (popuptext1.equals(GetGsonFairplayTestData.getConfigData().getOtpVerificationFail())) {
							closeOtp.click();
							Thread.sleep(1000);
							closePopup.click();
						}
						return popuptext1;
					} catch (Exception e) {
						int i = 0;
						do {
							break;
						} while (i == 0);
						return null;
					}
				} catch (Exception e) {
					extentTest.info("Not Able To Verify The OTP..!!!");
					closeOtp.click();
					return "Not Able To Verify The OTP..!!!";
				}
			} catch (Exception e) {
				extentTest.info("Not Able To Enter Second OTP..!!!");
				closeOtp.click();
				return "Not Able To Enter Second OTP..!!!";
			}
		}
	}

}