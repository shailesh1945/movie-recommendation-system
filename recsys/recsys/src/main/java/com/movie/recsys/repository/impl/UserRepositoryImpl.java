package com.movie.recsys.repository.impl;


import com.movie.recsys.constant.SqlConstants;
import com.movie.recsys.constant.UserSqlConstant;
import com.movie.recsys.dto.movie.UserMovieResponse;
import com.movie.recsys.dto.user.ProfileResponse;
import com.movie.recsys.dto.user.ProfileUpdateRequest;
import com.movie.recsys.mapper.UserMovieRowMapper;
import com.movie.recsys.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserMovieRowMapper rowMapper;

    public UserRepositoryImpl(
            JdbcTemplate jdbcTemplate, UserMovieRowMapper rowMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public boolean existsByEmail(
            String email,
            Integer userId) {

        String sql = """
                SELECT COUNT(*)
                FROM users
                WHERE email = ?
                AND user_id <> ?
                """;

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                email,
                userId
        );

        return count != null && count > 0;
    }
    // logic for all movies


    @Override
    public List<UserMovieResponse> getAllMovies() {
        return jdbcTemplate.query(
                SqlConstants.GET_ALL_MOVIES,
                rowMapper);
    }

    @Override
    public ProfileResponse getProfile(Integer userId) {
        return jdbcTemplate.queryForObject(
                UserSqlConstant.GET_PROFILE,

                (rs, rowNum) -> ProfileResponse.builder()
                        .userId(
                                rs.getInt("user_id")
                        )
                        .firstName(
                                rs.getString("first_name")
                        )
                        .lastName(
                                rs.getString("last_name")
                        )
                        .email(
                                rs.getString("email")
                        )
                        .phoneNumber(
                                rs.getString("mobile")
                        )
                        .gender(
                                rs.getString("gender")
                        )
                        .build(),

                userId
        );
    }

    @Override
    public int updateProfile(
            Integer userId,
            ProfileUpdateRequest request) {

        StringBuilder sql =
                new StringBuilder(
                        UserSqlConstant.UPDATE_PROFILE
                );

        List<Object> params =
                new ArrayList<>();

        boolean firstField = true;

        /*
         * First Name
         */
        if (request.getFirstName() != null) {

            if (!firstField) {
                sql.append(", ");
            }

            sql.append(
                    UserSqlConstant.FIRST_NAME
            );

            params.add(request.getFirstName());

            firstField = false;
        }

        /*
         * Last Name
         */
        if (request.getLastName() != null) {

            if (!firstField) {
                sql.append(", ");
            }

            sql.append(
                    UserSqlConstant.LAST_NAME
            );

            params.add(request.getLastName());

            firstField = false;
        }

        /*
         * Email
         */
        if (request.getEmail() != null) {

            if (!firstField) {
                sql.append(", ");
            }

            sql.append(
                    UserSqlConstant.EMAIL
            );

            params.add(request.getEmail());

            firstField = false;
        }

        /*
         * Mobile
         */
        if (request.getMobile() != null) {

            if (!firstField) {
                sql.append(", ");
            }

            sql.append(
                    UserSqlConstant.MOBILE
            );

            params.add(request.getMobile());

            firstField = false;
        }

        /*
         * Gender
         */
        if (request.getGender() != null) {

            if (!firstField) {
                sql.append(", ");
            }

            sql.append(
                    UserSqlConstant.GENDER
            );

            params.add(request.getGender());

            firstField = false;
        }

        /*
         * Password
         */
        if (request.getPassword() != null &&
                !request.getPassword().isBlank()) {

            if (!firstField) {
                sql.append(", ");
            }

            sql.append(
                    UserSqlConstant.PASSWORD
            );

            params.add(request.getPassword());

            firstField = false;
        }

        /*
         * No fields provided
         */
        if (firstField) {
            return 0;
        }

        sql.append(
                UserSqlConstant.WHERE_USER_ID
        );

        params.add(userId);

        return jdbcTemplate.update(
                sql.toString(),
                params.toArray()
        );
    }
}