package com.nbg.pages;

import org.openqa.selenium.By;

import com.nbg.base.Page;

public class HomePage extends Page {

	public void goToSupport() {
		driver.findElement(By.cssSelector(".zh-support")).click();
	}

	public void goToSignUp() {
		driver.findElement(By.cssSelector(".zh-signup")).click();
	}

	public LoginPage goToLogin() {
		click("loginlink");
		return new LoginPage();
	}

	public void validateFooterLink() {

	}
}
