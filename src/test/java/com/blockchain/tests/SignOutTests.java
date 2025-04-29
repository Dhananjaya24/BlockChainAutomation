package com.blockchain.tests;

import org.testng.annotations.Test;

import com.blockchain.base.BaseTest;

public class SignOutTests extends BaseTest {

	@Test(groups = { "sanity", "regression" }, timeOut = 20000)
	public void verfiySignOut() throws InterruptedException {
		String email = "dhanu30@yopmail.com";
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.login(email, password);
		portalObjects.loginButtonClick();
		portalObjects.signOut();

	}

}
