package constants;

import configuration.Configuration;

public class FrameworkConstants {

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    static Configuration config = new Configuration(System.getProperty("user.dir") + "/source/config.properties");
    public static final String URL = config.getProperty("URL");
    public static final String BROWSER = config.getProperty("BROWSER");
    public static final String REMOTE_URL = config.getProperty("REMOTE_URL");
    public static final String REMOTE_PORT = config.getProperty("REMOTE_PORT");
    public static final String HEADLESS = config.getProperty("HEADLESS");
    public static final String TARGET = config.getProperty("TARGET");

    public static void main(String[] args) {
        System.out.println(PROJECT_PATH);
        System.out.println(URL);
        System.out.println(TARGET);
    }

}
