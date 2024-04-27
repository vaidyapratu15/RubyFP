package com.fairplay.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLogout extends Admin_BaseClass {

	@FindBy(xpath = "//li[@class = 'nav-item dropdown']")
	WebElement profile;

	@FindBy(xpath = "//ul[@class = 'dropdown-menu show']//button")
	WebElement logout;

	@SuppressWarnings("static-access")
	public AdminLogout(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void adminProfile() {
		try {
			profile.click();
			extentTest.info("Profile Btn Is Clickable..!!!");
		} catch (Exception e) {
			extentTest.info("Profile Btn Is Not Clickable..!!!");
		}
	}

	public void logout() {
		try {
			logout.click();
			extentTest.info("Login Btn Is Clickable..!!!");
		} catch (Exception e) {
			extentTest.info("Login Btn Is Not Clickable..!!!");
		}
	}

}
