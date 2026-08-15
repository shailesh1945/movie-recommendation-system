package com.movie.recsys.security;

import org.springframework.security.core.Authentication;

public final class SecurityUtil {

    private SecurityUtil() {
    }


    public static Integer getUserId(
            Authentication authentication
    ) {

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            throw new RuntimeException(
                    "User is not authenticated"
            );
        }


        Object principal =
                authentication.getPrincipal();


        if (!(principal instanceof Integer)) {

            throw new RuntimeException(
                    "Invalid authenticated user"
            );
        }


        return (Integer) principal;
    }
}