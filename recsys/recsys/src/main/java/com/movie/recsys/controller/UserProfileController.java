package com.movie.recsys.controller;


import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.user.ProfileResponse;
import com.movie.recsys.dto.user.ProfileUpdateRequest;
import com.movie.recsys.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
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
            HttpSession session) {

        return ResponseEntity.ok(
                profileService.updateProfile(
                        request,
                        session
                )
        );
    }
    //get user information
    @GetMapping
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfile(
            HttpSession session) {

        return ResponseEntity.ok(
                profileService.getProfile(session)
        );
    }
}