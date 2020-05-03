package com.neo.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.neo.utilities.ExtentReportUtility;
import com.neo.utilities.JSONUtilities;
import com.neo.utilities.PropertyFileReader;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(
		features= {"src/test/java/com/neo/features"},
		glue={"com.neo.stepdefs"},
				tags = {"@Test"}
		)
public class Runner extends AbstractTestNGCucumberTests{

	@BeforeSuite
	public static void setup() {
		PropertyFileReader.get().readPropertyFiles();
		JSONUtilities.get().getParentJSONObject();
		ExtentReportUtility.get().setupReport();
	}
	
	@AfterSuite
	public void tearDown() {
		ExtentReportUtility.get().endReport();
	}
	
	
}
