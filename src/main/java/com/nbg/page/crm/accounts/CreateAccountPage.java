package com.nbg.page.crm.accounts;

import com.nbg.base.Page;

public class CreateAccountPage extends Page {
	public void createAccount(String accountName) {
		type("accountname", accountName);
	}
}
