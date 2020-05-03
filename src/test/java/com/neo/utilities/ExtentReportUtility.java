package com.neo.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtility {

	private static ExtentReportUtility extentReportUtility;

	public static ExtentReportUtility get() {
		if (extentReportUtility == null)
			extentReportUtility = new ExtentReportUtility();
		return extentReportUtility;
	}

	ExtentReports extent;
	static ExtentSparkReporter sparkReporter;
	static ExtentTest test;

	/**
	 * @description Set the report folder enconding, report name. @author
	 *              ashraf @Date 03/05/2020 @return @param URL @throws
	 */
	public void setupReport() {

		sparkReporter = new ExtentSparkReporter("./reports/extent.html");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("Automation Test Report");
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setReportName("Automation Practice Report");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Automation Tester", "Ashraf Mulla");
		extent.setSystemInfo("Email ID", "ashraf.mulla1614@gmail.com");
		extent.setSystemInfo("Contact Number", "9560011614");
		extent.setSystemInfo("Project", "NeoSoft Assignment");

	}

	/**
	 * @description Flush and generate the report @author ashraf @Date
	 *              03/05/2020 @return @param URL @throws
	 */
	public void endReport() {
		extent.flush();
	}

	/**
	 * @description Create the test in Extent report with testName provided @author
	 *              ashraf @Date 03/05/2020 @return @param testName @throws
	 */
	public void createTest(String testName) {
		test = extent.createTest(testName);
	}

	/**
	 * @description Generate the report statements with execution status. @author
	 *              ashraf @Date 03/05/2020 @return @param URL @throws
	 */
	public void setReport(String result, String resultText) {
		Markup markup;

		switch (result) {
		case "PASS":
			markup = MarkupHelper.createLabel("PASSED: " + resultText + "<a target=\"_blank\" href=\""
					+ ScreenShotUtility.captureScreenshot(result + "_" + resultText.replaceAll(" ", "_"))
					+ "\"><font color=\"blue\">		[SCREENSHOT]<font/></a>", ExtentColor.GREEN);
			test.pass(markup);
			break;
		case "FAIL":
			markup = MarkupHelper.createLabel("FAILED: " + resultText + "<a target=\"_blank\" href=\""
					+ ScreenShotUtility.captureScreenshot(result + "_" + resultText.replaceAll(" ", "_"))
					+ "\"><font color=\"blue\">		[SCREENSHOT]<font/></a>", ExtentColor.RED);
			test.fail(markup);
			break;
		case "SKIP":
			markup = MarkupHelper.createLabel("SKIPPED: " + resultText + "<a target=\"_blank\" href=\""
					+ ScreenShotUtility.captureScreenshot(result + "_" + resultText.replaceAll(" ", "_"))
					+ "\"><font color=\"blue\">		[SCREENSHOT]<font/></a>", ExtentColor.YELLOW);
			test.skip(markup);
			break;
		case "INFO":
			markup = MarkupHelper.createLabel("INFO: " + resultText + "<a target=\"_blank\" href=\""
					+ ScreenShotUtility.captureScreenshot(result + "_" + resultText.replaceAll(" ", "_"))
					+ "\"><font color=\"blue\">		[SCREENSHOT]<font/></a>", ExtentColor.ORANGE);
			test.info(markup);
			break;
		}

		// <a target="_blank" href=\""+ScreenShotUtility.captureScreenshot(result + "_"
		// + resultText.replaceAll(" ", "_"))+"\">screenshot</a>

		ScreenShotUtility.captureScreenshot(result + "_" + resultText.replaceAll(" ", "_"));
	}
}
