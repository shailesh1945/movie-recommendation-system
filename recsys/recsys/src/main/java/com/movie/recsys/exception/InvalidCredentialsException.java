package com.movie.recsys.exception;

/**
 * Thrown when login credentials don't match — wrong email or wrong
 * password. Deliberately uses one exception (and one generic message)
 * for both cases; telling a caller "email not found" vs "wrong
 * password" separately would let an attacker enumerate valid emails.
 * Mapped to HTTP 401 (Unauthorized) by {@link GlobalExceptionHandler}.
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
