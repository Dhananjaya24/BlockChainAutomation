package com.blockchain.base;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class TestUtils {
	public static String generateRandomIP() {
		Random rand = new Random();
		return rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256);
	}

	public static String generateRandomEmail() {
		Random rand = new Random();
		int randomNumber = rand.nextInt(10000); // generates a random number from 0 to 9999
		return "user" + randomNumber + "@example.com";

	}

	private static ThreadLocal<SoftAssert> softAssert = ThreadLocal.withInitial(SoftAssert::new);

	// Soft assert
	public static SoftAssert getSoftAssert() {
		return softAssert.get();
	}

	public static void assertEquals(String actual, String expected, String message) {
		softAssert.get().assertEquals(actual, expected, message);
	}

	public static void assertTrue(boolean condition, String message) {
		softAssert.get().assertTrue(condition, message);
	}

	public static void assertFalse(boolean condition, String message) {
		softAssert.get().assertFalse(condition, message);
	}

	public static void assertAll() {
		softAssert.get().assertAll();
		softAssert.remove();

	}

	// Method to switch to alert and return it
	public static Alert waitForAlertAndSwitch(WebDriver driver, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert();
	}

}
