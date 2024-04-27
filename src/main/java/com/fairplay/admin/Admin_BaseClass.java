package com.fairplay.admin;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class Admin_BaseClass {
	
	protected static WebDriver driver;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	protected AdminLogin adminlogin;
	protected AdminPanelTelegramSetting telegram;
	protected AdminPanelWhatsAppSetting whatsApp;
	protected AdminLogout adminlogout;
}
