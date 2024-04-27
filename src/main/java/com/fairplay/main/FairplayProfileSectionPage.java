package com.fairplay.main;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.fairplay.library.ActionClass;
import com.fairplay.library.ExplicitWaits;
import com.fairplay.library.FluentWaitsClass;
import com.fairplay.library.RandomizeData;

public class FairplayProfileSectionPage extends BaseClass {

	ActionClass act = new ActionClass();
	FluentWaitsClass wait = new FluentWaitsClass();
	ExplicitWaits ewait = new ExplicitWaits();
	RandomizeData rand = new RandomizeData();

	@FindBy(xpath = "(//div[@class='v-list v-sheet theme--light v-list--dense'])[3]//a[@target='blank']")
	List<WebElement> profileSection;

	@FindBy(xpath = "//span[contains(text(), 'Profile')]")
	WebElement profile;

	@FindBy(xpath = "//div[@class = 'v-toolbar__title tooltitle white--text font-weight-bold pl-0 body-2 text-capitalize']")
	WebElement profileText;

	@FindBy(xpath = "(//div//div[@class='col col-6'])")
	List<WebElement> leftSlider;
	
	@FindBy(xpath = "(//div[@class = 'v-responsive__content'])[4]")
	WebElement slider;

	@FindBy(xpath = "//div[@class = 'v-overlay__scrim']")
	WebElement randomClick;

	@SuppressWarnings("static-access")
	public FairplayProfileSectionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void profileClick() {
		try {
			profile.click();
		} catch (Exception e) {
			extentTest.info("Profile button is not clickable");
		}
	}

	public void profileSection() throws InterruptedException {
		profileClick();
		try {
			for (WebElement profilesectioneachpage : profileSection) {
				profilesectioneachpage.click();
				Thread.sleep(1000);
				extentTest.info("Page : " + profileText.getText());
				Thread.sleep(1000);
				extentTest.info("Page URL : " + driver.getCurrentUrl());
				Thread.sleep(1000);
				act.jsForScrollDown(driver);
				Thread.sleep(1000);
				act.jSForScroll(driver, profile);
				Thread.sleep(1000);
				profileClick();
				act.jSForScroll(driver, profilesectioneachpage);
			}
		} catch (Exception e) {
			extentTest.info(profileText.getText() + "Page is not clickable..!!!");
		} finally {
			randomClick.click();
		}

	}

	public void slider() {
		try {
			slider.click();
		} catch (Exception e) {
			extentTest.info("Slider button is not clickable");
		}
	}

	@SuppressWarnings("deprecation")
	public void leftSliderSection() throws InterruptedException {
		slider();
		Thread.sleep(1000);

		for (WebElement slidersectioneachpage : leftSlider) {
			// String text = slidersectioneachpage.getText();
			slidersectioneachpage.click();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			extentTest.info("Page : " + slidersectioneachpage.getText());
			Thread.sleep(1000);
			slider();
			Thread.sleep(1000);
		}
//		} catch (Exception e) {
//			extentTest.info(profileText.getText() + "Page is not clickable..!!!");
//		}

	}

}
