package com.movie.recsys.service;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.LoginRequest;
import com.movie.recsys.dto.LoginResponse;
import com.movie.recsys.dto.RegisterRequest;
import jakarta.servlet.http.HttpSession;

public interface AuthService {

    ApiResponse<Void> register(RegisterRequest request);

    ApiResponse<LoginResponse> login(
            LoginRequest request,
            HttpSession session);

    ApiResponse<Void> logout(HttpSession session);

}