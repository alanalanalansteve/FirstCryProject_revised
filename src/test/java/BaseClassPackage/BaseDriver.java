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
import org.testng.annotations.BeforeSuite;

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

	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void SetExtentReport() {

		// Initialize object here rather than outside the method because extent was
		// occasionally null
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")
				+ "/test-output/ExtentReport/myReport" + System.currentTimeMillis() + ".html");

		// Settings/Configuration for htmlReporter object
		htmlReporter.config().setDocumentTitle("First Cry Test Suite");
		htmlReporter.config().setReportName("Final Project");
		htmlReporter.config().setTheme(Theme.DARK);

		// Object for ExtentReport
		extent = new ExtentReports();

		// Add this test to the HTML Report
		extent.attachReporter(htmlReporter);
		if (extent == null) {
			System.out.println("ExtentReports is null after initialization");
		}

		// Test information as key/value pairs
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("BrowserName", "Firefox");
		extent.setSystemInfo("TesterName", "Alan Tishk");
		extent.setSystemInfo("Course", "Unique System Skills - Selenium and Appium Testing Tools");
		extent.setSystemInfo("ToolUsed", "Selenium");

	}

	@BeforeMethod
	public void setUp(Method method) {

		// Initialize WebDriver before each test method, quit it after each method 
		// (see @AfterMethod)
		driver = new FirefoxDriver();
		driver.get("https://www.firstcry.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		// Initialize page objects
		lp = new LandingPage(driver);
		slp = new StoreLocatorPage(driver);
		srp = new SearchResultsPage(driver);
		cup = new ContactUsPage(driver);

		// Moved this call inside test methods to create test names dynamically if using a DataProvider
//		test = extent.createTest(method.getName()); 

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

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
		
		driver.quit(); // Quit driver after each test method

	}

	@AfterSuite
	public void afterSuite() {

		if (extent != null) {
			extent.flush();
		}
	}
}
