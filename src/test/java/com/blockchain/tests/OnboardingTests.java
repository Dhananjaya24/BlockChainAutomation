package com.blockchain.tests;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class OnboardingTests {

	public static void main(String[] args) throws InterruptedException {
		// Setup ChromeDriver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();

		try {
			// STEP 1: Launch the website
			driver.get("https://xaltsocnportal.web.app/");

			// Move to Signup page
			driver.findElement(By.cssSelector(".cta-container .MuiButtonBase-root")).click();
			Thread.sleep(3000);

			// STEP 2: Sign Up
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[1]")).sendKeys("dhanu31@yopmail.com");
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]")).sendKeys("Test@1234");
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[3]")).sendKeys("Test@1234");
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("button.portal-signin-input")).click();
			//// button[normalize-space()='Sign Up']
			Thread.sleep(10000); // wait for redirection

			// SignOUT
			driver.findElement(By.xpath("//button[contains(text(), 'Sign Out')]")).click();

			// Step 3: Login
			// Move to login page
			driver.findElement(By.xpath("//button[contains(text(), 'Click here to sign in')]")).click();
			// Login
			driver.findElement(By.xpath("(//*[@id='outlined-basic'])[1]")).sendKeys("dhanu3@yopmail.com");
			driver.findElement(By.xpath("(//*[@id='outlined-basic'])[2]")).sendKeys("Test@1234");
			driver.findElement(By.xpath("//button[contains(text(), 'Sign In')]")).click();
			Thread.sleep(5000);

			// Move to onboard page
			driver.findElement(By.cssSelector("button.MuiButton-containedPrimary")).click();
			Thread.sleep(3000);

			// Add Onboard OCN node
			driver.findElement(By.xpath("//h2[contains(text(), 'Onboard')]/following-sibling::div[1]")).click();
			Thread.sleep(6000);

			// Add Nodeid and IP address
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[1]")).sendKeys("NodeID-123");
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]")).sendKeys("12.34.54.33");
			Thread.sleep(3000);
			driver.findElement(By.cssSelector(".add-button")).click();
			Thread.sleep(3000);

			for (int i = 1; i <= 5; i++) { // repeat 5 times
				String nodeId = "NodeID-" + i;
				String randomIP = generateRandomIP();
				driver.findElement(By.xpath("(//input[@id='outlined-basic'])[1]")).sendKeys(nodeId);
				driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]")).sendKeys(randomIP);
				Thread.sleep(3000);
				driver.findElement(By.cssSelector(".add-button")).click();
				Thread.sleep(3000);
			}

			// Move to Add Wallet
			driver.findElement(By.xpath("//button[text()='Next']")).click();
			Thread.sleep(3000);

			// Add wallet
			driver.findElement(By.xpath("//input[@id='outlined-basic']"))
					.sendKeys("0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[contains(text(),'Add Wallet')]")).click();

			// Move to preview node details and preview wallet details
			driver.findElement(By.xpath("//button[text()='Next']")).click();

			// Final Submit
			driver.findElement(By.xpath("//button[text()='Submit']")).click();

			// Step: Launch OCN child network
			// Launch OCN node
			driver.findElement(By.xpath("//h2[contains(text(),'Launch OCN')]")).click();
			Thread.sleep(3000);

			// Provide network details
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[1]")).sendKeys("NodeID-123");
			driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]"))
					.sendKeys("0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb");
			driver.findElement(By.xpath("//button[normalize-space(text())='Next']")).click();
			Thread.sleep(3000);

			// Add Nodeid and IP address
			for (int i = 1; i <= 5; i++) { // repeat 5 times
				String nodeId = "NodeID-" + i;
				String randomIP = generateRandomIP();
				driver.findElement(By.xpath("(//input[@id='outlined-basic'])[1]")).sendKeys(nodeId);
				driver.findElement(By.xpath("(//input[@id='outlined-basic'])[2]")).sendKeys(randomIP);
				Thread.sleep(3000);
				driver.findElement(By.cssSelector(".add-button")).click();
				Thread.sleep(3000);
			}

			// Move to Preview network details
			driver.findElement(By.xpath("//button[text()='Next']")).click();
			Thread.sleep(3000);

			// Verification points in 3rd step
			// Verify User is moved to Network details
			driver.findElement(By.xpath("//div[text()='Preview Network Details']")).isDisplayed();

			// Verify 3rd step Review and submit number is Highlighted
			driver.findElement(By.cssSelector(".Mui-active .Mui-active")).isEnabled();

			// Final Submit
			driver.findElement(By.xpath("//button[text()='Submit']")).click();

			// STEP 3: Navigate to Onboard Node Form
			driver.findElement(By.xpath("//button[contains(text(),'Onboard Node')]")).click();
			Thread.sleep(2000);

			// STEP 4: Add Node
			WebElement nodeIdField = driver.findElement(By.name("nodeId"));
			WebElement nodeIpField = driver.findElement(By.name("ipAddress"));
			WebElement addNodeBtn = driver.findElement(By.xpath("//button[contains(text(),'ADD NODE')]"));

			nodeIdField.sendKeys("NodeID-123");
			nodeIpField.sendKeys("192.168.1.1");
			addNodeBtn.click();

			// STEP 5: Next Step
			driver.findElement(By.xpath("//button[contains(text(),'NEXT')]")).click();
			Thread.sleep(1000);

			// STEP 6: Add Wallet
			driver.findElement(By.name("walletAddress")).sendKeys("0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb");
			driver.findElement(By.name("permissionType")).click(); // assume dropdown exists
			driver.findElement(By.xpath("//option[text()='ReadWrite']")).click();
			driver.findElement(By.xpath("//button[contains(text(),'ADD WALLET')]")).click();

			driver.findElement(By.xpath("//button[contains(text(),'NEXT')]")).click();
			Thread.sleep(1000);

			// STEP 7: Submit
			driver.findElement(By.xpath("//button[contains(text(),'SUBMIT')]")).click();
			Thread.sleep(3000);

			System.out.println("✅ Test Completed Successfully");

		} catch (Exception e) {
			System.out.println("❌ Test Failed: " + e.getMessage());
		} finally {
			// driver.quit();
		}
	}

	private static String generateRandomIP() {
		Random rand = new Random();
		return rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256);
	}

}
