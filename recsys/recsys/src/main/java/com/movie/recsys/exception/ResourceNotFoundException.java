package com.movie.recsys.exception;

/**
 * Thrown when a lookup by ID (or another unique key) finds nothing —
 * e.g. fetching a user that doesn't exist. Mapped to HTTP 404
 * (Not Found) by {@link GlobalExceptionHandler}.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
