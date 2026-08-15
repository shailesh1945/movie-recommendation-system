package com.movie.recsys.controller;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.user.ProfileResponse;
import com.movie.recsys.dto.user.ProfileUpdateRequest;
import com.movie.recsys.security.SecurityUtil;
import com.movie.recsys.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserService profileService;


    public UserProfileController(
            UserService profileService) {

        this.profileService =
                profileService;
    }


    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updateProfile(

            @RequestBody ProfileUpdateRequest request,

            Authentication authentication) {

        Integer userId =
                SecurityUtil.getUserId(authentication);


        return ResponseEntity.ok(
                profileService.updateProfile(
                        userId,
                        request
                )
        );
    }


    // Get user information
    @GetMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(

            Authentication authentication) {

        Integer userId =
                SecurityUtil.getUserId(authentication);


        return ResponseEntity.ok(
                profileService.getProfile(userId)
        );
    }
}