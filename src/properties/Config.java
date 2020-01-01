package properties;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

public class Config {

    public static String language;
    public static String country;
    public static String statsFolder;
    public static String statsFileName;
    public static int recentAnswersLimit;

    public static void readConfig() {
        Properties config = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
        try {
            config.load(inputStream);
            language = config.getProperty("LANGUAGE", "en");
            country = config.getProperty("COUNTRY", "EN");
            String defaultStatsFolder = "";
            try {
                defaultStatsFolder = new File(
                        Config.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            statsFolder = config.getProperty("STATS_FOLDER", defaultStatsFolder);
            statsFileName = config.getProperty("STATS_FILENAME", "GERDict_stats.ser");
            recentAnswersLimit = Integer.parseInt(config.getProperty("RECENT_TRIES_LIMIT", "20"));
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
