package com.movie.recsys.repository.impl;


import com.movie.recsys.constant.sql.MovieSql;
import com.movie.recsys.dto.movie.MovieSearchRequest;
import com.movie.recsys.mapper.MovieRowMapper;
import com.movie.recsys.model.Movie;
import com.movie.recsys.repository.MovieRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private final JdbcTemplate jdbcTemplate;
    private final MovieRowMapper movieRowMapper;

    public MovieRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            MovieRowMapper movieRowMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.movieRowMapper = movieRowMapper;
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

    @Override
    public int save(Movie movie) {

        return jdbcTemplate.update(
                MovieSql.INSERT_MOVIE,

                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseYear(),
                movie.getDuration(),
                movie.getDirector(),
                movie.getLanguageId(),
                movie.getPosterUrl(),
                movie.getTrailerUrl()
        );

    }

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

}