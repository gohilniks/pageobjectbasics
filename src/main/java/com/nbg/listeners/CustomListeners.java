package com.nbg.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.nbg.base.Page;
import com.nbg.utilities.Utilities;

public class CustomListeners extends Page implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		test = rep.createTest(result.getName().toUpperCase());

		/*
		 * if (!Utilities.isTestRunnable(result.getName(), excel)) { throw new
		 * SkipException("Skipping the test " + result.getName().toUpperCase() +
		 * "as the Runmode is NO"); }
		 */
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getName().toUpperCase() + " PASS");
		// rep.removeTest(test);
		rep.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");

			Utilities.captureScreenshot();

			test.log(Status.FAIL, result.getName().toUpperCase() + " Failed with exception : " + result.getThrowable());
			test.log(Status.FAIL, "Test Fail",
					MediaEntityBuilder.createScreenCaptureFromPath(Utilities.screenshotName).build());

			Reporter.log("</br>");
			Reporter.log("Click to see screenshot");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + ">Screenshot</a>");
			Reporter.log("</br>");
			Reporter.log("</br>");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img height=200 width=200 src="
					+ Utilities.screenshotName + "></img> </a>");

			// rep.removeTest(test);
			rep.flush();
		} catch (Exception e) {
			// TODO Auto0-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getName().toUpperCase() + " Skipped the test as the Runmode is NO");
		// rep.removeTest(test);
		rep.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
