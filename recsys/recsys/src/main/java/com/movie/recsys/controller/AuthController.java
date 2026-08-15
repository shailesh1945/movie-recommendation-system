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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(
            AuthService authService
    ) {

        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        ApiResponse<Void> response =
                authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request
    ) {

        ApiResponse<LoginResponse> response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }


    @GetMapping("/me")
    public ResponseEntity<ApiResponse<LoginResponse>> currentUser(
            Authentication authentication
    ) {

        if (authentication == null ||
                !authentication.isAuthenticated()) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }


        Integer userId =
                (Integer) authentication.getPrincipal();


        LoginResponse response =
                LoginResponse.builder()

                        .userId(userId)

                        .build();


        return ResponseEntity.ok(

                ApiResponse.<LoginResponse>builder()

                        .success(true)

                        .message("User Details")

                        .data(response)

                        .build()
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {

        return ResponseEntity.ok(
                authService.logout()
        );
    }

}