package com.test.scripts;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import BaseClassPackage.BaseDriver;

public class ContactPageTest extends BaseDriver {
	@Test
	public void contactUsClickTest(Method method) {
		
//		test = extent.createTest(method.getName());

		lp.clickContactUs();
		cup.clickContactDetailsButton();
		cup.validateEmailAddress();
	}
}
