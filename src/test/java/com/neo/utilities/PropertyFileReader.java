package com.neo.utilities;

import java.io.FileInputStream;
import java.util.Properties;

import com.neo.base.CommonPage;

public class PropertyFileReader {

	private static PropertyFileReader propertyFileReader;
	
	public static PropertyFileReader get() {
		if (propertyFileReader == null) {
			propertyFileReader = new PropertyFileReader();
		}

		return propertyFileReader;
	}

	private final static String setupFilePath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\properties\\config.properties";
	
	
	/**
	 * @description This method will read all the property files and store those
	 * values to propertiesMap Hashmap 
	 * @Date 03/05/2020 
	 * @return 
	 * @throws
	 */
	public void readPropertyFiles() {

		try {
			// Adding the setup file properties to hashmap
			FileInputStream input = new FileInputStream(setupFilePath);
			Properties prop = new Properties();
			prop.load(input);

			for (String key : prop.stringPropertyNames()) {
				String value = prop.getProperty(key);
				CommonPage.propertiesMap.put(key, value);
			}

			input.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
