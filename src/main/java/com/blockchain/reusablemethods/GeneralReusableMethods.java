package com.blockchain.reusablemethods;

import java.util.Random;

public class GeneralReusableMethods {
	public static String generateRandomIP() {
		Random rand = new Random();
		return rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256);
	}

}
