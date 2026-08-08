package com.movie.recsys.mapper;

import com.movie.recsys.model.UserPreference;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserPreferenceRowMapper
        implements RowMapper<UserPreference> {

    @Override
    public UserPreference mapRow(
            ResultSet rs,
            int rowNum)
            throws SQLException {

        return UserPreference.builder()

                .preferenceId(
                        rs.getInt("preference_id"))

                .userId(
                        rs.getInt("user_id"))

                .minRating(
                        rs.getDouble("min_rating"))

                .minReleaseYear(
                        rs.getInt("min_release_year"))

                .build();

    }

}