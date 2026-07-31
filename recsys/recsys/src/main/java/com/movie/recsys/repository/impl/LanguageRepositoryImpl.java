package com.movie.recsys.repository.impl;

import com.movie.recsys.constant.SqlConstants;
import com.movie.recsys.dto.language.LanguageResponse;
import com.movie.recsys.mapper.LanguageRowMapper;
import com.movie.recsys.model.Language;
import com.movie.recsys.repository.LanguageRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageRepositoryImpl implements LanguageRepository {

    private final JdbcTemplate jdbcTemplate;
    private final LanguageRowMapper languageRowMapper;

    public LanguageRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            LanguageRowMapper languageRowMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.languageRowMapper = languageRowMapper;
    }

    @Override
    public List<Language> getAllLanguages() {

        return jdbcTemplate.query(

                SqlConstants.GET_ALL_LANGUAGES,

                languageRowMapper

        );

    }

}