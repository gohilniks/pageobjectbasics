package com.nbg.rough;

import org.testng.Assert;

import com.nbg.base.Page;
import com.nbg.page.crm.accounts.AccountsPage;
import com.nbg.page.crm.accounts.CreateAccountPage;
import com.nbg.pages.HomePage;
import com.nbg.pages.LoginPage;
import com.nbg.pages.ZohoAppPage;

public class LoginTest {
	public static void main(String[] args) {
		//using egit plugin
		HomePage home = new HomePage();
		LoginPage login = home.goToLogin();

		ZohoAppPage zp = login.doLogin("nikunjgohil@techhive.co.in", "Techhive@123");

		zp.goToCRM();

		AccountsPage account = Page.menu.goToAccounts();

		CreateAccountPage cap = account.goToCreateAccounts();

		cap.createAccount("Nikunj");
		
		Assert.fail("Login test failed");

	}
}
