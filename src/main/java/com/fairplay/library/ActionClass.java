package com.fairplay.library;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/*
 * @Author: Tejal Gavade.
 * @Since : November 2022
 * @Discription : This class contains different methods to perform mouse actions with the help of action class and JavascriptExecutor.
 */

public class ActionClass {

	public void jSForClick(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}

	public void jSForScroll(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}

	public void jSForScroll(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(1385, -571)", " ");
	}
	
	public void jsForScrollDown(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void selectIndex(WebElement ele, int i) {
		Select sel = new Select(ele);
		sel.selectByIndex(i);
	}

	public void selectValue(WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByValue(value);
	}

	public void selectVisibleText(WebElement ele, String text) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(text);
	}

	public void doubleClick_delete(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).sendKeys(Keys.DELETE).perform();
	}

	public void deleteall(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.keyDown(element, Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build().perform();
	}

	public void moveToElememt(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void clickandHold(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.clickAndHold(element).perform();
	}

	public void dragAndDrop(WebDriver driver, WebElement source, WebElement destination) {
		Actions act = new Actions(driver);
		act.dragAndDrop(source, destination);
	}

	public void moveToElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void contextClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

	public void click(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.click(element).perform();
	}
//
//	public static void gf() {
//		String str = "Tejal Gavade";
//		if (str.contains(" ")) {
//			str = str.replaceAll("\\s+", "");
//			System.out.println(str);
//		}
//	}

//	public static void main(String[] args) {
//		String str = "7249153969";
//		//String msg = String.join("+91"+str);
//		String op = "+91"+str;
//		System.out.println(op);
//	}

}
