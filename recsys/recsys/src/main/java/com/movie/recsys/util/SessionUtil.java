package com.movie.recsys.util;


import com.movie.recsys.constant.AppConstants;
import com.movie.recsys.dto.LoginResponse;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {

    private SessionUtil() {
    }

    public static void createSession(
            HttpSession session,
            LoginResponse response) {

        session.setAttribute(
                AppConstants.SESSION_USER_ID,
                response.getUserId());

        session.setAttribute(
                AppConstants.SESSION_ROLE,
                response.getRole());

        session.setAttribute(
                AppConstants.SESSION_USER_NAME,
                response.getFirstName());

    }

    public static void invalidateSession(
            HttpSession session) {

        if (session != null) {
            session.invalidate();
        }

    }

    public static Integer getUserId(
            HttpSession session) {

        return (Integer) session.getAttribute(
                AppConstants.SESSION_USER_ID
        );

    }

    public static String getRole(
            HttpSession session) {

        return (String) session.getAttribute(
                AppConstants.SESSION_ROLE
        );

    }

}