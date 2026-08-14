package com.movie.recsys.repository.impl;


import com.movie.recsys.constant.SqlConstants;
import com.movie.recsys.constant.sql.MovieSql;
import com.movie.recsys.dto.genre.GenreResponse;
import com.movie.recsys.dto.movie.MovieSearchRequest;
import com.movie.recsys.mapper.GenreRowMapper;
import com.movie.recsys.mapper.MovieRowMapper;
import com.movie.recsys.model.Movie;
import com.movie.recsys.repository.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final JdbcTemplate jdbcTemplate;
    private final MovieRowMapper movieRowMapper;
    private final GenreRowMapper genreRowMapper;

    public MovieRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            MovieRowMapper movieRowMapper,
            GenreRowMapper genreRowMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.movieRowMapper = movieRowMapper;
        this.genreRowMapper = genreRowMapper;
    }


    @Override
    public List<Movie> findAll() {

        return jdbcTemplate.query(
                MovieSql.FIND_ALL,
                movieRowMapper
        );

    }

    @Override
    public Movie findById(Integer movieId) {

        try {

            return jdbcTemplate.queryForObject(
                    MovieSql.FIND_BY_ID,
                    movieRowMapper,
                    movieId
            );

        } catch (Exception e) {

            return null;

        }

    }

    // =========================================================
    // SAVE MOVIE
    // =========================================================

    @Override
    public Integer save(Movie movie) {

        KeyHolder keyHolder =
                new GeneratedKeyHolder();


        jdbcTemplate.update(connection -> {

            PreparedStatement ps =
                    connection.prepareStatement(
                            MovieSql.INSERT_MOVIE,
                            Statement.RETURN_GENERATED_KEYS
                    );


            ps.setString(
                    1,
                    movie.getTitle()
            );

            ps.setString(
                    2,
                    movie.getDescription()
            );

            ps.setInt(
                    3,
                    movie.getReleaseYear()
            );

            ps.setInt(
                    4,
                    movie.getDuration()
            );

            ps.setString(
                    5,
                    movie.getDirector()
            );

            ps.setInt(
                    6,
                    movie.getLanguageId()
            );

            ps.setString(
                    7,
                    movie.getPosterUrl()
            );

            ps.setString(
                    8,
                    movie.getTrailerUrl()
            );


            return ps;

        }, keyHolder);


        Number generatedId =
                keyHolder.getKey();


        if (generatedId == null) {

            throw new IllegalStateException(
                    "Failed to generate movie ID."
            );

        }


        return generatedId.intValue();

    }


    // =========================================================
    // UPDATE MOVIE
    // =========================================================

    @Override
    public int update(Movie movie) {

        return jdbcTemplate.update(

                MovieSql.UPDATE_MOVIE,

                movie.getTitle(),

                movie.getDescription(),

                movie.getReleaseYear(),

                movie.getDuration(),

                movie.getDirector(),

                movie.getLanguageId(),

                movie.getPosterUrl(),

                movie.getTrailerUrl(),

                movie.getMovieId()

        );

    }


    @Override
    public int delete(Integer movieId) {

        return jdbcTemplate.update(
                MovieSql.DELETE_MOVIE,
                movieId
        );

    }

    @Override
    public List<Movie> latestMovies() {

        return jdbcTemplate.query(
                MovieSql.LATEST_MOVIES,
                movieRowMapper
        );

    }

    @Override
    public List<Movie> topRatedMovies() {

        return jdbcTemplate.query(
                MovieSql.TOP_RATED,
                movieRowMapper
        );

    }

    @Override
    public List<Movie> search(MovieSearchRequest request) {

        StringBuilder sql = new StringBuilder("""
                SELECT
                    m.*,
                    l.language_name,
                    ROUND(IFNULL(AVG(r.rating),0),1) average_rating
                FROM movies m
                LEFT JOIN languages l
                    ON l.language_id = m.language_id
                LEFT JOIN ratings r
                    ON r.movie_id = m.movie_id
                WHERE 1 = 1
                """);

        List<Object> params = new ArrayList<>();

        if (request.getTitle() != null &&
                !request.getTitle().isBlank()) {

            sql.append(" AND m.title LIKE ? ");

            params.add("%" + request.getTitle() + "%");

        }

        if (request.getLanguageId() != null) {

            sql.append(" AND m.language_id = ? ");

            params.add(request.getLanguageId());

        }

        if (request.getReleaseYear() != null) {

            sql.append(" AND m.release_year = ? ");

            params.add(request.getReleaseYear());

        }

        sql.append("""
                GROUP BY m.movie_id
                ORDER BY m.title
                """);

        return jdbcTemplate.query(
                sql.toString(),
                movieRowMapper,
                params.toArray()
        );

    }

    @Override
    public void saveMovieGenre(
            Integer movieId,
            Integer genreId) {

        jdbcTemplate.update(

                MovieSql.INSERT_MOVIE_GENRE,

                movieId,
                genreId

        );

    }

    @Override
    public void deleteMovieGenres(
            Integer movieId) {

        jdbcTemplate.update(

                MovieSql.DELETE_MOVIE_GENRES,

                movieId

        );

    }

    @Override
    public List<GenreResponse> findGenresByMovieId(
            Integer movieId) {

        return jdbcTemplate.query(
                MovieSql.FIND_GENRES_BY_MOVIE_ID,
                genreRowMapper,
                movieId
        );
    }


}