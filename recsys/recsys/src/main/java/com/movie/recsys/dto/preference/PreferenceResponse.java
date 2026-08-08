package com.movie.recsys.dto.preference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceResponse {

    private List<Integer> genreIds;

    private List<Integer> languageIds;

    private Double minRating;

    private Integer minReleaseYear;

}