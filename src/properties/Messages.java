package properties;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
	private static Locale LOCALE = new Locale(Config.LANGUAGE, Config.COUNTRY);
	private static ResourceBundle MESSAGES = ResourceBundle.getBundle("i18n.MessagesBundle", LOCALE);
	
	public static String getMessage(Class cls, String componentName) {
		return MESSAGES.getString(cls.getName() + "." + componentName);
	}	
}
