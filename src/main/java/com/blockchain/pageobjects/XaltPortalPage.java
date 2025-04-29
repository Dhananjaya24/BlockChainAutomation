package com.blockchain.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.blockchain.reusablemethods.ReusableWrapperMethods;
import com.blockchain.reusablemethods.GeneralReusableMethods;

public class XaltPortalPage extends ReusableWrapperMethods {

	WebDriver driver;

	public XaltPortalPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Web elements
	@FindBy(css = ".cta-container .MuiButtonBase-root")
	WebElement getStarted;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[1]")
	WebElement emailField;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[2]")
	WebElement passwordField;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[3]")
	WebElement confirmPasswordField;

	@FindBy(xpath = "//button[contains(text(), 'Sign Up') and @tabindex='0']")
	WebElement signUpButton;

	@FindBy(xpath = "//button[contains(text(), 'Sign Out')]")
	WebElement signOutButton;

	@FindBy(xpath = "//button[contains(text(), 'Sign Up') and @tabindex='-1']")
	WebElement signOutButtonState;

	@FindBy(xpath = "//p[contains(text(), 'Password must contain')]")
	WebElement passwordFieldErrorMesage;

	@FindBy(xpath = "//button[contains(text(), 'Click here to sign in')]")
	WebElement loginRedirectBtn;

	// Login Page
	@FindBy(xpath = "(//*[@id='outlined-basic'])[1]")
	WebElement loginEmailField;

	@FindBy(xpath = "(//*[@id='outlined-basic'])[2]")
	WebElement loginPasswordField;

	@FindBy(xpath = "//button[contains(text(), 'Sign In')]")
	WebElement loginSubmitButton;

	@FindBy(xpath = "//p[contains(text(), 'Permissioned EVM')]")
	WebElement dashboardText;

	@FindBy(css = "button.MuiButton-containedPrimary")
	WebElement goToOnboard;

	@FindBy(css = "button.MuiButton-containedPrimary")
	WebElement onboardBtn;

	@FindBy(xpath = "//h1[text()='Open Capital Network']")
	WebElement h1Title;

	@FindBy(xpath = "//h2[text()='Onboard OCN Node']")
	WebElement OCNNodeH2;

	@FindBy(xpath = "//h2[contains(text(),'OCN Child Network')]")
	WebElement OCNChildNetworkH2;

	@FindBy(xpath = "//h2[contains(text(), 'Onboard')]/following-sibling::div[1]")
	WebElement onboardOCNBtn;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[1]")
	WebElement nodeIdInput;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[2]")
	WebElement ipAddressInput;

	@FindBy(css = ".add-button")
	WebElement addNodeBtn;

	@FindBy(xpath = "//div[@class='MuiDataGrid-cellContent' and contains(text(),'NodeID')]")
	WebElement addedNode;

	@FindBy(xpath = "//div[@data-field='publicIp']//div[@class='MuiDataGrid-cellContent']")
	WebElement addedIP;

	@FindBy(css = "div[data-field='walletAddress'] > div.MuiDataGrid-cellContent")
	WebElement addedWalletAddress;

	@FindBy(xpath = "//button[text()='Next']")
	WebElement nextBtn;

	@FindBy(xpath = "//input[@id='outlined-basic']")
	WebElement walletInput;

	@FindBy(xpath = "//button[contains(text(),'Add Wallet')]")
	WebElement addWalletBtn;

	@FindBy(xpath = "//button[text()='Submit']")
	WebElement submitBtn;

	@FindBy(xpath = "//h2[contains(text(),'Launch OCN')]")
	WebElement launchOCNBtn;

	@FindBy(xpath = "//div[text()='Preview Network Details']")
	WebElement previewNetworkLabel;

	@FindBy(css = ".Mui-active .Mui-active")
	WebElement reviewStepIndicator;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[1]")
	WebElement networkNameField;

	@FindBy(xpath = "(//input[@id='outlined-basic'])[2]")
	WebElement walletAddressField;

	@FindBy(xpath = "//button[text()='Next']")
	WebElement networkDetailsNextBtn;

	@FindBy(xpath = "//div[text()='Preview Network Details']")
	WebElement previewNetworkDetails;

	@FindBy(xpath = "//button[text()='Get Started']")
	WebElement gotoSignup;

	// Action methods
	public boolean getStartedButtonIsdisplayed() {
		return getStarted.isDisplayed();
	}

	public String dashBoardH1Heading() {
		return h1Title.getText();

	}

	public void signUpPageRedirectClick() {

		waitForWebElementToAppear(gotoSignup);
		gotoSignup.click();

	}

	public void goTo() {
		driver.get("https://xaltsocnportal.web.app/");

	}

	public void goToSignUpPage() {
		waitForWebElementToAppear(getStarted);
		getStarted.click();

	}

	public void signUp(String email, String password) throws InterruptedException {

		waitForWebElementToAppear(emailField);
		emailField.sendKeys(email);
		waitForWebElementToAppear(passwordField);
		passwordField.sendKeys(password);
		waitForWebElementToAppear(confirmPasswordField);
		confirmPasswordField.sendKeys(password);

	}

	public void signUpButtonClick() {

		waitForWebElementToAppear(signUpButton);
		signUpButton.click();
	}

	public boolean signOutButtonState() {
		return signOutButtonState.isEnabled();
	}

	public String passwordFieldErrorMesageText() {
		waitForWebElementToAppear(passwordFieldErrorMesage);
		return passwordFieldErrorMesage.getText();

	}

	public OnboardingPage clickOnboard() {
		onboardBtn.click();
		return new OnboardingPage(driver);
	}

	public void signOut() {
		waitForWebElementToAppear(signOutButton);
		signOutButton.click();
	}

	public void login(String email, String password) throws InterruptedException {

		waitForWebElementToAppear(loginRedirectBtn);
		loginRedirectBtn.click();
		loginEmailField.sendKeys(email);
		loginPasswordField.sendKeys(password);

	}

	public void loginButtonClick() {
		waitForWebElementToAppear(loginSubmitButton);
		loginSubmitButton.click();

	}

	public void goToDAshBoard() {

		waitForWebElementToAppear(goToOnboard);
		goToOnboard.click();

	}

	public String getDashboardText() {
		waitForWebElementToAppear(dashboardText);
		return dashboardText.getText();

	}

	public String signOutButtonText() {
		waitForWebElementToAppear(signOutButton);
		return signOutButton.getText();
	}

	public boolean signOutButtonDisaply() {
		waitForWebElementToAppear(signOutButton);
		return signOutButton.isDisplayed();
	}

	public boolean getStartedButtonDisaply() {
		waitForWebElementToAppear(getStarted);
		return getStarted.isDisplayed();
	}

	public void goToOnboardNodes() {
		waitForWebElementToAppear(goToOnboard);
		goToOnboard.click();
		waitForWebElementToAppear(OCNNodeH2);
	}

	public String onBoardOCNNodeButtonHeading() {
		waitForWebElementToAppear(OCNNodeH2);
		return OCNNodeH2.getText();

	}

	public String launchOCNChildNetworkHeading() {
		waitForWebElementToAppear(OCNChildNetworkH2);
		return OCNChildNetworkH2.getText();

	}

	public void gotoOnboardOCNNode() {

		waitForWebElementToAppear(onboardOCNBtn);
		onboardOCNBtn.click();
	}

	public void addNode(String nodeId, String ip) throws InterruptedException {

		waitForWebElementToAppear(nodeIdInput);
		nodeIdInput.sendKeys(nodeId);
		ipAddressInput.sendKeys(ip);
		waitForWebElementToAppear(addNodeBtn);
		addNodeBtn.click();

	}

	public String getAddedNodeText() {
		waitForWebElementToAppear(addedNode);
		return addedNode.getText();

	}

	public String getAddedIPText() {
		waitForWebElementToAppear(addedIP);
		return addedIP.getText();

	}

	public String getAddedWalletAddressText() {
		waitForWebElementToAppear(addedWalletAddress);
		return addedWalletAddress.getText();

	}

	public boolean provideWalletDetailsStepState() {
		return reviewStepIndicator.isEnabled();
	}

	public boolean reviewAndSubmitStepState() {
		return reviewStepIndicator.isEnabled();
	}

	public boolean provideWalletDetailsIsDisplayed() {
		return getStarted.isDisplayed();
	}

	public void addWallet(String walletAddress) throws InterruptedException {
		waitForWebElementToAppear(nextBtn);
		nextBtn.click();
		waitForWebElementToAppear(walletInput);
		walletInput.sendKeys(walletAddress);
		waitForWebElementToAppear(addWalletBtn);
		addWalletBtn.click();
	}

	public void previewNodeDetailsNextButton() {

		waitForWebElementToAppear(nextBtn);
		nextBtn.click();

	}

	public void finalSubmit() {
		waitForWebElementToAppear(submitBtn);
		submitBtn.click();
	}

	public void launchOCN(String nodeId, String walletAddress) throws InterruptedException {

		waitForWebElementToAppear(launchOCNBtn);
		launchOCNBtn.click();
		waitForWebElementToAppear(nodeIdInput);
		nodeIdInput.sendKeys(nodeId);
		ipAddressInput.sendKeys(walletAddress);
		nextBtn.click();
	}

	public boolean verifyPreviewStep() {
		return previewNetworkLabel.isDisplayed() && reviewStepIndicator.isEnabled();
	}

	// Helper to add multiple nodes
	public void addMultipleNodes(int count) throws InterruptedException {
		for (int i = 1; i <= count; i++) {
			String nodeId = "NodeID-" + i;
			String ip = GeneralReusableMethods.generateRandomIP();
			nodeIdInput.sendKeys(nodeId);
			ipAddressInput.sendKeys(ip);
			waitForWebElementToAppear(addNodeBtn);
			addNodeBtn.click();

		}
	}

	public void launchOCNChildNetworkClick() {
		waitForWebElementToAppear(OCNChildNetworkH2);
		OCNChildNetworkH2.click();

	}

	public void addNetworkDetails(String networkName, String walletAddress) {
		waitForWebElementToAppear(networkNameField);
		networkNameField.sendKeys(networkName);
		walletAddressField.sendKeys(walletAddress);
		networkDetailsNextBtn.click();

	}

	public void nodeDetailsNextbtn() {
		waitForWebElementToAppear(nextBtn);
		nextBtn.click();
	}

	public boolean previewNetworkDetailsIsDisplayed() {

		waitForWebElementToAppear(previewNetworkDetails);
		return previewNetworkDetails.isDisplayed();
	}

}
