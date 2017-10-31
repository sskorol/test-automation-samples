package com.travel.pay.utils;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {

    private DateTimeUtils() {
        throw new UnsupportedOperationException("Illegal access to private constructor");
    }

    public static LocalDate formatTourDate(final String dayMonthYear) {
        String pattern = "yyyy/M/d";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dayMonthYear, formatter);
    }

    public static YearMonth formatCreditCard(final String monthYear) {
        String pattern = "M/yy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return YearMonth.parse(monthYear, formatter);
    }
}
