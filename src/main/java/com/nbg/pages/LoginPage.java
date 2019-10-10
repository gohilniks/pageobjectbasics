package com.nbg.pages;

import com.nbg.base.Page;

public class LoginPage extends Page {
	
	public ZohoAppPage doLogin(String username, String password) {
		type("email", username);
		type("password", password);
		click("signbtn");
		/*
		 * driver.findElement(By.cssSelector("#lid")).sendKeys(username);
		 * driver.findElement(By.cssSelector("#pwd")).sendKeys(password);
		 * driver.findElement(By.cssSelector("#signin_submit")).click();
		 */
		return new ZohoAppPage();
	}
	
	
}
