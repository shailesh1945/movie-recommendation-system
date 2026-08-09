package com.movie.recsys.repository;

import com.movie.recsys.dto.user.UserResponse;

import java.util.List;

public interface AdminRepository {
    List<UserResponse> getAllUser();

    void deleteUser(Integer id);

    Integer getMoviesCount();

    Integer getUserCount();
}