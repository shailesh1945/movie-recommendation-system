package com.movie.recsys.util;


public class ValidationUtil {

    private ValidationUtil() {
    }

    public static boolean isNullOrEmpty(
            String value) {

        return value == null || value.trim().isEmpty();

    }

}