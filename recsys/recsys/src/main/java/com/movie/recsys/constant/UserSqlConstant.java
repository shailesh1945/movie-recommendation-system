package com.movie.recsys.constant;

public final class UserSqlConstant {

    private UserSqlConstant() {
    }

    public static final String UPDATE_PROFILE =
            "UPDATE users SET ";

    public static final String FIRST_NAME =
            "first_name = ?";

    public static final String LAST_NAME =
            "last_name = ?";

    public static final String EMAIL =
            "email = ?";

    public static final String MOBILE =
            "mobile = ?";

    public static final String GENDER =
            "gender = ?";

    public static final String PASSWORD =
            "password = ?";

    public static final String WHERE_USER_ID =
            " WHERE user_id = ?";


    public static final String GET_PROFILE = """
            SELECT user_id,
                   first_name,
                   last_name,
                   email,
                   mobile,
                   gender
            FROM users
            WHERE user_id = ?
            """;
}
