package constants;

import configuration.Configuration;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final Configuration CONFIG = new Configuration(PROJECT_PATH + "/source/config.properties");
    public static final String URL = CONFIG.getProperty("URL");
    public static final String BROWSER = CONFIG.getProperty("BROWSER");
    public static final String REMOTE_URL = CONFIG.getProperty("REMOTE_URL");
    public static final String REMOTE_PORT = CONFIG.getProperty("REMOTE_PORT");
    public static final String HEADLESS = CONFIG.getProperty("HEADLESS");
    public static final String TARGET = CONFIG.getProperty("TARGET");
}
