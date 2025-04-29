package com.blockchain.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

	WebDriver driver;

	public SignUpPage(WebDriver driver) {
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//input[@id='outlined-basic'])[1]")
	WebElement emailField;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[2]")
	WebElement passwordField;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[3]")
	WebElement confirmPasswordField;

	@FindBy(css = "button.portal-signin-input")
	WebElement signUpBtn;

	@FindBy(xpath = "//h1[text()='Open Capital Network']")
	private WebElement h1Title;

	@FindBy(css = ".cta-container .MuiButtonBase-root")
	WebElement getStarted;

	public void signUp(String email, String password) {
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		signUpBtn.click();
	}

	public boolean getStartedButtonIsdisplayed() {
		return getStarted.isDisplayed();
	}

	public String dashBoardH1Heading() {
		return h1Title.getText();

	}

}
