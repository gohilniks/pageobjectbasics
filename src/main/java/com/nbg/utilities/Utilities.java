package com.nbg.utilities;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.nbg.base.Page;

public class Utilities extends Page {
	public static String screenshotPath;
	public static String screenshotName;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static Date d;

	public static void captureScreenshot() throws Exception {
		d = new Date();
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println("Date and time is : " + sdf.format(d));
		screenshotName = sdf.format(d).toString().replace(":", "_").replace(" ", "_").replace(".", "_") + ".jpg";
		
		System.out.println(screenshotName);

		FileUtils.copyFile(srcFile, new File(path + "\\target\\surefire-reports\\html\\" + screenshotName));
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		/*
		 * if (excel == null) { excel = new ExcelReader(path +
		 * "\\src\\test\\resources\\excel\\testdata.xlsx"); }
		 * 
		 * String sheetName = m.getName(); if (sheetName.equalsIgnoreCase("login")) {
		 * sheetName = "newRegistration"; }
		 */
		String sheetName = m.getName();

		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}
		}
		return data;
	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			String testCase = excel.getCellData(sheetName, "TCID", rowNum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, "Runmode", rowNum);
				if (runmode.equalsIgnoreCase("Y")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
