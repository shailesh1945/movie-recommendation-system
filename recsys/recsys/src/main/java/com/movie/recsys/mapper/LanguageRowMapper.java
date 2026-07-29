package com.movie.recsys.mapper;


import com.movie.recsys.model.Language;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LanguageRowMapper
        implements RowMapper<Language> {

    @Override
    public Language mapRow(ResultSet rs,
                           int rowNum)
            throws SQLException {

        Language language = new Language();

        language.setLanguageId(
                rs.getInt("language_id"));

        language.setLanguageName(
                rs.getString("language_name"));

        return language;

    }

}
