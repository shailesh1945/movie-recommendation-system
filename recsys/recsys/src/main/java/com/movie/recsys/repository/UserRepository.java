package com.movie.recsys.repository;

import com.movie.recsys.dto.movie.UserMovieResponse;
import com.movie.recsys.dto.user.ProfileResponse;
import com.movie.recsys.dto.user.ProfileUpdateRequest;

import java.util.List;

public interface UserRepository {
    ProfileResponse getProfile(
            Integer userId
    );
    int updateProfile(
            Integer userId,
            ProfileUpdateRequest request
    );

    boolean existsByEmail(
            String email,
            Integer userId
    );

    List<UserMovieResponse> getAllMovies();
}
