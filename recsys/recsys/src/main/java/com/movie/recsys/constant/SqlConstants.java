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
            FROM languages
            ORDER BY language_name
            """;

    public static final String FIND_PREFERENCE_ID =
            """
                    SELECT preference_id
                    FROM user_preferences
                    WHERE user_id=?
                    """;

    public static final String INSERT_USER_PREFERENCE =
            """
                    INSERT INTO user_preferences(
                    
                    user_id,
                    
                    min_rating,
                    
                    min_release_year
                    
                    )
                    
                    VALUES(?,?,?)
                    """;

    public static final String UPDATE_USER_PREFERENCE =
            """
                    UPDATE user_preferences
                    
                    SET
                    
                    min_rating=?,
                    
                    min_release_year=?
                    
                    WHERE preference_id=?
                    """;

    public static final String DELETE_USER_PREFERENCE_GENRES =
            """
                    DELETE FROM user_preference_genres
                    WHERE preference_id=?
                    """;

    public static final String DELETE_USER_PREFERENCE_LANGUAGES =
            """
                    DELETE FROM user_preference_languages
                    WHERE preference_id=?
                    """;

    public static final String INSERT_USER_PREFERENCE_GENRE =
            """
                    INSERT INTO user_preference_genres(
                    
                    preference_id,
                    
                    genre_id
                    
                    )
                    
                    VALUES(?,?)
                    """;

    public static final String INSERT_USER_PREFERENCE_LANGUAGE =
            """
                    INSERT INTO user_preference_languages(
                    
                    preference_id,
                    
                    language_id
                    
                    )
                    
                    VALUES(?,?)
                    """;

    public static final String GET_USER_PREFERENCE =
            """
                    SELECT *
                    
                    FROM user_preferences
                    
                    WHERE user_id=?
                    """;

    public static final String GET_SELECTED_GENRES =
            """
                    SELECT genre_id
                    
                    FROM user_preference_genres
                    
                    WHERE preference_id=?
                    """;

    public static final String GET_SELECTED_LANGUAGES =
            """
                    SELECT language_id
                    
                    FROM user_preference_languages
                    
                    WHERE preference_id=?
                    """;

    public static final String GET_RECOMMENDATIONS =
            """
                            SELECT

        m.movie_id,

        m.title,

        m.director,

        m.release_year,

        m.average_rating,

        m.poster_url,

        l.language_name,

        (
            SELECT GROUP_CONCAT(
                DISTINCT g.genre_name
                ORDER BY g.genre_name
                SEPARATOR ', '
            )
            FROM movie_genres mg2
            JOIN genres g
                ON g.genre_id = mg2.genre_id
            WHERE mg2.movie_id = m.movie_id
        ) AS genre_name,

        (
            CASE

                WHEN EXISTS(
                    SELECT 1
                    FROM movie_genres mg
                    JOIN user_preference_genres upg
                        ON mg.genre_id = upg.genre_id
                    JOIN user_preferences up
                        ON up.preference_id = upg.preference_id
                    WHERE mg.movie_id = m.movie_id
                    AND up.user_id = ?
                )
                THEN 40
                ELSE 0

            END

            +

            CASE

                WHEN EXISTS(
                    SELECT 1
                    FROM user_preference_languages upl
                    JOIN user_preferences up
                        ON up.preference_id = upl.preference_id
                    WHERE up.user_id = ?
                    AND upl.language_id = m.language_id
                )
                THEN 20
                ELSE 0

            END

            +

            CASE

                WHEN m.average_rating >= (
                    SELECT min_rating
                    FROM user_preferences
                    WHERE user_id = ?
                )
                THEN 20
                ELSE 0

            END

            +

            CASE

                WHEN m.release_year >= (
                    SELECT min_release_year
                    FROM user_preferences
                    WHERE user_id = ?
                )
                THEN 10
                ELSE 0

            END

            +

            ROUND(m.average_rating)

        ) AS score

    FROM movies m

    JOIN languages l
        ON l.language_id = m.language_id

    WHERE EXISTS (

        SELECT 1

        FROM user_preference_languages upl

        JOIN user_preferences up
            ON up.preference_id = upl.preference_id

        WHERE up.user_id = ?
        AND upl.language_id = m.language_id

    )

    ORDER BY score DESC,
             average_rating DESC

    LIMIT 20
    """;
}