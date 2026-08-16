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
        m.movie_id,
        m.title,
        m.description,
        m.release_year,
        m.duration,
        m.director,
        m.language_id,
        m.poster_url,
        m.trailer_url,
        m.created_at,
        l.language_name,
        ROUND(IFNULL(AVG(r.rating), 0), 1) AS average_rating
    FROM movies m
    LEFT JOIN languages l
        ON l.language_id = m.language_id
    LEFT JOIN ratings r
        ON r.movie_id = m.movie_id
    WHERE m.movie_id = ?
    GROUP BY
        m.movie_id,
        m.title,
        m.description,
        m.release_year,
        m.duration,
        m.director,
        m.language_id,
        m.poster_url,
        m.trailer_url,
        m.created_at,
        l.language_name
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
        m.movie_id,
        m.title,
        m.description,
        m.release_year,
        m.duration,
        m.director,
        m.language_id,
        m.poster_url,
        m.trailer_url,
        m.created_at,
        l.language_name,
        ROUND(IFNULL(AVG(r.rating), 0), 1) AS average_rating

    FROM movies m

    LEFT JOIN languages l
        ON l.language_id = m.language_id

    LEFT JOIN ratings r
        ON r.movie_id = m.movie_id

    GROUP BY
        m.movie_id,
        m.title,
        m.description,
        m.release_year,
        m.duration,
        m.director,
        m.language_id,
        m.poster_url,
        m.trailer_url,
        m.created_at,
        l.language_name

    ORDER BY m.release_year DESC

    LIMIT 12
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

    public static final String INSERT_MOVIE_GENRE = """
        INSERT INTO movie_genres (
            movie_id,
            genre_id
        )
        VALUES (?, ?)
        """;

    public static final String DELETE_MOVIE_GENRES = """
        DELETE FROM movie_genres
        WHERE movie_id = ?
        """;

    public static final String FIND_GENRES_BY_MOVIE_ID = """
        SELECT
            g.genre_id,
            g.genre_name
        FROM genres g
        INNER JOIN movie_genres mg
            ON mg.genre_id = g.genre_id
        WHERE mg.movie_id = ?
        ORDER BY g.genre_name
        """;



}
