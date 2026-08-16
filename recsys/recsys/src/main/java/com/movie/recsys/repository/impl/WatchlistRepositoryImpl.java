package com.movie.recsys.repository.impl;

import com.movie.recsys.mapper.MovieRowMapper;
import com.movie.recsys.model.Movie;
import com.movie.recsys.repository.WatchlistRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WatchlistRepositoryImpl
        implements WatchlistRepository {

    private final JdbcTemplate jdbcTemplate;

    public WatchlistRepositoryImpl(
            JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addToWatchlist(
            Integer userId,
            Integer movieId) {

        String sql = """
                INSERT INTO watchlist (movie_id, user_id)
                VALUES (?, ?)
                """;

        jdbcTemplate.update(
                sql,
                movieId,
                userId
        );
    }

    @Override
    public void removeFromWatchlist(
            Integer userId,
            Integer movieId) {

        String sql = """
                DELETE FROM watchlist
                WHERE user_id = ?
                AND movie_id = ?
                """;

        jdbcTemplate.update(
                sql,
                userId,
                movieId
        );
    }

    @Override
    public boolean exists(
            Integer userId,
            Integer movieId) {

        String sql = """
                SELECT COUNT(*)
                FROM watchlist
                WHERE user_id = ?
                AND movie_id = ?
                """;

        Integer count =
                jdbcTemplate.queryForObject(
                        sql,
                        Integer.class,
                        userId,
                        movieId
                );

        return count != null && count > 0;
    }

    @Override
    public List<Movie> getUserWatchlist(
            Integer userId) {

        String sql = """
                SELECT m.*
                FROM movies m
                INNER JOIN watchlist w
                    ON m.movie_id = w.movie_id
                WHERE w.user_id = ?
                ORDER BY w.created_at DESC
                """;

        return jdbcTemplate.query(
                sql,
                new MovieRowMapper(),
                userId
        );
    }
}