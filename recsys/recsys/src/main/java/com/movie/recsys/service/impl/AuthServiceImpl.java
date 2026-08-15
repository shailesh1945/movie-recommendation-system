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
import com.movie.recsys.util.JwtUtil;
import com.movie.recsys.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    AuthServiceImpl.class
            );

    private final AuthRepository authRepository;

    private final JwtUtil jwtUtil;


    public AuthServiceImpl(
            AuthRepository authRepository,
            JwtUtil jwtUtil
    ) {

        this.authRepository = authRepository;

        this.jwtUtil = jwtUtil;
    }


    @Override
    public ApiResponse<Void> register(
            RegisterRequest request
    ) {

        logger.info(
                "Register request received for email: {}",
                request.getEmail()
        );


        if (authRepository.existsByEmail(
                request.getEmail()
        )) {

            throw new EmailAlreadyExistsException(
                    "Email already registered."
            );
        }


        User user =
                User.builder()

                        .roleId(request.getRoleId())

                        .firstName(
                                request.getFirstName()
                        )

                        .lastName(
                                request.getLastName()
                        )

                        .email(
                                request.getEmail()
                        )

                        .password(
                                PasswordUtil.hashPassword(
                                        request.getPassword()
                                )
                        )

                        .mobile(
                                request.getMobile()
                        )

                        .gender(
                                request.getGender()
                        )

                        .status(
                                AppConstants.ACTIVE
                        )

                        .build();


        authRepository.saveUser(user);


        return ApiResponse.<Void>builder()

                .success(true)

                .message(
                        AppConstants.REGISTER_SUCCESS
                )

                .build();
    }


    @Override
    public ApiResponse<LoginResponse> login(
            LoginRequest request
    ) {

        logger.info(
                "Login request for {}",
                request.getEmail()
        );


        User user =
                authRepository.findUserWithRole(
                        request.getEmail()
                );


        if (user == null) {

            throw new InvalidCredentialsException(
                    "Invalid email or password."
            );
        }


        boolean valid =
                PasswordUtil.verifyPassword(

                        request.getPassword(),

                        user.getPassword()
                );


        if (!valid) {

            throw new InvalidCredentialsException(
                    "Invalid email or password."
            );
        }


        // =====================================
        // GENERATE JWT
        // =====================================

        String token =
                jwtUtil.generateToken(

                        user.getUserId(),

                        user.getEmail(),

                        user.getRoleName(),

                        user.getFirstName()
                );


        LoginResponse response =
                LoginResponse.builder()

                        .userId(
                                user.getUserId()
                        )

                        .firstName(
                                user.getFirstName()
                        )

                        .lastName(
                                user.getLastName()
                        )

                        .email(
                                user.getEmail()
                        )

                        .role(
                                user.getRoleName()
                        )

                        // Add this field
                        .token(token)

                        .build();


        logger.info(
                "{} logged in successfully.",
                user.getEmail()
        );


        return ApiResponse.<LoginResponse>builder()

                .success(true)

                .message(
                        AppConstants.LOGIN_SUCCESS
                )

                .data(response)

                .build();
    }

    @Override
    public ApiResponse<Void> logout() {

        logger.info("User logout request received.");

        return ApiResponse.<Void>builder()
                .success(true)
                .message(AppConstants.LOGOUT_SUCCESS)
                .build();
    }

}