package com.movie.recsys.mapper;

import com.movie.recsys.dto.movie.UserMovieResponse;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMovieRowMapper implements RowMapper<UserMovieResponse> {

    @Override
    public UserMovieResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return UserMovieResponse.builder()

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
                .language(
                        rs.getString("language_name"))
                .genre(
                        rs.getString("genre_name"))
                .score(

                        rs.getInt("score")

                )

                .build();
    }
}
