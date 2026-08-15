package com.movie.recsys.service;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.movie.UserMovieResponse;
import com.movie.recsys.dto.user.ProfileResponse;
import com.movie.recsys.dto.user.ProfileUpdateRequest;

import java.util.List;

public interface UserService {

    ApiResponse<ProfileResponse> getProfile(
            Integer userId
    );

    ApiResponse<Void> updateProfile(
            Integer userId,
            ProfileUpdateRequest request
    );

    List<UserMovieResponse> getAllMovies();
}