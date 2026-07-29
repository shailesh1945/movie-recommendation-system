package com.movie.recsys.controller;


import com.movie.recsys.constant.AppConstants;
import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.LoginRequest;
import com.movie.recsys.dto.LoginResponse;
import com.movie.recsys.dto.RegisterRequest;
import com.movie.recsys.exception.UnauthorizedException;
import com.movie.recsys.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @Valid @RequestBody RegisterRequest request) {

        ApiResponse<Void> response =
                authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session) {

        ApiResponse<LoginResponse> response =
                authService.login(request, session);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            HttpSession session) {

        ApiResponse<Void> response =
                authService.logout(session);

        return ResponseEntity.ok(response);

    }


    @GetMapping("/me")
    public ResponseEntity<ApiResponse<LoginResponse>> currentUser(
            HttpSession session) {

        Integer userId = (Integer) session.getAttribute(
                AppConstants.SESSION_USER_ID);

        if (userId == null) {
            throw new UnauthorizedException("Please login first.");
        }

        String name = (String) session.getAttribute(
                AppConstants.SESSION_USER_NAME);

        String role = (String) session.getAttribute(
                AppConstants.SESSION_ROLE);

        LoginResponse response =
                LoginResponse.builder()
                        .userId(userId)
                        .firstName(name)
                        .role(role)
                        .build();

        return ResponseEntity.ok(

                ApiResponse.<LoginResponse>builder()
                        .success(true)
                        .message("User Details")
                        .data(response)
                        .build()

        );

    }

}