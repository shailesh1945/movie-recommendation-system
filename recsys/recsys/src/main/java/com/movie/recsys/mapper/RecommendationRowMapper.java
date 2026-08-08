package com.movie.recsys.mapper;

import com.movie.recsys.dto.recommendation.RecommendationResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecommendationRowMapper
        implements RowMapper<RecommendationResponse> {

    @Override
    public RecommendationResponse mapRow(
            ResultSet rs,
            int rowNum)
            throws SQLException {

        return RecommendationResponse.builder()

                .movieId(
                        rs.getInt("movie_id"))

                .title(
                        rs.getString("title"))

                .releaseYear(
                        rs.getInt("release_year"))

                .averageRating(
                        rs.getBigDecimal("average_rating"))

                .posterUrl(
                        rs.getString("poster_url"))

                .director(
                        rs.getString("director"))
                .score(

                        rs.getInt("score")

                )

                .build();

    }

}