package com.blockchain.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OnboardingPage {

	WebDriver driver;

	public OnboardingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[contains(text(), 'Onboard')]/following-sibling::div[1]")
	WebElement onboardOCNBtn;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[1]")
	WebElement nodeIdInput;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[2]")
	WebElement nodeIPInput;

	@FindBy(css = ".add-button")
	WebElement addNodeBtn;

	@FindBy(xpath = "//button[text()='Next']")
	WebElement nextBtn;

	public void startOCNOnboarding() {
		onboardOCNBtn.click();
	}

	public void addNode(String nodeId, String ip) {
		nodeIdInput.sendKeys(nodeId);
		nodeIPInput.sendKeys(ip);
		addNodeBtn.click();
	}

	public void goToWalletStep() {
		nextBtn.click();
	}

}
