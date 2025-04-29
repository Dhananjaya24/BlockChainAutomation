package com.blockchain.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.blockchain.base.BaseTest;
import com.blockchain.base.Retry;
import com.blockchain.base.TestUtils;

public class LaunchOCNChildNetworkTests extends BaseTest {

	@Test(groups = { "sanity", "regression" }, retryAnalyzer = Retry.class)
	public void verifyAddingOCNChildNetwork() throws InterruptedException {
		String email = "dhanu30@yopmail.com";
		String password = "Test@1234";
		portalObjects.goToSignUpPage();
		portalObjects.login(email, password);
		portalObjects.goToDAshBoard();
		portalObjects.goToOnboardNodes();
		portalObjects.launchOCNChildNetworkClick();
		String nodeId = "NodeID-123";
		String ipAddress = "234.34.43.34";
		String walletAddress = "0x88fa61d2faA13aad8Fbd5B030372B4A159BbbDFb";
		String networkName = "Child OCN Network";
		portalObjects.addNetworkDetails(networkName, walletAddress);
		portalObjects.addNode(nodeId, ipAddress);
		portalObjects.nodeDetailsNextbtn();
		String nodeIDText = portalObjects.getAddedNodeText();
		System.out.println("Node text from code is : " + portalObjects.getAddedNodeText());
		TestUtils.assertEquals(portalObjects.getAddedNodeText(), nodeId, "Both NodeIDs are not matching");
		System.out.println("Node text from code is : " + portalObjects.getAddedNodeText());
		TestUtils.assertEquals(ipAddress, portalObjects.getAddedIPText(), "Both IPs are not matching");
		TestUtils.assertTrue(portalObjects.provideWalletDetailsStepState(), "State is wrong");
		TestUtils.assertTrue(portalObjects.previewNetworkDetailsIsDisplayed(), "Not Displayed");
		TestUtils.assertTrue(portalObjects.reviewAndSubmitStepState(), "State is wrong");
		TestUtils.assertAll();

	}

}
