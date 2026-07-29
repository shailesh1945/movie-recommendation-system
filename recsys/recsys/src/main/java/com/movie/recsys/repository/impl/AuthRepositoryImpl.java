package com.movie.recsys.repository.impl;


import com.movie.recsys.constant.SqlConstants;
import com.movie.recsys.mapper.UserRowMapper;
import com.movie.recsys.model.User;
import com.movie.recsys.repository.AuthRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepositoryImpl implements AuthRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public AuthRepositoryImpl(
            JdbcTemplate jdbcTemplate,
            UserRowMapper userRowMapper) {

        this.jdbcTemplate = jdbcTemplate;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public int saveUser(User user) {

        return jdbcTemplate.update(

                SqlConstants.INSERT_USER,

                user.getRoleId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getMobile(),
                user.getGender(),
                user.getStatus()

        );

    }

    @Override
    public User findByEmail(String email) {

        try {

            return jdbcTemplate.queryForObject(

                    SqlConstants.FIND_USER_BY_EMAIL,

                    userRowMapper,

                    email

            );

        } catch (Exception e) {

            return null;

        }

    }

    @Override
    public User findUserWithRole(String email) {

        try {

            return jdbcTemplate.queryForObject(

                    SqlConstants.FIND_USER_WITH_ROLE,

                    userRowMapper,

                    email

            );

        } catch (Exception e) {

            return null;

        }

    }

    @Override
    public boolean existsByEmail(String email) {

        Integer count = jdbcTemplate.queryForObject(

                SqlConstants.EXISTS_EMAIL,

                Integer.class,

                email

        );

        return count != null && count > 0;

    }

}