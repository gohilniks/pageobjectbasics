package com.nbg.page.crm.accounts;

import com.nbg.base.Page;

public class AccountsPage extends Page {
	public CreateAccountPage goToCreateAccounts() {
		click("createaccountbtn");
		return new CreateAccountPage();
	}
}
