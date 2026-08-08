package com.movie.recsys.service;

import com.movie.recsys.dto.user.UserResponse;

import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();

    void deleteUserById(Integer id);
}
