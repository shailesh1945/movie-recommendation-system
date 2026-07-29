package com.movie.recsys.exception;

/**
 * Thrown when an operation would create a duplicate of something that
 * must be unique — e.g. registering with an email that already exists.
 * Mapped to HTTP 409 (Conflict) by {@link GlobalExceptionHandler}.
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
