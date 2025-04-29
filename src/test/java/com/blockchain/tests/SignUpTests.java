package com.blockchain.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.blockchain.base.BaseTest;
import com.blockchain.base.TestUtils;

public class SignUpTests extends BaseTest {

	// Test case to verify valid signup
	@Test(groups = { "sanity", "regression" })
	public void verifySingup() throws InterruptedException {

		String expectedH1 = "Open Capital Network";
		String email = TestUtils.generateRandomEmail();
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.signUp(email, password);
		portalObjects.signUpButtonClick();
		// Directly soft assert can also be used by creating soft assert class
		// SoftAssert soft = new SoftAssert();
		TestUtils.assertTrue(portalObjects.getStartedButtonIsdisplayed(), "Button is not displayed");
		TestUtils.assertEquals(expectedH1, portalObjects.dashBoardH1Heading(),
				"Actual and expected dashboard title are  not matching after signup");
		TestUtils.assertAll();

	}

	// Verify Alert is popping up when existing user tries to Signup
	@Test(groups = { "regression" })
	public void verifySingupWithExisingUserDetails() throws InterruptedException {
		String email = "Dhanu30@yopmail.com";
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.signUp(email, password);
		portalObjects.signUpButtonClick();

		// wait.until(ExpectedConditions.alertIsPresent());
		// Alert alert = driver.switchTo().alert();
		Alert alert = TestUtils.waitForAlertAndSwitch(driver, 10);
		String alertText = alert.getText();
		System.out.println(alertText);
		alert.accept();
		String expAletText = "Provided E-Mail is already in use";
		TestUtils.assertEquals(alertText, expAletText, "Alert text is not matching");
		TestUtils.assertAll();
	}

	// Test case to verify signup with invalid email and valid password
	@Test()
	public void verifySingupWithInvalidEmailAndValidPassword() throws InterruptedException {
		String email = "Dhanu30@yopmail";
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.signUp(email, password);
		TestUtils.assertFalse(portalObjects.signOutButtonState(), "Button is Enabled");
		TestUtils.assertAll();
		System.out.println(portalObjects.signOutButtonState());

	}

	// Test case to verify if password is Weak
	@Test()
	public void verifySingupWithValidEmailAndWeakPassword() throws InterruptedException {
		String expErrorMessage = "Password must contain atelast one lowercase letter, uppercase letter, number and special character and be a minimum of 8 characters in length";
		String email = "Dhanu30@yopmail.com";
		String password = "test";
		portalObjects.goToSignUpPage();
		portalObjects.signUp(email, password);
		TestUtils.assertFalse(portalObjects.signOutButtonState(), "Button is Enabled");
		TestUtils.assertEquals(expErrorMessage, portalObjects.passwordFieldErrorMesageText(),
				"Both error messages are not matching");
		TestUtils.assertAll();
		System.out.println(portalObjects.passwordFieldErrorMesageText());

	}

	// Test case to verify if password is Weak
	@Test()
	public void verifySingupWithValidEmailAndWeakPasswordToTakeScreenshot() throws InterruptedException {
		String expErrorMessage = "Password must contain atelast one lowercase letter, uppercase letter, number and special character and be a minimum of 8 characters in length";
		String email = "Dhanu30@yopmail.com";
		String password = "test";
		portalObjects.goToSignUpPage();
		portalObjects.signUp(email, password);
		portalObjects.goToSignUpPage();
		TestUtils.assertFalse(portalObjects.signOutButtonState(), "Button is Enabled");
		TestUtils.assertEquals(expErrorMessage, portalObjects.passwordFieldErrorMesageText(),
				"Both error messages are not matching");
		TestUtils.assertAll();
		System.out.println(portalObjects.passwordFieldErrorMesageText());

	}

}
