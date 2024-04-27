package com.fairplay.library;

import com.github.javafaker.Faker;

/*
 * @Author: Tejal Gavade.
 * @Since : December 2022
 * @Discription : This class contains methods to Randomize the username, mobile number, password for signup page of Fairplay UI.
 */

public class RandomizeData {

	public String randomizeName() {
		Faker faker = new Faker();
		String firstName = faker.name().firstName();
		return firstName;
	}

	public String randomizeNumber() {
		Faker faker = new Faker();
		String mobileNumber = faker.number().digits(10);
		return mobileNumber;
	}
	
	public String randomize12DigitNumber() {
		Faker faker = new Faker();
		String mobileNumber = faker.number().digits(12);
		return mobileNumber;
	}

	public String randomizeStrongPassword() {
		Faker faker = new Faker();
		String password = faker.bothify("P???@###");
		return password;
	}
	
	public String randomizeTelegramLink() {
		Faker faker = new Faker();
		String link = faker.bothify("https://t.me/fairplay_club###");
		return link;
	}

	public String randomizeWeakPassword() {
		Faker faker = new Faker();
		String password = faker.bothify("??????");
		return password;
	}

	public String randomizeIFSCCode() {

		String[] str = { "ICIC0006650", 
				"YESB0000014", 
				"ICIC0006997", 
				"HDFC0000522", 
				"HSBC0500002", 
				"UBIN0535125", 
				"UBIN0532801", 
				"SBIN0070988", 
				"CNRB0000606", 
				"KKBK0000263", 
				"RATN0000112"};

		Faker faker = new Faker();
		String num = faker.number().digits(1);
		int i = Integer.parseInt(num);
		return str[i];
		
		
	}

}
