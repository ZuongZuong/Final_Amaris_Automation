package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

	private Properties prop;
	private InputStream input;
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
		try {
			prop = new Properties();
			input = new FileInputStream(filePath);

			if (input != null) {
				prop.load(input);
			} else {
				throw new FileNotFoundException("property file '" + filePath + "' not found in the classpath");
			}

			result = prop.getProperty(propertyName);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
}
