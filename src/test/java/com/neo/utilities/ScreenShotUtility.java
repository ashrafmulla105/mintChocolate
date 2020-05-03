package com.neo.utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.neo.base.CommonPage;
import com.neo.drivers.DriverManager;

public class ScreenShotUtility {

	private static ScreenShotUtility screenShotUtility;

	public static ScreenShotUtility get() {
		if (screenShotUtility == null)
			screenShotUtility = new ScreenShotUtility();
		return screenShotUtility;
	}


	/**
	 * @description Capture Screenshot and store it in reports folder
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param URL
	 * @throws
	 */
	public static String captureScreenshot(String screenShotName) {
		String screenshotPath = null;
		try {
			screenshotPath = System.getProperty("user.dir") + "\\reports\\screenshots\\"+CommonPage.scenarioName+"\\" + screenShotName + ".jpg";
			TakesScreenshot screenshot = (TakesScreenshot) DriverManager.get().getDriver();
			File srcShot = screenshot.getScreenshotAs(OutputType.FILE);
			File reportScreenshot = new File(screenshotPath);
			FileUtils.copyFile(srcShot, reportScreenshot);
		} catch (Exception e) {
			System.out.println("Error while capturing screenshot");
		}
		
		return screenshotPath;
	}
}
