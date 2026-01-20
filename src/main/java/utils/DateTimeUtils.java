package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private DateTimeUtils() {
        // prevent object creation
    }

    public static String getCurrentDate() {
        return LocalDate.now().format(DATE_FORMAT);
    }

    public static String getFutureDate(int days) {
        return LocalDate.now()
                .plusDays(days)
                .format(DATE_FORMAT);
    }

    public static void main(String[] args) {
        System.out.println(DateTimeUtils.getCurrentDate());
    }
}
