package com.nbg.pages;

import org.openqa.selenium.By;

import com.nbg.base.Page;
import com.nbg.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page {
	
	public void goToBooks() {
		driver.findElement(By.cssSelector(".zicon-apps-books")).click();
	}

	public CRMHomePage goToCRM() {
		click("crmlink");
		return new CRMHomePage();
	}

	public void goToDesk() {
		driver.findElement(By.cssSelector("zicon-apps-support")).click();
	}
}
