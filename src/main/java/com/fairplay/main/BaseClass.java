
package com.fairplay.main;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseClass  {
	public WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	protected FairplayLoginPage loginpage;
	protected FairplaySignupPage signuppage;
	protected FairplayResetPassword resetpage;
	protected FairplayEditProfile editprofile;
	protected FairplayWithdraw withdrawalpage;
	protected FairplayInstantWithdraw instantwithdrawalpage;
	protected FairplayProfileSectionPage profilesection;
	protected FairplayForgotPassword forgotpassword;
	protected FairplayDepositPage deposit;
	protected FairplayChangeMobileNumberPage changepass;
	protected Turnover turnover;

}

 