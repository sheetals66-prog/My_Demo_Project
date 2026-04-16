package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	// Path of the properties file where key-value data is stored
	public static String file_path = "src/test/resources/config.properties";
	// Open the properties file
	private static final Properties prop = new Properties();

	// Method to read value from properties file using a key
	public static String getProperties(String Key) throws IOException {
		try {
			// Open the properties file
			FileInputStream file = new FileInputStream(file_path);

			// Load file data into Properties object
			prop.load(file);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		// Return value of the given key
		return prop.getProperty(Key);

	}

}
//configReader class reads key-value data from a .properties file using the Properties class.