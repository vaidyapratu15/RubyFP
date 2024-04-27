package com.fairplay.library;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.fairplay.main.BaseClass;

/*
 * @Author: Tejal Gavade.
 * @Since : November 2022
 * @Discription : This class contains getPropertyData method which is use to getData from Property file by using parameters Keys.
 * 				  And CaptureScreenshots method use to capture failureScreenshots by using parameters testCaseName.
 */

public class Utilities {
	
	// TakeScreenShot
	public static String screenshotsSubFolderName;
	public static String captureScreenshot(WebDriver driver, String testCaseName) {

		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File sourceFile = scrShot.getScreenshotAs(OutputType.FILE);
		String datestamp = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		String timestamp = new SimpleDateFormat("hh_mm_ss").format(new Date());
		File destFile = new File(
				System.getProperty("user.dir") + "//Screenshots//" + datestamp + "//" + timestamp + testCaseName);
		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Screenshot saved successfully");

		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
   //Excel Data
	public static String getTestData(int rowIndex, int cellIndex) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//ExcelData//Login_TestData.xlsx");
		Sheet sheet = WorkbookFactory.create(file).getSheet("Sheet1");
		String value = sheet.getRow(rowIndex).getCell(cellIndex).getStringCellValue();
		return value;
	}

	//Get Access Token From Local Storage
	public static String getAccessToken(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String accesstoken = (String) js.executeScript(String.format(
		            "return window.localStorage.getItem('%s');", "accessToken"));
		return accesstoken;
	}

	// Upload Image
	public static void uploadImage() throws AWTException {
		Robot robot = new Robot();
		File file = new File(System.getProperty("user.dir") + "//test-output//image.png");
		StringSelection ss = new StringSelection(file.getAbsolutePath());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_TAB);

		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_TAB);

		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_G);
		robot.delay(2000);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_G);

		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_META);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_META);
		robot.keyRelease(KeyEvent.VK_V);

		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

}
