package com.training.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StoreLocatorPage {

	WebDriver driver;
	WebDriverWait w;

	@FindBy(css = ".p1")
	WebElement storeLocatorSearchHeader;

	// Create constructor
	public StoreLocatorPage(WebDriver driver) {
		this.driver = driver;
		this.w = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	public void checkStoreLocatorPageText() {

		String actualStoreLocatorSearchHeader = storeLocatorSearchHeader.getText();

		// Assert that the loaded page displays specific text
		Assert.assertEquals(actualStoreLocatorSearchHeader, "Store Locator");

	}

}
