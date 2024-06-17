package com.test.scripts;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseClassPackage.BaseDriver;

public class ContactPageTest extends BaseDriver {

	// Use data from DataProvider - one positive test case and one negative 
	@Test(dataProvider = "emailProvider")
	public void emailValidationTest(Method method, String email) {
		test = extent.createTest(method.getName() + " - " + email);
		lp.clickContactUs();
		cup.clickContactDetailsButton();
		cup.validateEmailAddress(email);
	}

	// DataProvider that returns email addresses
	@DataProvider(name = "emailProvider")
	public Object[][] emailData() {
		return new Object[][] { 
			{ "customercare@firstcry.com" }, // Correct email
			{ "incorrectemail@firstcry.com" } // Incorrect email
		};
	}
}