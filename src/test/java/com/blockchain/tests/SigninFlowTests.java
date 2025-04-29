package com.blockchain.tests;

import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import com.blockchain.base.BaseTest;
import com.blockchain.base.TestUtils;

public class SigninFlowTests extends BaseTest {

	// Successful Login with valid user credentials
	@Test(groups = { "sanity", "regression" })
	public void verifyLogin() throws InterruptedException {

		String email = "dhanu30@yopmail.com";
		String password = "Test@1234";
		String expDashboardText = "Permissioned EVM, Blockchain, purpose built for the needs of regulated finance.";
		portalObjects.goToSignUpPage();
		portalObjects.login(email, password);
		portalObjects.loginButtonClick();

		TestUtils.assertEquals(expDashboardText, portalObjects.getDashboardText(), "Both texts are not matching");
		TestUtils.assertTrue(portalObjects.signOutButtonDisaply(), "Sign out button is not displayed");
		TestUtils.assertTrue(portalObjects.getStartedButtonDisaply(), "Get Started button is not displayed");
		TestUtils.assertAll();
		portalObjects.goToDAshBoard();

	}

	// Verify error message when entered Valid email and invalid password
	@Test(groups = { "regression" })
	public void verifyLoginWithValidEmailAndInvalidPassword() throws InterruptedException {

		String email = "dhanu30@yopmail.com";
		String password = "Test@123456";
		portalObjects.goToSignUpPage();
		portalObjects.login(email, password);
		portalObjects.loginButtonClick();
		Alert alert = TestUtils.waitForAlertAndSwitch(driver, 10);
		String alertText = alert.getText();
		String expAlertText = "Incorrect E-Mail or Password";
		TestUtils.assertEquals(alertText, expAlertText, "Both alert text are  not matching");
		TestUtils.assertAll();
		alert.accept();

	}

	//// Verify error message when entered InValid email and Valid password
	@Test(groups = { "sanity" })
	public void verifyLoginWithUnRegisteredEmailAndValidPassword() throws InterruptedException {

		String email = "dtetwwr@yopmail.com";
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.login(email, password);
		portalObjects.loginButtonClick();
		Alert alert = TestUtils.waitForAlertAndSwitch(driver, 10);
		String alertText = alert.getText();
		String expAlertText = "User not found";
		TestUtils.assertEquals(alertText, expAlertText, "Both alert text are  not matching");
		TestUtils.assertAll();
		alert.accept();

	}

}
