package com.nbg.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nbg.base.Page;
import com.nbg.page.crm.accounts.AccountsPage;
import com.nbg.page.crm.accounts.CreateAccountPage;
import com.nbg.pages.ZohoAppPage;
import com.nbg.utilities.Utilities;

public class CreateAccountTest {
	@Test(dataProviderClass=Utilities.class, dataProvider="dp")
	public void createAccountTest(Hashtable<String, String> data) {
		ZohoAppPage zp = new ZohoAppPage();
		zp.goToCRM();

		AccountsPage account = Page.menu.goToAccounts();

		CreateAccountPage cap = account.goToCreateAccounts();

		cap.createAccount(data.get("accountname"));
		Page.log.debug("Create Account Test Completed");
		//Assert.fail("Create Account test failed");
	}
}
