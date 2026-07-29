package com.movie.recsys.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}
