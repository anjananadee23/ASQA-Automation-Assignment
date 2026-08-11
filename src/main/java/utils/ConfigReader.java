package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads environment/config values from config.properties so that
 * tests do not hard-code the base URL, browser, or wait timings.
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    private static void load() {
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
                properties.load(fis);
            } catch (IOException e) {
                throw new RuntimeException("Unable to read config.properties at " + CONFIG_PATH, e);
            }
        }
    }

    public static String get(String key) {
        load();
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing property '" + key + "' in config.properties");
        }
        return value;
    }

    public static String getBaseUrl() { return get("baseUrl"); }
    public static String getBrowser() { return get("browser"); }
    public static int getImplicitWaitSeconds() { return Integer.parseInt(get("implicitWaitSeconds")); }
    public static int getExplicitWaitSeconds() { return Integer.parseInt(get("explicitWaitSeconds")); }
    public static boolean isHeadless() { return Boolean.parseBoolean(get("headless")); }
}
