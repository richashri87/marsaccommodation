package io.billie.rest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {
	
	public Properties readEnvironmentPropertyFile() {
		String environment = System.getProperty("env");
		Properties properties = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(String.format(System.getProperty("user.dir")) + String.format("/src/test/resources/%s.properties",environment));
			properties.load(input);
		} catch (IOException ex) {
			
		}
		return properties;
	}

}
