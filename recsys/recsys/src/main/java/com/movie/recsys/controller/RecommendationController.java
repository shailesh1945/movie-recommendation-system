package com.movie.recsys.controller;

import com.movie.recsys.dto.recommendation.RecommendationResponse;
import com.movie.recsys.service.RecommendationService;
import com.movie.recsys.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(
            RecommendationService recommendationService) {

        this.recommendationService = recommendationService;

    }

    @GetMapping
    public ResponseEntity<List<RecommendationResponse>>
    getRecommendations(HttpSession session){

        Integer userId =
                SessionUtil.getUserId(session);

        return ResponseEntity.ok(

                recommendationService.getRecommendations(
                        userId)

        );

    }

}