package com.movie.recsys.mapper;


import com.movie.recsys.model.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GenreRowMapper
        implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet rs,
                        int rowNum)
            throws SQLException {

        Genre genre = new Genre();

        genre.setGenreId(
                rs.getInt("genre_id"));

        genre.setGenreName(
                rs.getString("genre_name"));

        return genre;

    }

}
