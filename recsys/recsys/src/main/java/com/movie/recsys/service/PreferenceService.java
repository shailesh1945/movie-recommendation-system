package com.movie.recsys.service;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.preference.PreferenceResponse;
import com.movie.recsys.dto.preference.SavePreferenceRequest;

public interface PreferenceService {

    ApiResponse<Void> savePreference(

            Integer userId,

            SavePreferenceRequest request);

    PreferenceResponse getPreference(

            Integer userId);

}