package com.nbg.base;

import org.openqa.selenium.WebDriver;

import com.nbg.page.crm.accounts.AccountsPage;

public class TopMenu {
	WebDriver driver;
	
	public TopMenu(WebDriver driver) {
		this.driver = driver;
	}
	
	public void goToHome() {

	}

	public void goToLeads() {

	}

	public void goToContacts() {

	}

	public AccountsPage goToAccounts() {
		Page.click("accountstab_XPATH");
		return new AccountsPage();
	}

	public void goToSignOut() {

	}
}
