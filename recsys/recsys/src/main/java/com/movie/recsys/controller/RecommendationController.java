package com.movie.recsys.controller;

import com.movie.recsys.dto.recommendation.RecommendationResponse;
import com.movie.recsys.security.SecurityUtil;
import com.movie.recsys.service.RecommendationService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;


    public RecommendationController(
            RecommendationService recommendationService
    ) {

        this.recommendationService =
                recommendationService;
    }


    @GetMapping
    public ResponseEntity<List<RecommendationResponse>>
    getRecommendations(
            Authentication authentication
    ) {

        Integer userId =
                SecurityUtil.getUserId(authentication);


        return ResponseEntity.ok(

                recommendationService
                        .getRecommendations(userId)
        );
    }
}