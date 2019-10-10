package com.nbg.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.nbg.base.Page;

public class ExtentManager{
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if(extent == null) {
			reporter = new ExtentHtmlReporter(Page.path + "\\target\\surefire-reports\\html\\extent.html");
			reporter.loadConfig(Page.path + "\\src\\test\\resources\\com\\nbg\\extentconfig\\ReportsConfig.xml");
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
		return extent;
	}
}
