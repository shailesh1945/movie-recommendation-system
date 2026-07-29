package com.movie.recsys.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy HH:mm:ss"
            );

    private DateUtil() {
    }

    public static String format(
            LocalDateTime dateTime) {

        return dateTime.format(formatter);

    }

}