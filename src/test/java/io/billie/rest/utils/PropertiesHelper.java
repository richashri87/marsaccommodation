package io.billie.rest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.EnumUtils;

public class PropertiesHelper {
	
	public Properties readEnvironmentPropertyFile() {
		String environment = System.getProperty("env");
		if(! isEnvDefined(environment)) {
			environment	= Environments.dev.toString();
		}
		
		Properties properties = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(String.format(System.getProperty("user.dir")) + String.format("/src/test/resources/%s.properties",environment));
			properties.load(input);
		} catch (IOException ex) {
			
		}
		return properties;
	}
	
	private boolean isEnvDefined(String envName) {
		return EnumUtils.isValidEnum(Environments.class, envName)
	}

	private enum Environments{
		local,dev,test,staging
	}
}
