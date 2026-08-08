
package com.movie.recsys.dto.preference;

import lombok.Data;

import java.util.List;

@Data
public class SavePreferenceRequest {

    private List<Integer> genreIds;

    private List<Integer> languageIds;

    private Double minRating;

    private Integer minReleaseYear;

}
