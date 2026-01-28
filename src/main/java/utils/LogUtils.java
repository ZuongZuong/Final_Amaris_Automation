package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class LogUtils {

    private LogUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    public static void info(String message) {
        LogManager.getLogger(LogUtils.class).info(message);
    }

    public static void warn(String message) {
        LogManager.getLogger(LogUtils.class).warn(message);
    }

    public static void error(String message) {
        LogManager.getLogger(LogUtils.class).error(message);
    }

    public static void debug(String message) {
        LogManager.getLogger(LogUtils.class).debug(message);
    }
}
