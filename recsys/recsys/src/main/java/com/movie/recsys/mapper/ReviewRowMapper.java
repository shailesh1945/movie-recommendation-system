package com.movie.recsys.mapper;


import com.movie.recsys.model.Review;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class ReviewRowMapper
        implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet rs,
                         int rowNum)
            throws SQLException {

        Review review = new Review();

        review.setReviewId(
                rs.getInt("review_id"));

        review.setMovieId(
                rs.getInt("movie_id"));

        review.setUserId(
                rs.getInt("user_id"));

        review.setReviewText(
                rs.getString("review_text"));

        Timestamp timestamp =
                rs.getTimestamp("review_date");

        if (timestamp != null) {

            review.setReviewDate(
                    timestamp.toLocalDateTime());

        }

        return review;

    }

}
