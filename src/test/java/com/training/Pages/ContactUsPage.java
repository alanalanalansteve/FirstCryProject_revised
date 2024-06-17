package com.training.Pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ContactUsPage {
	
	WebDriver driver;
	WebDriverWait w;
	
	@FindBy(id="contactdet")
	WebElement contactDetailsBtn;
	
	@FindBy(id="customercare")
	WebElement customerCareAccordion;
	
	@FindBy(css=".R16_link")
	WebElement customerCareEmail;
	
	// Create constructor
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		this.w = new WebDriverWait(driver, Duration.ofSeconds(30));
		PageFactory.initElements(driver, this);
	}
	
	// Define actions
	public void clickContactDetailsButton() {
		
		// Scroll down to "Contact Details" button and click on it
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", contactDetailsBtn);
		contactDetailsBtn.click();
	}
	
	public void validateEmailAddress() {
		
		// Click "Customer Care" accordion to expand details
		customerCareAccordion.click();
		String actualCustomerCareEmail = customerCareEmail.getText();
		Assert.assertEquals(actualCustomerCareEmail, "customercare@firstcry.com");
		
	}

}
