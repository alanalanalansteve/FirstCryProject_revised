package com.test.scripts;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import BaseClassPackage.BaseDriver;

public class StoresButtonTest extends BaseDriver {
	@Test
	public void storesButtonClickTest(Method method) throws InterruptedException { 

		test = extent.createTest(method.getName());
		
		lp.hoverAndClickFindStores();
		slp.checkStoreLocatorPageText();
	}
}
