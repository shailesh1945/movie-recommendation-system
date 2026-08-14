package com.movie.recsys.service;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.movie.UserMovieResponse;
import com.movie.recsys.dto.user.ProfileResponse;
import com.movie.recsys.dto.user.ProfileUpdateRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {
    ApiResponse<ProfileResponse> getProfile(
            HttpSession session
    );
    ApiResponse<Void> updateProfile(
            ProfileUpdateRequest request,
            HttpSession session
    );

    List<UserMovieResponse> getAllMovies();
}
