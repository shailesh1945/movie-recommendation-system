package com.movie.recsys.constant.sql;


public final class MovieSql {

    private MovieSql() {
    }

    // ==========================
    // CRUD
    // ==========================

    public static final String INSERT_MOVIE = """
        INSERT INTO movies
        (
            title,
            description,
            release_year,
            duration,
            director,
            language_id,
            poster_url,
            trailer_url
        )
        VALUES
        (?, ?, ?, ?, ?, ?, ?, ?)
        """;

    public static final String UPDATE_MOVIE = """
        UPDATE movies
        SET
            title = ?,
            description = ?,
            release_year = ?,
            duration = ?,
            director = ?,
            language_id = ?,
            poster_url = ?,
            trailer_url = ?
        WHERE movie_id = ?
        """;

    public static final String DELETE_MOVIE = """
        DELETE FROM movies
        WHERE movie_id = ?
        """;

    public static final String FIND_BY_ID = """
        SELECT
            m.*,
            l.language_name,
            ROUND(IFNULL(AVG(r.rating),0),1) average_rating
        FROM movies m
        LEFT JOIN languages l
            ON l.language_id = m.language_id
        LEFT JOIN ratings r
            ON r.movie_id = m.movie_id
        WHERE m.movie_id = ?
        GROUP BY m.movie_id
        """;

    public static final String FIND_ALL = """
        SELECT
            m.*,
            l.language_name,
            ROUND(IFNULL(AVG(r.rating),0),1) average_rating
        FROM movies m
        LEFT JOIN languages l
            ON l.language_id = m.language_id
        LEFT JOIN ratings r
            ON r.movie_id = m.movie_id
        GROUP BY m.movie_id
        ORDER BY m.title
        """;

    public static final String LATEST_MOVIES = """
        SELECT
            m.*,
            l.language_name,
            ROUND(IFNULL(AVG(r.rating),0),1) average_rating
        FROM movies m
        LEFT JOIN languages l
            ON l.language_id = m.language_id
        LEFT JOIN ratings r
            ON r.movie_id = m.movie_id
        GROUP BY m.movie_id
        ORDER BY m.release_year DESC
        LIMIT 10
        """;

    public static final String TOP_RATED = """
        SELECT
            m.*,
            l.language_name,
            ROUND(IFNULL(AVG(r.rating),0),1) average_rating
        FROM movies m
        LEFT JOIN languages l
            ON l.language_id = m.language_id
        LEFT JOIN ratings r
            ON r.movie_id = m.movie_id
        GROUP BY m.movie_id
        ORDER BY average_rating DESC
        LIMIT 10
        """;

}
