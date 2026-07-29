package com.movie.recsys.exception;


import com.movie.recsys.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Validation Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<String> errors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError ->
                                fieldError.getField()
                                        + " : "
                                        + fieldError.getDefaultMessage())
                        .toList();

        ErrorResponse response =
                ErrorResponse.builder()
                        .success(false)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .message("Validation Failed")
                        .validationErrors(errors)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Email Exists
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(
            EmailAlreadyExistsException ex) {

        logger.warn("{}", ex.getMessage());

        ErrorResponse response =
                buildResponse(
                        HttpStatus.CONFLICT,
                        ex.getMessage()
                );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    /**
     * Invalid Credentials
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidLogin(
            InvalidCredentialsException ex) {

        logger.warn("{}", ex.getMessage());

        ErrorResponse response =
                buildResponse(
                        HttpStatus.UNAUTHORIZED,
                        ex.getMessage()
                );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    /**
     * Resource Not Found
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex) {

        ErrorResponse response =
                buildResponse(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage()
                );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    /**
     * Unauthorized
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorized(
            UnauthorizedException ex) {

        ErrorResponse response =
                buildResponse(
                        HttpStatus.UNAUTHORIZED,
                        ex.getMessage()
                );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }

    /**
     * JDBC Exception
     */
    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<ErrorResponse> handleDatabase(
            DatabaseOperationException ex) {

        logger.error("Database Error", ex);

        ErrorResponse response =
                buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Database operation failed."
                );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    /**
     * Generic Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            Exception ex) {

        logger.error("Unexpected Error", ex);

        ErrorResponse response =
                buildResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Something went wrong."
                );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    /**
     * Common Method
     */
    private ErrorResponse buildResponse(
            HttpStatus status,
            String message) {

        return ErrorResponse.builder()
                .success(false)
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();

    }

}