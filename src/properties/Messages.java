package properties;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

    private static Locale locale = new Locale(Config.language, Config.country);

    private static ResourceBundle messages = ResourceBundle.getBundle("i18n.MessagesBundle", locale);

    public static String getMessage(Class<?> cls, String componentName) {
        return messages.getString(cls.getName() + "." + componentName);
    }
}
