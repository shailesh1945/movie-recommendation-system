package com.movie.recsys.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    private PasswordUtil() {
    }

    public static String hashPassword(String password) {

        return encoder.encode(password);

    }

    public static boolean verifyPassword(
            String rawPassword,
            String encodedPassword) {

        return encoder.matches(
                rawPassword,
                encodedPassword
        );

    }

}