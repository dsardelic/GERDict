package properties;

import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static String language = "hr";
	public static String country = "HR";
	
	public static void readConfig() {
		Properties config = new Properties();
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
		try {
			config.load(inputStream);
			language = config.getProperty("LANGUAGE");
			country = config.getProperty("COUNTRY");
		} catch (Exception e) {
			System.err.println("Error reading config.properties.");
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e2) {
				System.err.println("Error closing config.properties.");
			}
		}
	}
}
