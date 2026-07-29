package com.movie.recsys.mapper;


import com.movie.recsys.model.Rating;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


@Component
public class RatingRowMapper
        implements RowMapper<Rating> {

    @Override
    public Rating mapRow(ResultSet rs,
                         int rowNum)
            throws SQLException {

        Rating rating = new Rating();

        rating.setRatingId(
                rs.getInt("rating_id"));

        rating.setMovieId(
                rs.getInt("movie_id"));

        rating.setUserId(
                rs.getInt("user_id"));

        rating.setRating(
                rs.getInt("rating"));

        Timestamp timestamp =
                rs.getTimestamp("rated_at");

        if (timestamp != null) {

            rating.setRatedAt(
                    timestamp.toLocalDateTime());

        }

        return rating;

    }

}
