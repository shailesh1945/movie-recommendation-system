package com.movie.recsys.service.impl;


import com.movie.recsys.constant.AppConstants;
import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.LoginRequest;
import com.movie.recsys.dto.LoginResponse;
import com.movie.recsys.dto.RegisterRequest;
import com.movie.recsys.exception.EmailAlreadyExistsException;
import com.movie.recsys.exception.InvalidCredentialsException;
import com.movie.recsys.model.User;
import com.movie.recsys.repository.AuthRepository;
import com.movie.recsys.service.AuthService;
import com.movie.recsys.util.PasswordUtil;
import com.movie.recsys.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthServiceImpl.class);

    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public ApiResponse<Void> register(RegisterRequest request) {

        logger.info("Register request received for email: {}",
                request.getEmail());

        if (authRepository.existsByEmail(request.getEmail())) {

            logger.warn("Email already exists: {}",
                    request.getEmail());

            throw new EmailAlreadyExistsException(
                    "Email already registered."
            );
        }

        User user = User.builder()
                .roleId(request.getRoleId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(
                        PasswordUtil.hashPassword(
                                request.getPassword()))
                .mobile(request.getMobile())
                .gender(request.getGender())
                .status(AppConstants.ACTIVE)
                .build();

        int rows = authRepository.saveUser(user);

        logger.info("{} row(s) inserted.", rows);

        return ApiResponse.<Void>builder()
                .success(true)
                .message(AppConstants.REGISTER_SUCCESS)
                .build();

    }

    @Override
    public ApiResponse<LoginResponse> login(
            LoginRequest request,
            HttpSession session) {

        logger.info("Login request for {}",
                request.getEmail());

        User user =
                authRepository.findUserWithRole(
                        request.getEmail());

        if (user == null) {

            logger.warn("User not found.");

            throw new InvalidCredentialsException(
                    "Invalid email or password."
            );

        }

        boolean valid =
                PasswordUtil.verifyPassword(
                        request.getPassword(),
                        user.getPassword());

        if (!valid) {

            logger.warn("Password mismatch.");

            throw new InvalidCredentialsException(
                    "Invalid email or password."
            );

        }

        LoginResponse response =
                LoginResponse.builder()
                        .userId(user.getUserId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .role(user.getRoleName())
                        .build();

        SessionUtil.createSession(
                session,
                response);

        logger.info("{} logged in successfully.",
                user.getEmail());

        return ApiResponse.<LoginResponse>builder()
                .success(true)
                .message(AppConstants.LOGIN_SUCCESS)
                .data(response)
                .build();

    }

    @Override
    public ApiResponse<Void> logout(
            HttpSession session) {

        SessionUtil.invalidateSession(session);

        logger.info("User logged out.");

        return ApiResponse.<Void>builder()
                .success(true)
                .message(AppConstants.LOGOUT_SUCCESS)
                .build();

    }

}