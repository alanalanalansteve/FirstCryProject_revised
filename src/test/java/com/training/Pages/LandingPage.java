package com.training.Pages;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {

	WebDriver driver;
	WebDriverWait w;

	// Identify elements

	@FindBy(xpath = "/html/body/div[1]/div[5]/div/div[3]/ul/li[2]/span/span")
	WebElement storesAndPreschoolsLink;

	@FindBy(xpath = "/html/body/div[1]/div[5]/div/div[3]/ul/li[2]/ul/li[1]/a/span")
	WebElement findStoresLink;

	@FindBy(id = "search_box")
	WebElement searchBox;

	@FindBy(className = "search-button")
	WebElement searchBtn;

	@FindBy(linkText = "Contact Us")
	WebElement contactUsLink;

	// Constructor
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		this.w = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}

	// Define actions
	public void enterSearchKey(String val) {
		searchBox.clear();
		searchBox.sendKeys(val);
	}

	public void searchBTNClick() {
		searchBtn.click();
	}

	public void hoverAndClickFindStores() throws InterruptedException { // Can remove this throws statement, but left in
																		// place in case I uncomment the debugging block

		// Hover over the "Stores & Preschools" link
		Actions action = new Actions(driver);

		/*
		 * Debugging - left in place for future reference Thread.sleep(5000);
		 * System.out.println("Stores & Preschools Link: " + storesAndPreschoolsLink);
		 * System.out.println("Find Stores Link: " + findStoresLink);
		 */

		action.moveToElement(storesAndPreschoolsLink).perform();

		// Wait for the "Find Stores" link to be visible and clickable
		WebElement findStores = w.until(ExpectedConditions.visibilityOf(findStoresLink));
		w.until(ExpectedConditions.elementToBeClickable(findStoresLink));

		// Click on "Find Stores"
		findStores.click();

		// Switch context to new tab
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));

	}

	public void clickContactUs() {

		// Scroll down to "Contact Us" link and click on it
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", contactUsLink);
		contactUsLink.click();

	}

}
