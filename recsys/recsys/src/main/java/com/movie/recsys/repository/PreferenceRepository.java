package com.movie.recsys.repository;

import com.movie.recsys.dto.preference.PreferenceResponse;
import com.movie.recsys.model.UserPreference;

import java.util.List;

public interface PreferenceRepository {

    Integer findPreferenceId(Integer userId);

    int savePreference(UserPreference preference);

    int updatePreference(UserPreference preference);

    void deleteGenres(Integer preferenceId);

    void deleteLanguages(Integer preferenceId);

    void saveGenre(Integer preferenceId,Integer genreId);

    void saveLanguage(Integer preferenceId,Integer languageId);

    List<Integer> getGenreIds(Integer preferenceId);

    List<Integer> getLanguageIds(Integer preferenceId);

    PreferenceResponse getPreference(Integer userId);

    Integer savePreferenceAndReturnId(UserPreference preference);

}