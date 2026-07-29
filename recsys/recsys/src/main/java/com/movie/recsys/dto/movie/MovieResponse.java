package com.movie.recsys.dto.movie;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MovieResponse {

    private Integer movieId;

    private String title;

    private Integer releaseYear;

    private Integer duration;

    private String director;

    private String language;

    private BigDecimal averageRating;

    private String posterUrl;

}
