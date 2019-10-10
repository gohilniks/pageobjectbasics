package com.nbg.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nbg.pages.HomePage;
import com.nbg.pages.LoginPage;
import com.nbg.utilities.Utilities;

public class LoginTest extends BaseTest {
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String , String> data) {
		HomePage home = new HomePage();
		LoginPage login = home.goToLogin();

		login.doLogin(data.get("username"), data.get("password"));
		
		Assert.fail("Login test failed");
	}
}
