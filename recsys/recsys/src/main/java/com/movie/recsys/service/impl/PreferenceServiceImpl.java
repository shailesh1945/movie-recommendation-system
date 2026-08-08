package com.movie.recsys.service.impl;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.preference.PreferenceResponse;
import com.movie.recsys.dto.preference.SavePreferenceRequest;
import com.movie.recsys.model.UserPreference;
import com.movie.recsys.repository.PreferenceRepository;
import com.movie.recsys.service.PreferenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreferenceServiceImpl
        implements PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public PreferenceServiceImpl(
            PreferenceRepository preferenceRepository) {

        this.preferenceRepository = preferenceRepository;

    }

    @Override
    public ApiResponse<Void> savePreference(

            Integer userId,

            SavePreferenceRequest request) {

        Integer preferenceId =
                preferenceRepository.findPreferenceId(userId);

        if (preferenceId == null) {

            UserPreference preference =
                    UserPreference.builder()

                            .userId(userId)

                            .minRating(
                                    request.getMinRating())

                            .minReleaseYear(
                                    request.getMinReleaseYear())

                            .build();

            preferenceId =
                    preferenceRepository
                            .savePreferenceAndReturnId(preference);

        }

        else {

            UserPreference preference =
                    UserPreference.builder()

                            .preferenceId(preferenceId)

                            .minRating(
                                    request.getMinRating())

                            .minReleaseYear(
                                    request.getMinReleaseYear())

                            .build();

            preferenceRepository
                    .updatePreference(preference);

        }

        preferenceRepository.deleteGenres(preferenceId);

        preferenceRepository.deleteLanguages(preferenceId);

        for (Integer genreId : request.getGenreIds()) {

            preferenceRepository.saveGenre(

                    preferenceId,

                    genreId

            );

        }

        for (Integer languageId : request.getLanguageIds()) {

            preferenceRepository.saveLanguage(

                    preferenceId,

                    languageId

            );

        }

        return ApiResponse.<Void>builder()

                .success(true)

                .message("Preferences saved successfully.")

                .build();

    }

    @Override
    public PreferenceResponse getPreference(
            Integer userId) {

        Integer preferenceId =
                preferenceRepository.findPreferenceId(userId);

        if (preferenceId == null) {

            return PreferenceResponse.builder()

                    .genreIds(List.of())

                    .languageIds(List.of())

                    .minRating(0.0)

                    .minReleaseYear(2000)

                    .build();

        }

        return preferenceRepository.getPreference(userId);

    }



}