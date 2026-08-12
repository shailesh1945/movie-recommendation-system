package com.movie.recsys.repository;

import com.movie.recsys.dto.user.ProfileResponse;
import com.movie.recsys.dto.user.ProfileUpdateRequest;

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
}
