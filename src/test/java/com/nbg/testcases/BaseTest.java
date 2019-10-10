package com.nbg.testcases;

import org.testng.annotations.AfterSuite;

import com.nbg.base.Page;

public class BaseTest {
	@AfterSuite
	public void tearDown() {
		Page.quit();
	}
}
