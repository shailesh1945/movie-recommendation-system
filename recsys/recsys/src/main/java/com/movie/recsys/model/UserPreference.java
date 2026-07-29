package com.movie.recsys.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPreference {

    private Integer preferenceId;

    private Integer userId;

    private Integer genreId;

    private Integer languageId;

}