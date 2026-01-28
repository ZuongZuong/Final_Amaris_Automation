package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import utils.LogUtils;

public class Configuration {

	private String filePath;

	public Configuration(String filePath) {
		this.filePath = filePath;
	}

	public String getProperty(String propertyName) {
		// Check if there is a system property override (from command line
		// -DpropName=value)
		String systemProp = System.getProperty(propertyName);
		if (systemProp != null && !systemProp.isEmpty()) {
			return systemProp;
		}

		String result = "";
		try (InputStream input = new FileInputStream(filePath)) {
			Properties prop = new Properties();
			prop.load(input);
			result = prop.getProperty(propertyName);
		} catch (IOException e) {
			LogUtils.error("Failed to load configuration file: " + filePath + " - " + e.getMessage());
		}
		return result;
	}
}
