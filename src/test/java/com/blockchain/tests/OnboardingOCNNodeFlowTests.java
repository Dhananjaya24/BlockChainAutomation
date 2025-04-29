package com.blockchain.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.blockchain.base.BaseTest;
import com.blockchain.base.Retry;
import com.blockchain.base.TestUtils;
import com.blockchain.pageobjects.XaltPortalPage;

public class OnboardingOCNNodeFlowTests extends BaseTest {

	@Test(enabled = false)
	public void verifyLogin() throws InterruptedException {

		String email = "dhanu30@yopmail.com";
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.login(email, password);

	}

	@Test(groups = { "sanity", "regression" }, retryAnalyzer = Retry.class)
	public void verifyDashboardOptionNdesArePresent() throws InterruptedException {
		String email = "dhanu30@yopmail.com";
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.login(email, password);
		portalObjects.goToDAshBoard();
		String expectedOCNNodeText = "Onboard OCN Node";
		String expectedLaunchOCNchildNetworkText = "Launch OCN Child Network";
		portalObjects.goToOnboardNodes();
		TestUtils.assertEquals(portalObjects.onBoardOCNNodeButtonHeading(), expectedOCNNodeText,
				"OCN Node heading mismatch");
		TestUtils.assertEquals(portalObjects.launchOCNChildNetworkHeading(), expectedLaunchOCNchildNetworkText,
				"Child network heading mismatch");
		TestUtils.assertAll();

	}

	@Test(groups = { "sanity", "regression" }, dependsOnMethods = { "verifyDashboardOptionNdesArePresent" })
	public void verifyAddingOnboardOCNNode() throws InterruptedException {
		String email = "dhanu30@yopmail.com";
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.login(email, password);
		portalObjects.goToDAshBoard();
		portalObjects.goToOnboardNodes();
		String nodeId = "NodeID-123";
		String ipAddress = "234.34.43.34";
		String walletAddress = "0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb";
		// portalObjects.goToOnboardNodes();
		portalObjects.gotoOnboardOCNNode();
		portalObjects.addNode(nodeId, ipAddress);
		TestUtils.assertEquals(nodeId, portalObjects.getAddedNodeText(), "Both NodeIDs are not matching");
		TestUtils.assertEquals(ipAddress, portalObjects.getAddedIPText(), "Both IPs are not matching");
		TestUtils.assertTrue(portalObjects.provideWalletDetailsStepState(), "State is wrong");
		portalObjects.addWallet(walletAddress);
		TestUtils.assertEquals(walletAddress, portalObjects.getAddedWalletAddressText(),
				"Both Wallet Addresses are not matching");
		TestUtils.assertTrue(portalObjects.reviewAndSubmitStepState(), "State is wrong");
		System.out.println(portalObjects.provideWalletDetailsStepState());
		portalObjects.previewNodeDetailsNextButton();
		portalObjects.finalSubmit();
		TestUtils.assertAll();

	}

	@Test(dependsOnMethods = { "verifyDashboardOptionNdesArePresent" })
	public void verifyAddingMultipleNodes() throws InterruptedException {
		String email = "dhanu30@yopmail.com";
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.login(email, password);
		portalObjects.goToDAshBoard();
		portalObjects.goToOnboardNodes();

		String walletAddress = "0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb";
		portalObjects.gotoOnboardOCNNode();
		for (int i = 1; i <= 5; i++) { // repeat 5 times
			String nodeId = "NodeID-" + i;
			String randomIP = TestUtils.generateRandomIP();
			portalObjects.addNode(nodeId, randomIP);
		}

		portalObjects.addWallet(walletAddress);
		portalObjects.previewNodeDetailsNextButton();
		portalObjects.finalSubmit();

	}
}
