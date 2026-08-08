package com.movie.recsys.dto.recommendation;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RecommendationResponse {

    private Integer movieId;

    private String title;

    private String director;

    private Integer releaseYear;

    private BigDecimal averageRating;

    private String posterUrl;

    private String language;

    private Integer score;

}