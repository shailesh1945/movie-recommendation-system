package com.movie.recsys.constant;


public final class SqlConstants {

    private SqlConstants() {
    }

    // ==========================
    // Authentication
    // ==========================

    public static final String INSERT_USER = """
        INSERT INTO users
        (
            role_id,
            first_name,
            last_name,
            email,
            password,
            mobile,
            gender,
            status
        )
        VALUES
        (?, ?, ?, ?, ?, ?, ?, ?)
        """;

    public static final String FIND_USER_BY_EMAIL = """
        SELECT *
        FROM users
        WHERE email = ?
        """;

    public static final String EXISTS_EMAIL = """
        SELECT COUNT(*)
        FROM users
        WHERE email = ?
        """;

    public static final String FIND_USER_WITH_ROLE = """
        SELECT
            u.*,
            r.role_name
        FROM users u
        INNER JOIN roles r
            ON u.role_id = r.role_id
        WHERE u.email = ?
        """;


    public static final String GET_ALL_LANGUAGES = """
        SELECT
            language_id,
            language_name
        FROM language
        ORDER BY language_name
        """;
}