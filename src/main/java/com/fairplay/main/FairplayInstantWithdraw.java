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

public class FairplayInstantWithdraw extends BaseClass {

	ActionClass act = new ActionClass();
	FluentWaitsClass wait = new FluentWaitsClass();
	ExplicitWaits ewait = new ExplicitWaits();
	RandomizeData rand = new RandomizeData();

	@FindBy(xpath = "//span[contains(text(),'Withdraw Now')]")
	WebElement withdrawNow;

	@FindBy(xpath = "//div[contains(text(),'Request Withdrawal')]")
	WebElement bankTransfers;

	@FindBy(xpath = "//div[contains(text(),'Instant Withdrawal')]")
	WebElement instantWithdrawal;

	@FindBy(xpath = "(//button//span[contains(text(),'add new')])[2]")
	WebElement addNew;

	@FindBy(xpath = "(//div//div[@class = 'v-text-field__slot'])[3]//input")
	WebElement enterOtp;

	@FindBy(xpath = "(//div//div[@class = 'v-text-field__slot'])[4]//input")
	WebElement enterAccountNumber;

	@FindBy(xpath = "(//div//div[@class = 'v-text-field__slot'])[5]//input")
	WebElement enterConfirmAccountNumber;

	@FindBy(xpath = "(//div//div[@class = 'v-text-field__slot'])[6]//input")
	WebElement enterIfscCode;

	@FindBy(xpath = "(//div//div[@class = 'v-text-field__slot'])[7]//input")
	WebElement enterAccountHolder;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[8]//input[@readonly = 'readonly']")
	WebElement enterBankName;

	@FindBy(xpath = "(//div[@class='v-text-field__slot'])[9]//input[@readonly = 'readonly']")
	WebElement enterBranchName;

	@FindBy(xpath = "(//span[normalize-space()='Add'])")
	WebElement add;

	@FindBy(xpath = "//button[@class='caption font-weight-bold v-btn v-btn--text theme--dark v-size--small']//span[@class='v-btn__content'][normalize-space()='Close']")
	WebElement close;

	@FindBy(xpath = "(//div[@class='v-text-field__slot']//input)[2]")
	WebElement enterAmount;

	@FindBy(xpath = "(//span[contains(text(),'submit')])[2]")
	WebElement submit;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[2]")
	WebElement errorMsgForOtp;
	
	@FindBy(xpath = "//button[@class = 'mr-2 v-btn v-btn--outlined theme--dark v-size--default success--text']")
	WebElement proceed;

	@FindBy(xpath = "//button[@class='v-btn v-btn--outlined theme--dark v-size--default error--text']")
	WebElement cancel;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[3]")
	WebElement accountVerification;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[4]")
	WebElement confirmAccountVerification;

	@FindBy(xpath = "(//div[@class='v-messages__wrapper'])[5]")
	WebElement ifscVerification;

	@FindBy(xpath = ("//div//div[@role='status']"))
	WebElement popup;

	@FindBy(xpath = "//div[contains(text(),'OTP sent Successfully')]")
	WebElement popupS;

	@FindBy(xpath = "//div[contains(text(),'OTP Verification Fail, Please Provide Valid OTP or Try Again !!!')]")
	WebElement otpPopup;

	@FindBy(xpath = "//div[contains(text(),'You have sent too many OTP requests, Please contact support']")
	WebElement errOtp;

	@FindBy(xpath = "(//button//span[contains(text(),'Close')])[1]")
	WebElement closePopup;

	@FindBy(xpath = "//div[contains(text(),'Please provide valid ifsc code')]")
	WebElement ifscPopup;

	@FindBy(xpath = "//div[contains(text(),'Bank Account Added.')]")
	WebElement accountPopup;

	@FindBy(xpath = "(//div[@class = 'v-card__text text-capitalize black--text f10'])[1]//div")
	List<WebElement> accountDetails;

	@FindBy(xpath = "(//div//button[@class = 'po-right black--text v-btn v-btn--absolute v-btn--fab v-btn--round v-btn--text theme--dark v-size--small'])[1]")
	WebElement delete;

	@FindBy(xpath = "//div[contains(text(),'Bank Detail Deleted Sucessfully !!!')]")
	WebElement deletePopup;

	@FindBy(xpath = "//button//span[contains(text(),'No')]")
	WebElement no;

	@FindBy(xpath = "//button//span[contains(text(),'Yes')]")
	WebElement yes;

	@FindBy(xpath = "(//div[@class='v-responsive__content'])[5]")
	WebElement fairplay;

	@FindBy(xpath = "(//div[@class='v-responsive__sizer'])[14]")
	WebElement banner;
	
	@FindBy(xpath = "//div[@class='v-list v-sheet theme--light v-list--dense']//div[@class = 'v-list-item theme--light']")
	List<WebElement> walletDetails;

	@FindBy(xpath = "//span[@class='user-balance white--text']")
	WebElement wallet_Amt;

	@FindBy(xpath = "((//div[@class='v-slide-group__content v-tabs-bar__content'])[2]//div[@class = 'white--text f12 font-weight-bold v-tab'])[1]")
	WebElement withdrawal_req;

	@FindBy(xpath = "//div[@class = 'text-right col col-4']//button")
	WebElement cancel_withdrawal_req;

	@FindBy(xpath = "//div[@class = 'v-overlay__scrim']")
	WebElement clickAnywhr;

	@FindBy(xpath = "//div[contains(text(),'My Transactions')]")
	WebElement myTransactions;

	
	public FairplayInstantWithdraw(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private String otp;
	private String accountNumber;
	private String ifscCode;
	private String accountHolderName;

	public String withdrawNow() throws InterruptedException {
		try {
			withdrawNow.click();
			extentTest.info("WithdrawNow Is Clickable..!!!");
			return "WithdrawNow Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("WithdrawNow Is Not Clickable..!!!");
			return "WithdrawNow Is Not Clickable..!!!";
		}
	}
	
	public String instantWithdraw() {
		try {
			instantWithdrawal.click();
			extentTest.info("InstantWithdraw Is Clickable..!!!");
			return "InstantWithdraw Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("InstantWithdraw Is Not Clickable..!!!");
			return "InstantWithdraw Is Not Clickable..!!!";
		}
	}
	
	public String myTransactions() throws InterruptedException {
		try {
			myTransactions.click();
			extentTest.info("MyTransactions Is Clickable..!!!");
			Thread.sleep(1000);
			return "MyTransactions Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("MyTransactions Is Not Clickable..!!!");
			return "MyTransactions Is Not Clickable..!!!";
		}
	}

	public void checkVisibilityOfBanner() {
		try {
			Boolean value = banner.isDisplayed();
			extentTest.info("WhatsApp Banner Is Displayed On Withdrawal Page : " + value);
		} catch (Exception e) {
			extentTest.info("WhatsApp Banner Is Not Displayed On Withdrawal Page...!!!");
		}
	}

	public String instantAddBankDetails() throws InterruptedException {
		ewait.visibilityOf(driver, addNew, 30);
		Thread.sleep(1000);
		try {
			addNew.click();
			extentTest.info("AddNow Is Clickable..!!!");
			return "AddNow Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("AddNow Is Not Clickable..!!!");
			return "AddNow Is Not Clickable..!!!";
		}
	}

	public String otpPopup() throws InterruptedException {
		try {
			ewait.visibilityOf(driver, popup, 6);
			String popupMsg = popup.getText();
			if (popupMsg.equals(GetGsonFairplayTestData.getConfigData().getOtpPopup())) {
				extentTest.info("Verification message : " + popupMsg);
				closePopup.click();
				return popupMsg;
			} else {
				extentTest.info("Verification message : " + popupMsg);
				closePopup.click();
				int i = 0;
				do {
					act.jSForScroll(driver, close);
					Thread.sleep(1000);
					close.click();
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
				return "Not Able To Enter OTP..!!!";
			}
		} else {
			enterOtp.sendKeys(otp);
			extentTest.info("OTP : " + otp);
			return null;
		}

	}

	public String getAccountNumber() {
		accountNumber = rand.randomizeNumber();
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		int length = accountNumber.length();
		try {
			enterAccountNumber.sendKeys(accountNumber);
			extentTest.info("Account number : " + accountNumber);
			try {
				if (length < 9) {
					extentTest.info(accountVerification.getText());
				} else if (length > 18) {
					extentTest.info(accountVerification.getText());
				}
			} catch (Exception e) {
				extentTest.info("Valid Account Number..!!!");
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Enter Account Number..!!!");
		}

	}

	public void confirmAccountNumber(String accountNumber) {
		try {
			enterConfirmAccountNumber.sendKeys(accountNumber);
			extentTest.info("Confirm Account Number : " + accountNumber);
		} catch (Exception e) {
			extentTest.info("Not Able To ReEnter Account Number..!!!");
		} finally {
			extentTest.info(confirmAccountVerification.getText());
		}
	}

	public String getIfscCode() {
		ifscCode = rand.randomizeIFSCCode();
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
		try {
			enterIfscCode.sendKeys(ifscCode);
			extentTest.info("IFSC Code : " + ifscCode);
			try {
				int length = ifscCode.length();
				if (length < 11) {
					ewait.visibilityOf(driver, ifscVerification, 1);
					extentTest.info("Verification message for IFSC code :" + ifscVerification.getText());
				} else if (length == 11) {
					try {
						ewait.visibilityOf(driver, ifscVerification, 1);
						extentTest.info(ifscVerification.getText());
					} catch (Exception e) {
						ewait.visibilityOf(driver, ifscPopup, 1);
						extentTest.info("PopUp : " + ifscPopup.getText());
						closePopup.click();
					}
				} else if (length > 11) {
					ewait.visibilityOf(driver, ifscVerification, 1);
					extentTest.info("Verification message for IFSC code :" + ifscVerification.getText());
				}
			} catch (Exception e) {
				extentTest.info("Valide IFSC Code..!!!");
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Enter IFSC Code..!!!");
		}

	}

	public String getAccountholderName() {
		accountHolderName = rand.randomizeName();
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) throws InterruptedException {
		this.accountHolderName = accountHolderName;
		try {
			enterAccountHolder.sendKeys(accountHolderName);
			extentTest.info("Account Holder Name : " + accountHolderName);
			Thread.sleep(1000);
			act.jSForScroll(driver, add);
		} catch (Exception e) {
			extentTest.info("Not Able To Enter Account Holder Name..!!!");
		}
	}

	public void bankName() {
		String bankName = enterBankName.getText();
		extentTest.info("Bank Name : " + bankName);
	}

	public void branchName() {
		String branchName = enterBranchName.getText();
		extentTest.info("Branch Name : " + branchName);
	}

	public String addAccount() throws InterruptedException {
		Boolean button = add.isEnabled();
		extentTest.info("Add Button Is Enabled : " + button.toString());
		try {
			ewait.elementToBeClickable(driver, add, 6);
			add.click();
			ewait.visibilityOf(driver, popup, 6);
			String popupMsg = popup.getText();
			if (!(popupMsg.equals(GetGsonFairplayTestData.getConfigData().getBankAdded()))) {
				extentTest.info("Popup : " + popupMsg);
				Thread.sleep(1000);
				act.jSForScroll(driver, add);
				close.click();
				Thread.sleep(1000);
				closePopup.click();
				return popupMsg;
			} else {
				extentTest.info("Popup : " + popupMsg);
				closePopup.click();
				return popupMsg;
			}
		} catch (Exception e) {
			ewait.elementToBeClickable(driver, close, 6);
			close.click();
			extentTest.info("Due to Invalid Cred Not Able To Create Account..!!!");
			return "Due to Invalid Cred Not Able To Create Account..!!!";
		}
	}

	public void accountDetails() throws InterruptedException {
		bankTransfers.click();
		Thread.sleep(1000);
		try {
			for (WebElement detail : accountDetails) {
				extentTest.info(detail.getText());
			}
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch The Bank Details..!!!");
		}
	}

	public String clickDeleteBtn() throws Throwable {
		try {
			delete.click();
			extentTest.info("Delete Button Is Clickable..!!");
			return "Delete Button Is Clickable..!!";
		} catch (Exception e) {
			extentTest.info("Delete Button Is Not Clickable..!!");
			return "Delete Button Is Not Clickable..!!";
		}
	}

	public String deleteOtpPopUp() {
		try {
			String popupMsg = popup.getText();
			if (popupMsg.equals(GetGsonFairplayTestData.getConfigData().getOtpPopup())) {
				extentTest.info("Popup : " + popupMsg);
				closePopup.click();
				return popupMsg;
			} else {
				extentTest.info("Popup : " + popupMsg);
				closePopup.click();
				Thread.sleep(1000);
				no.click();
				return popupMsg;
			}

		} catch (Exception e) {
			extentTest.info("Popup Is Not Visible...!!");
			return "Popup Is Not Visible...!!";
		}
	}
	
	public String yes() throws InterruptedException {
		yes.click();
		try {
		//	ewait.visibilityOf(driver, deletePopup, 6);
			extentTest.info("Popup : " + deletePopup.getText());
		//	closePopup.click();
			return deletePopup.getText();
		} catch (Exception e) {
		//	ewait.visibilityOf(driver, otpPopup, 6);
			extentTest.info("Popup : " + otpPopup.getText());
		//	closePopup.click();
			Thread.sleep(1000);
			no.click();
			return otpPopup.getText();
		}
	}
	
	public void getWalletDetails() {
		for (WebElement Amt : walletDetails) {
			extentTest.info(Amt.getText());
		}
	}

	public String getAmountToBeWithdrawal() {
		try {
			String balanceAmt = wallet_Amt.getText();
			extentTest.info("Balance Amount Is : " + balanceAmt);
			return balanceAmt;
		} catch (Exception e) {
			extentTest.info("Not Able To Fetch Balance Amount...!!!");
			return "Not Able To Fetch Balance Amount...!!!";
		}
	}
	
	public String enterAmt(String amount) {
		enterAmount.sendKeys(amount);
		extentTest.info("Amount To Be Withdraw :" + amount);
		try {
			Thread.sleep(1000);
			submit.click();
			return "Submit Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("Submit Button Is Not Clickable..!!!");
			return "Submit Button Is Not Clickable..!!!";
		}
	}
	
	public String popUp() {
		try {
			String text = popup.getText();
			extentTest.info("Popup : " + popup.getText());
			closePopup.click();
			return text;
		} catch (Exception e) {
			extentTest.info("PopUp Is Not Visible...!!!");
			return "PopUp Is Not Visible...!!!";
		}
	}

	public String proceedWithdrawal() {
		try {
			proceed.click();
			extentTest.info("Proceed Button Is Clickable..!!!");
			return "Proceed Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("Proceed Button Is Not Clickable..!!!");
			return "Proceed Button Is Not Clickable..!!!";
		}
	}

	public String checkWithdrawalReq() {
		try {
			withdrawal_req.click();
			extentTest.info("withdrawal_req Button Is Clickable..!!!");
			return "withdrawal_req Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("withdrawal_req Button Is Not Clickable..!!!");
			return "withdrawal_req Button Is Clickable..!!!";
		}
	}

	public String cancelWithdrawalReq() {
		clickAnywhr.click();
		try {
			cancel_withdrawal_req.click();
			extentTest.info("Cancel Withdrawal_req Button Is Clickable..!!!");
			yes.click();
			return "Cancel Withdrawal_req Button Is Clickable..!!!";
		} catch (Exception e) {
			extentTest.info("Cancel withdrawal_req Button Is Not Clickable..!!!");
			return "Cancel Withdrawal_req Button Is Clickable..!!!";
		}
	}

	public void cancelWithdrawal() {
		try {
			cancel.click();
		} catch (Exception e) {
			extentTest.info("Cancel Button Is Not Clickable..!!!");
		}
	}

}
