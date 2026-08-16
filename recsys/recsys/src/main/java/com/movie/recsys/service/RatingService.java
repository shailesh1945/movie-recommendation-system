package com.movie.recsys.service;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.model.Rating;

public interface RatingService {

    ApiResponse<Rating> getMyRating(
            Integer movieId,
            Integer userId
    );

    ApiResponse<Rating> saveRating(
            Integer movieId,
            Integer userId,
            Integer rating
    );

    ApiResponse<Double> getAverageRating(
            Integer movieId
    );

    ApiResponse<Integer> getRatingCount(
            Integer movieId
    );
}