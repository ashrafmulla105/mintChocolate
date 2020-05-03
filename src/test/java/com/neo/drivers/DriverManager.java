package com.neo.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.neo.base.CommonPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager extends CommonPage{

	private static DriverManager driverManager;

	public static DriverManager get() {
		if (driverManager == null) {
			driverManager = new DriverManager();
		}

		return driverManager;
	}
	
	private WebDriver driver;
	
	/**
	 * @description Method to get the Driver for specified browser
	 * @author ashraf
	 * @Date 03/05/2020
	 * @return
	 * @param
	 * @throws @return
	 */
	public WebDriver getDriver() {

		if (driver == null) {

			switch (browserName) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "ie":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			default:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			}

		}

		return driver;
	}
}
