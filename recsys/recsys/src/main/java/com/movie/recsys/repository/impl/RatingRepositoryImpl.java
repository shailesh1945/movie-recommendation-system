package com.movie.recsys.repository.impl;

import com.movie.recsys.model.Rating;
import com.movie.recsys.repository.RatingRepository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

    private final JdbcTemplate jdbcTemplate;

    public RatingRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Rating findByMovieAndUser(
            Integer movieId,
            Integer userId) {

        String sql = """
                SELECT rating_id,
                       movie_id,
                       user_id,
                       rating,
                       rated_at
                FROM ratings
                WHERE movie_id = ?
                  AND user_id = ?
                """;

        return jdbcTemplate.query(
                sql,
                rs -> {

                    if (rs.next()) {

                        Timestamp timestamp =
                                rs.getTimestamp("rated_at");

                        return Rating.builder()
                                .ratingId(
                                        rs.getInt("rating_id")
                                )
                                .movieId(
                                        rs.getInt("movie_id")
                                )
                                .userId(
                                        rs.getInt("user_id")
                                )
                                .rating(
                                        rs.getInt("rating")
                                )
                                .ratedAt(
                                        timestamp != null
                                                ? timestamp.toLocalDateTime()
                                                : null
                                )
                                .build();
                    }

                    return null;
                },
                movieId,
                userId
        );
    }


    @Override
    public void saveOrUpdate(Rating rating) {

        String checkSql = """
                SELECT COUNT(*)
                FROM ratings
                WHERE movie_id = ?
                  AND user_id = ?
                """;

        Integer count = jdbcTemplate.queryForObject(
                checkSql,
                Integer.class,
                rating.getMovieId(),
                rating.getUserId()
        );

        if (count != null && count > 0) {

            String updateSql = """
                    UPDATE ratings
                    SET rating = ?,
                        rated_at = CURRENT_TIMESTAMP
                    WHERE movie_id = ?
                      AND user_id = ?
                    """;

            jdbcTemplate.update(
                    updateSql,
                    rating.getRating(),
                    rating.getMovieId(),
                    rating.getUserId()
            );

        } else {

            String insertSql = """
                    INSERT INTO ratings
                    (
                        movie_id,
                        user_id,
                        rating
                    )
                    VALUES (?, ?, ?)
                    """;

            jdbcTemplate.update(
                    insertSql,
                    rating.getMovieId(),
                    rating.getUserId(),
                    rating.getRating()
            );
        }
    }


    @Override
    public Double getAverageRating(Integer movieId) {

        String sql = """
                SELECT AVG(rating)
                FROM ratings
                WHERE movie_id = ?
                """;

        Double average =
                jdbcTemplate.queryForObject(
                        sql,
                        Double.class,
                        movieId
                );

        return average != null ? average : 0.0;
    }


    @Override
    public Integer getRatingCount(Integer movieId) {

        String sql = """
                SELECT COUNT(*)
                FROM ratings
                WHERE movie_id = ?
                """;

        Integer count =
                jdbcTemplate.queryForObject(
                        sql,
                        Integer.class,
                        movieId
                );

        return count != null ? count : 0;
    }
}