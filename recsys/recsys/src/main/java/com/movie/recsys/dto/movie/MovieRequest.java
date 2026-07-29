package com.movie.recsys.dto.movie;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieRequest {

    @NotBlank(message = "Movie title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Release year is required")
    @Min(value = 1900, message = "Invalid release year")
    private Integer releaseYear;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be greater than zero")
    private Integer duration;

    @NotBlank(message = "Director is required")
    private String director;

    @NotNull(message = "Language is required")
    private Integer languageId;

    private String posterUrl;

    private String trailerUrl;

}
