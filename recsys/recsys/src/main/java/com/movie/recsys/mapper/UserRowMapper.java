package com.movie.recsys.mapper;


import com.movie.recsys.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();

        user.setUserId(rs.getInt("user_id"));
        user.setRoleId(rs.getInt("role_id"));

        /*
            If query contains role_name (JOIN)
            otherwise ignore.
        */
        try {
            user.setRoleName(rs.getString("role_name"));
        } catch (SQLException ignored) {
        }

        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setMobile(rs.getString("mobile"));
        user.setGender(rs.getString("gender"));

        try {
            user.setDob(rs.getDate("dob").toLocalDate());
        } catch (Exception ignored) {
        }

        user.setProfileImage(rs.getString("profile_image"));
        user.setStatus(rs.getString("status"));

        Timestamp timestamp = rs.getTimestamp("created_at");

        if (timestamp != null) {
            user.setCreatedAt(timestamp.toLocalDateTime());
        }

        return user;
    }
}