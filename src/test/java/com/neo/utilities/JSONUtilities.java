package com.neo.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class JSONUtilities {

	private static JSONUtilities jSONUtilities;

	public static JSONUtilities get() {
		if (jSONUtilities == null)
			jSONUtilities = new JSONUtilities();
		return jSONUtilities;
	}

	JSONObject jobject = null;
	String fileData = null;
	byte[] filePath = null;
	private final static String jsonFilePath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\data.json";

	/**
	 * @description set the JSON File path and return the Parent JSON Object
	 * @author ashraf 
	 * @Date 03/05/2020
	 * @return 
	 * @param URL
	 * @throws
	 */
	public void getParentJSONObject() {

		try {
			filePath = Files.readAllBytes(Paths.get(jsonFilePath));
			fileData = new String(filePath);

			jobject = new JSONObject(fileData);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @description The 'jObjectName' is the parent json Object name which can be
	 *              used to get the data from its child objects. The 'key' is the
	 *              child object.
	 * @Date 03/05/2020
	 * @param jObjectName
	 * @param key
	 * @return
	 */
	public String getJSONValue(String jObjectName, String key) {

		JSONObject jsonObj = jobject.getJSONObject(jObjectName);

		return jsonObj.getString(key);

	}

}
