package com.movie.recsys.repository;

import com.movie.recsys.dto.recommendation.RecommendationResponse;

import java.util.List;

public interface RecommendationRepository {

    List<RecommendationResponse> getRecommendations(
            Integer userId);

}