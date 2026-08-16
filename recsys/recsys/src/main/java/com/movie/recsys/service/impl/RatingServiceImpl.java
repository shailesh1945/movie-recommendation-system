package com.movie.recsys.service.impl;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.model.Rating;
import com.movie.recsys.repository.RatingRepository;
import com.movie.recsys.service.RatingService;

import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(
            RatingRepository ratingRepository) {

        this.ratingRepository =
                ratingRepository;
    }


    @Override
    public ApiResponse<Rating> getMyRating(
            Integer movieId,
            Integer userId) {

        Rating rating =
                ratingRepository.findByMovieAndUser(
                        movieId,
                        userId
                );

        return ApiResponse.<Rating>builder()
                .success(true)
                .message(
                        rating != null
                                ? "Rating found."
                                : "No rating submitted yet."
                )
                .data(rating)
                .build();
    }


    @Override
    public ApiResponse<Rating> saveRating(
            Integer movieId,
            Integer userId,
            Integer ratingValue) {

        if (ratingValue == null) {

            return ApiResponse.<Rating>builder()
                    .success(false)
                    .message("Rating is required.")
                    .data(null)
                    .build();
        }


        if (ratingValue < 0 ||
                ratingValue > 5) {

            return ApiResponse.<Rating>builder()
                    .success(false)
                    .message("Rating must be between 0 and 5.")
                    .data(null)
                    .build();
        }


        Rating rating =
                Rating.builder()
                        .movieId(movieId)
                        .userId(userId)
                        .rating(ratingValue)
                        .build();


        ratingRepository.saveOrUpdate(rating);


        Rating savedRating =
                ratingRepository.findByMovieAndUser(
                        movieId,
                        userId
                );


        return ApiResponse.<Rating>builder()
                .success(true)
                .message("Rating saved successfully.")
                .data(savedRating)
                .build();
    }


    @Override
    public ApiResponse<Double> getAverageRating(
            Integer movieId) {

        Double average =
                ratingRepository.getAverageRating(
                        movieId
                );

        return ApiResponse.<Double>builder()
                .success(true)
                .message("Average rating fetched.")
                .data(average)
                .build();
    }


    @Override
    public ApiResponse<Integer> getRatingCount(
            Integer movieId) {

        Integer count =
                ratingRepository.getRatingCount(
                        movieId
                );

        return ApiResponse.<Integer>builder()
                .success(true)
                .message("Rating count fetched.")
                .data(count)
                .build();
    }
}