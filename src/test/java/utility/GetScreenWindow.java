package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetScreenWindow {

	public static String getScreen(WebDriver driver, String ScreenshotName) throws IOException { 

		// 1. Convert the WebDriver object into TakesScreenshot object
		TakesScreenshot ts = (TakesScreenshot) driver;

		// 2. Call the getScreenshotAs method to create a raw image
		File f_temp = ts.getScreenshotAs(OutputType.FILE);

		// 3. Copy the raw file into the desired location
		String location = System.getProperty("user.dir") + "/test-output/Screenshots/IMG_" + System.currentTimeMillis()
				+ ".jpg";
		File target = new File(location);
		FileUtils.copyFile(f_temp, target);

		return location;

	}

}
