package com.movie.recsys.dto.movie;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class MovieDetailsResponse {

    private Integer movieId;

    private String title;

    private String description;

    private Integer releaseYear;

    private Integer duration;

    private String director;

    private String language;

    private BigDecimal averageRating;

    private String posterUrl;

    private String trailerUrl;

    private List<String> genres;

}