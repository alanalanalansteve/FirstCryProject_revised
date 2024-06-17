package BaseClassPackage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.training.Pages.ContactUsPage;
import com.training.Pages.LandingPage;
import com.training.Pages.SearchResultsPage;
import com.training.Pages.StoreLocatorPage;

import utility.GetScreenWindow;

public class BaseDriver {

	public static WebDriver driver;
	public LandingPage lp;
	public StoreLocatorPage slp;
	public SearchResultsPage srp;
	public ContactUsPage cup;

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeTest // Runs once, before first test is executed
//	@BeforeSuite
	public void SetExtentReport() {
		
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReport/myReport" + System.currentTimeMillis() + ".html");

		// Settings/Configuration for htmlReporter object
		htmlReporter.config().setDocumentTitle("First Cry Test Suite");
		htmlReporter.config().setReportName("Final Project");
		htmlReporter.config().setTheme(Theme.DARK);

		// Object for ExtentReport
		extent = new ExtentReports();

		// Add this test to the HTML Report
		extent.attachReporter(htmlReporter);

		// Test information as key/value pairs
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("BrowserName", "Firefox");
		extent.setSystemInfo("TesterName", "Alan Tishk");
		extent.setSystemInfo("Course", "Unique System Skills - Selenium and Appium Testing Tools");
		extent.setSystemInfo("ToolUsed", "Selenium");

	}

//	@BeforeTest
	@BeforeMethod
	public void setUp(Method method) {
		driver = new FirefoxDriver();
		driver.get("https://www.firstcry.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		
		lp = new LandingPage(driver);
		slp = new StoreLocatorPage(driver);
		srp = new SearchResultsPage(driver);
		cup = new ContactUsPage(driver);
		
		test = extent.createTest(method.getName());

	}

	@AfterMethod
//	@AfterTest
	public void getstatus(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test case passed: " + result.getName());
		} 
		else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test case failed: " + result.getName());
			String screenLocation = GetScreenWindow.getScreen(driver, result.getName());
			test.addScreenCaptureFromPath(screenLocation, "Screenshot");	
		} 
		else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test case skipped: " + result.getName());
		}
		driver.quit();
	}

//	@AfterTest
	@AfterSuite
	public void tearDown() {
		if (extent != null) { 
			extent.flush();	
		}
		
	}
}
