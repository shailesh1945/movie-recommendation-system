package com.movie.recsys.service;

import com.movie.recsys.dto.recommendation.RecommendationResponse;

import java.util.List;

public interface RecommendationService {

    List<RecommendationResponse> getRecommendations(
            Integer userId);

}