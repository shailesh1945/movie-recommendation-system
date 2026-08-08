package com.movie.recsys.service.impl;

import com.movie.recsys.dto.recommendation.RecommendationResponse;
import com.movie.recsys.repository.RecommendationRepository;
import com.movie.recsys.service.RecommendationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl
        implements RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationServiceImpl(

            RecommendationRepository recommendationRepository) {

        this.recommendationRepository =
                recommendationRepository;

    }

    @Override
    public List<RecommendationResponse>
    getRecommendations(Integer userId) {

        return recommendationRepository
                .getRecommendations(userId);

    }

}