package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private LogUtils() {}

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    public static void info(String message) {
        LogManager.getLogger().info(message);
    }

    public static void warn(String message) {
        LogManager.getLogger().warn(message);
    }

    public static void error(String message) {
        LogManager.getLogger().error(message);
    }

    public static void debug(String message) {
        LogManager.getLogger().debug(message);
    }

    public static void main(String[] args) {
        LogUtils.info("Hello World!");
        LogUtils.getLogger(LogUtils.class).info("Hello from class logger");
    }
}
