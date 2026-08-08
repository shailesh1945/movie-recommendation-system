package com.movie.recsys.repository.impl;

import com.movie.recsys.constant.SqlConstants;
import com.movie.recsys.dto.preference.PreferenceResponse;
import com.movie.recsys.mapper.UserPreferenceRowMapper;
import com.movie.recsys.model.UserPreference;
import com.movie.recsys.repository.PreferenceRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class PreferenceRepositoryImpl
        implements PreferenceRepository {

    private final JdbcTemplate jdbcTemplate;

    private final UserPreferenceRowMapper rowMapper;

    public PreferenceRepositoryImpl(

            JdbcTemplate jdbcTemplate,

            UserPreferenceRowMapper rowMapper) {

        this.jdbcTemplate = jdbcTemplate;

        this.rowMapper = rowMapper;

    }

    @Override
    public Integer findPreferenceId(
            Integer userId) {

        try {

            return jdbcTemplate.queryForObject(

                    SqlConstants.FIND_PREFERENCE_ID,

                    Integer.class,

                    userId

            );

        }

        catch (Exception e) {

            return null;

        }

    }

    @Override
    public int savePreference(
            UserPreference preference) {

        return jdbcTemplate.update(

                SqlConstants.INSERT_USER_PREFERENCE,

                preference.getUserId(),

                preference.getMinRating(),

                preference.getMinReleaseYear()

        );

    }

    @Override
    public int updatePreference(
            UserPreference preference) {

        return jdbcTemplate.update(

                SqlConstants.UPDATE_USER_PREFERENCE,

                preference.getMinRating(),

                preference.getMinReleaseYear(),

                preference.getPreferenceId()

        );

    }

    @Override
    public void deleteGenres(
            Integer preferenceId) {

        jdbcTemplate.update(

                SqlConstants.DELETE_USER_PREFERENCE_GENRES,

                preferenceId

        );

    }

    @Override
    public void deleteLanguages(
            Integer preferenceId) {

        jdbcTemplate.update(

                SqlConstants.DELETE_USER_PREFERENCE_LANGUAGES,

                preferenceId

        );

    }

    @Override
    public void saveGenre(

            Integer preferenceId,

            Integer genreId) {

        jdbcTemplate.update(

                SqlConstants.INSERT_USER_PREFERENCE_GENRE,

                preferenceId,

                genreId

        );

    }

    @Override
    public void saveLanguage(

            Integer preferenceId,

            Integer languageId) {

        jdbcTemplate.update(

                SqlConstants.INSERT_USER_PREFERENCE_LANGUAGE,

                preferenceId,

                languageId

        );

    }

    @Override
    public List<Integer> getGenreIds(
            Integer preferenceId) {

        return jdbcTemplate.queryForList(

                SqlConstants.GET_SELECTED_GENRES,

                Integer.class,

                preferenceId

        );

    }

    @Override
    public List<Integer> getLanguageIds(
            Integer preferenceId) {

        return jdbcTemplate.queryForList(

                SqlConstants.GET_SELECTED_LANGUAGES,

                Integer.class,

                preferenceId

        );

    }

    @Override
    public PreferenceResponse getPreference(
            Integer userId) {

        UserPreference preference =
                jdbcTemplate.queryForObject(

                        SqlConstants.GET_USER_PREFERENCE,

                        rowMapper,

                        userId

                );

        List<Integer> genres =
                getGenreIds(
                        preference.getPreferenceId());

        List<Integer> languages =
                getLanguageIds(
                        preference.getPreferenceId());

        return PreferenceResponse.builder()

                .genreIds(genres)

                .languageIds(languages)

                .minRating(
                        preference.getMinRating())

                .minReleaseYear(
                        preference.getMinReleaseYear())

                .build();

    }

    @Override
    public Integer savePreferenceAndReturnId(
            UserPreference preference) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(

                    SqlConstants.INSERT_USER_PREFERENCE,

                    Statement.RETURN_GENERATED_KEYS

            );

            ps.setInt(1, preference.getUserId());

            ps.setDouble(2, preference.getMinRating());

            ps.setInt(3, preference.getMinReleaseYear());

            return ps;

        }, keyHolder);

        return keyHolder.getKey().intValue();

    }

}