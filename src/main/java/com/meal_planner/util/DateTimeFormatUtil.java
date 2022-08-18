package com.meal_planner.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatUtil {

    private static DateTimeFormatter dateTimeFormatter;

    public static String timestampToString(Timestamp timestamp, String pattern) {
        return new SimpleDateFormat(pattern).format(timestamp);
    }

    public static String timestampToIsoFormat(Timestamp timestamp) {
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        return timestampToString(timestamp, pattern);
    }
}
