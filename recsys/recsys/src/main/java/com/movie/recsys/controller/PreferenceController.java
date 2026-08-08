package com.movie.recsys.controller;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.preference.PreferenceResponse;
import com.movie.recsys.dto.preference.SavePreferenceRequest;
import com.movie.recsys.service.PreferenceService;
import com.movie.recsys.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

    private final PreferenceService preferenceService;

    public PreferenceController(
            PreferenceService preferenceService) {

        this.preferenceService = preferenceService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> savePreference(

            @RequestBody SavePreferenceRequest request,

            HttpSession session) {

        Integer userId =
                SessionUtil.getUserId(session);

        return ResponseEntity.ok(

                preferenceService.savePreference(

                        userId,

                        request)

        );

    }

    @GetMapping
    public ResponseEntity<PreferenceResponse> getPreference(

            HttpSession session) {

        Integer userId =
                SessionUtil.getUserId(session);

        return ResponseEntity.ok(

                preferenceService.getPreference(

                        userId)

        );

    }

}