package com.test.scripts;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import BaseClassPackage.BaseDriver;

public class SearchTest extends BaseDriver {
	@Test
	public void searchForAnythingTest(Method method) {

		test = extent.createTest(method.getName());

		lp.enterSearchKey("diaper");
		lp.searchBTNClick();
		srp.checkDiaperSearchHeader();
		
		test.info("ugggggghhhh");
	}
}
