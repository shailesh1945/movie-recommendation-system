package com.movie.recsys.constant;

public final class AppConstants {

    private AppConstants() {

    }

    // Roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    // User Status
    public static final String ACTIVE = "ACTIVE";
    public static final String BLOCKED = "BLOCKED";

    // Session
    public static final String SESSION_USER_ID = "userId";
    public static final String SESSION_ROLE = "role";
    public static final String SESSION_USER_NAME = "userName";

    // Messages
    public static final String LOGIN_SUCCESS =
            "Login Successful";

    public static final String LOGOUT_SUCCESS =
            "Logout Successful";

    public static final String REGISTER_SUCCESS =
            "Registration Successful";

}