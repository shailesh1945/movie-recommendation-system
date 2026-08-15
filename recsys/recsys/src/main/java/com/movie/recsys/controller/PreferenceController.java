package com.movie.recsys.controller;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.preference.PreferenceResponse;
import com.movie.recsys.dto.preference.SavePreferenceRequest;
import com.movie.recsys.security.SecurityUtil;
import com.movie.recsys.service.PreferenceService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

    private final PreferenceService preferenceService;


    public PreferenceController(
            PreferenceService preferenceService
    ) {

        this.preferenceService =
                preferenceService;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Void>>
    savePreference(

            @RequestBody SavePreferenceRequest request,

            Authentication authentication
    ) {

        Integer userId =
                SecurityUtil.getUserId(authentication);


        return ResponseEntity.ok(

                preferenceService.savePreference(
                        userId,
                        request
                )
        );
    }


    @GetMapping
    public ResponseEntity<PreferenceResponse>
    getPreference(
            Authentication authentication
    ) {

        Integer userId =
                SecurityUtil.getUserId(authentication);


        return ResponseEntity.ok(

                preferenceService.getPreference(
                        userId
                )
        );
    }
}