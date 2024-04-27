package com.fairplay.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fairplay.library.OtpExtractor;
import com.fairplay.library.gson_Model.GetGsonFairplayTestData;

public class AdminLogin extends Admin_BaseClass{
	
	private String username;
	private String password;
	private String otp;
	
	
	@FindBy(xpath = "//div[@class = 'form-group input-group-alternative mb-3']//input")
	WebElement userName;
	
	@FindBy(xpath = "(//div//button[@type = 'button'])[1]")
	WebElement next;
	
	@FindBy(xpath = "(//div[@class = 'form-group input-group-alternative mb-3']//input)[1]")
	WebElement OTP;
	
	@FindBy(xpath = "(//div//input[@type = 'password'])")
	WebElement passWord;
	
	@FindBy(xpath = "(//div//button[@type = 'button'])[1]")
	WebElement login;
	
	@SuppressWarnings("static-access")
	public AdminLogin(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getUsername() {
		username = "tejal.gavade@wohlig.com";
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
		userName.sendKeys(username);
	}
	
	public void clickNext() {
		try {
			next.click();
			extentTest.info("Next Is Clickable..!!!");
		}catch (Exception e) {
			extentTest.info("Next Is Not Clickable..!!!");
		}
	}

	public String getPassword() {
		password = "Vrushabh@10";
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		passWord.sendKeys(password);
	}  

	public String getOtp() throws Exception {
		
		String apiUrl = GetGsonFairplayTestData.getConfigData().getAdminAPIUrl();
		
		String requestBody = "{\"email\":\"tejal.gavade@wohlig.com\"}";

		String desiredKey = GetGsonFairplayTestData.getConfigData().getData();

		String desiredKey1 = GetGsonFairplayTestData.getConfigData().getAdminLoginOTP();

		otp = OtpExtractor.sendPostRequest(apiUrl, null, requestBody, desiredKey, desiredKey1);

		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
		OTP.sendKeys(otp);
	}
	
	public void login() {
		try {
			login.click();
			extentTest.info("Login Btn Is Clickable..!!!");
		}catch (Exception e) {
			extentTest.info("Login Btn Is Not Clickable..!!!");
		}
	}
	



}
