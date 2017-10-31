package io.github.sskorol.utils;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {

    private DateTimeUtils() {
        throw new UnsupportedOperationException("Illegal access to private constructor");
    }

    public static String format(final LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss"));
    }

    public static YearMonth format(final String date) {
        return YearMonth.parse(date, DateTimeFormatter.ofPattern("MM/yy"));
    }
}
