package com.movie.recsys.service.impl;

import com.movie.recsys.dto.ApiResponse;
import com.movie.recsys.dto.user.ProfileResponse;
import com.movie.recsys.dto.user.ProfileUpdateRequest;
import com.movie.recsys.exception.EmailAlreadyExistsException;
import com.movie.recsys.repository.UserRepository;
import com.movie.recsys.service.UserService;
import com.movie.recsys.util.PasswordUtil;
import com.movie.recsys.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        implements UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    UserServiceImpl.class
            );

    private final UserRepository profileRepository;

    public UserServiceImpl(
            UserRepository profileRepository) {

        this.profileRepository =
                profileRepository;
    }

    @Override
    public ApiResponse<ProfileResponse> getProfile(HttpSession session) {
        logger.info("Get profile request received.");

        Integer userId =
                SessionUtil.getUserId(session);

        if (userId == null) {

            logger.warn(
                    "Profile requested without login."
            );

            throw new RuntimeException(
                    "User is not logged in."
            );
        }

        logger.info(
                "Fetching profile for userId: {}",
                userId
        );

        ProfileResponse profile =
                profileRepository.getProfile(userId);

        if (profile == null) {

            logger.warn(
                    "Profile not found for userId: {}",
                    userId
            );

            throw new RuntimeException(
                    "Profile not found."
            );
        }

        return ApiResponse.<ProfileResponse>builder()
                .success(true)
                .message("Profile fetched successfully.")
                .data(profile)
                .build();
    }

    @Override
    public ApiResponse<Void> updateProfile(
            ProfileUpdateRequest request,
            HttpSession session) {

        logger.info(
                "Profile update request received."
        );

        /*
         * Get logged-in user's ID
         * from HttpSession.
         */
        Integer userId =
                SessionUtil.getUserId(session);

        /*
         * User is not logged in.
         */
        if (userId == null) {

            logger.warn(
                    "Profile update attempted without login."
            );

            throw new RuntimeException(
                    "User is not logged in."
            );
        }

        logger.info(
                "Updating profile for userId: {}",
                userId
        );

        /*
         * Check email only if user
         * wants to change email.
         */
        if (request.getEmail() != null) {

            boolean emailExists =
                    profileRepository.existsByEmail(
                            request.getEmail(),
                            userId
                    );

            if (emailExists) {

                logger.warn(
                        "Email already exists: {}",
                        request.getEmail()
                );

                throw new EmailAlreadyExistsException(
                        "Email already registered."
                );
            }
        }

        /*
         * Hash password only if a new
         * password is provided.
         */
        if (request.getPassword() != null &&
                !request.getPassword().isBlank()) {

            request.setPassword(
                    PasswordUtil.hashPassword(
                            request.getPassword()
                    )
            );
        }

        /*
         * Update profile.
         */
        int rowsUpdated =
                profileRepository.updateProfile(
                        userId,
                        request
                );

        if (rowsUpdated == 0) {

            logger.warn(
                    "No profile fields were updated for userId: {}",
                    userId
            );

            throw new RuntimeException(
                    "No profile information provided."
            );
        }

        logger.info(
                "Profile updated successfully for userId: {}",
                userId
        );

        return ApiResponse.<Void>builder()
                .success(true)
                .message(
                        "Profile updated successfully."
                )
                .build();
    }
}