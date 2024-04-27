package com.fairplay.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaits {

	@SuppressWarnings("deprecation")
	public void elementToBeClickable(WebDriver driver, WebElement elem, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(elem));
	}

	@SuppressWarnings("deprecation")
	public void visibilityOf(WebDriver driver, WebElement elem, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(elem));
	}

	@SuppressWarnings("deprecation")
	public void invisibilityOf(WebDriver driver, WebElement elem, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.invisibilityOf(elem));
	}

	@SuppressWarnings("deprecation")
	public void textToBePresentInElement(WebDriver driver, WebElement elem, int timeout, String args) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.textToBePresentInElement(elem, args));
	}

}
