package com.movie.recsys.repository;

import com.movie.recsys.model.Rating;

public interface RatingRepository {

    Rating findByMovieAndUser(Integer movieId, Integer userId);

    void saveOrUpdate(Rating rating);

    Double getAverageRating(Integer movieId);

    Integer getRatingCount(Integer movieId);
}