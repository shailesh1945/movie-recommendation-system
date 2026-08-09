package com.movie.recsys.repository.impl;

import com.movie.recsys.dto.user.UserResponse;
import com.movie.recsys.repository.AdminRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

    private final JdbcTemplate jdbcTemplate;

    public AdminRepositoryImpl(
            JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<UserResponse> getAllUser() {
        // Updated created_at column name mapping
        String sql = "SELECT user_id, first_name, last_name, email, mobile, gender, created_at FROM users where role_id != 1";

        return jdbcTemplate.query(sql, (rs, rowNum) -> UserResponse.builder()
                .userId(rs.getInt("user_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .email(rs.getString("email"))
                .mobile(rs.getString("mobile"))
                .gender(rs.getString("gender"))
                .createdAt(rs.getTimestamp("created_at") != null
                        ? rs.getTimestamp("created_at").toLocalDateTime()
                        : null)
                .build()
        );
    }

    @Override
    public void deleteUser(Integer id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public Integer getMoviesCount() {
        Long count = jdbcTemplate.queryForObject(
                "Select count(*) from movies",
                Long.class
        );
        return count != null ? count.intValue() : 0;
    }

    @Override
    public Integer getUserCount() {
        Long count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users where user_id != 1",
                Long.class
        );

        return count != null ? count.intValue() : 0;
    }
}