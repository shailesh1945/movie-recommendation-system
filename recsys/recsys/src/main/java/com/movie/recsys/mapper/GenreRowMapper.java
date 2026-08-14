package com.movie.recsys.mapper;


import com.movie.recsys.dto.genre.GenreResponse;
import com.movie.recsys.model.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GenreRowMapper
        implements RowMapper<GenreResponse> {

    @Override
    public GenreResponse mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        return GenreResponse.builder()
                .genreId(rs.getInt("genre_id"))
                .genreName(rs.getString("genre_name"))
                .build();
    }

}
