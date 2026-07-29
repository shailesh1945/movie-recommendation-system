package com.movie.recsys.mapper;


import com.movie.recsys.model.Movie;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


@Component
public class MovieRowMapper
        implements RowMapper<Movie> {

    @Override
    public Movie mapRow(ResultSet rs,
                        int rowNum)
            throws SQLException {

        Movie movie = new Movie();

        movie.setMovieId(
                rs.getInt("movie_id"));

        movie.setTitle(
                rs.getString("title"));

        movie.setDescription(
                rs.getString("description"));

        movie.setReleaseYear(
                rs.getInt("release_year"));

        movie.setDuration(
                rs.getInt("duration"));

        movie.setDirector(
                rs.getString("director"));

        movie.setLanguageId(
                rs.getInt("language_id"));

        try {
            movie.setLanguageName(
                    rs.getString("language_name"));
        } catch (SQLException ignored) {
        }

        movie.setPosterUrl(
                rs.getString("poster_url"));

        movie.setTrailerUrl(
                rs.getString("trailer_url"));

        movie.setAverageRating(
                rs.getBigDecimal("average_rating"));

        Timestamp timestamp =
                rs.getTimestamp("created_at");

        if (timestamp != null) {

            movie.setCreatedAt(
                    timestamp.toLocalDateTime());

        }

        return movie;

    }

}
