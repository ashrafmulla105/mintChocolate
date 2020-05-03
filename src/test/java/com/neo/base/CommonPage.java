package com.neo.base;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.neo.actions.BrowserActions;
import com.neo.drivers.DriverManager;
import com.neo.utilities.JSONUtilities;

public class CommonPage {

	private static CommonPage commonPage;

	public static CommonPage get() {
		if (commonPage == null)
			commonPage = new CommonPage();
		return commonPage;
	}

	public static HashMap<String, String> propertiesMap = new HashMap<String, String>();

	public static String JSONObj;
	public static String browserName;
	WebDriver driver;
	public static BrowserActions actions;
	public static String scenarioName;

	/**
	 * @description Launch the browser specified
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param 
	 * @throws
	 */
	public void initiateDriver() {
		browserName = getProperty("browserName");
		actions = new BrowserActions(DriverManager.get().getDriver());

	}

	/**
	 * @description Get the value of the key provided from properties file
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param key
	 * @throws
	 */
	public static String getProperty(String key) {
		return propertiesMap.get(key);
	}

	/**
	 * @description Get the value of the key provided from JSON File
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param element,elementName 
	 * @throws
	 */
	public static String getTestJSONValue(String Key) {
		return JSONUtilities.get().getJSONValue(JSONObj, Key);
	}

}
