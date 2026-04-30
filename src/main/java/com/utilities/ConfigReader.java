package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static String file_path = "src/test/resources/config.properties";

	private static final Properties prop = new Properties();

	public static String getProperties(String Key) throws IOException {
		try {

			FileInputStream file = new FileInputStream(file_path);
			prop.load(file);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return prop.getProperty(Key);

	}

}
