package com.framework.testlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

/*
 * @Author: Tejal Gavade & Pratiksha Vaidya.
 * @Since : November 2022
 * @Discription : DataProvider this method use to get test data from Excel by using HashMap (Key & Values).
 */

public class ExcelTestData_Utility {

//Excel read
	@DataProvider(name = "data")
	public Object[][] dataSupplier() throws IOException {
		File filePath = new File(System.getProperty("user.dir") + "//ExcelData//LoginTestCase.xlsx");
		FileInputStream load = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(load);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		workbook.close();

		Object[][] obj = new Object[lastRowNum][1];
		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> datamap = new HashMap<Object, Object>();
			for (int j = 0; j < lastCellNum; j++) {
				datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
			}
			obj[i][0] = datamap;
		}
		return obj;
	}

}
