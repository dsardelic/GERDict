package properties;

import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static String LANGUAGE = "hr";
	public static String COUNTRY = "HR";
	
	public static void readConfig() {
		Properties config = new Properties();
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
		try {
			config.load(inputStream);
			LANGUAGE = config.getProperty("LANGUAGE");
			COUNTRY = config.getProperty("COUNTRY");
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
