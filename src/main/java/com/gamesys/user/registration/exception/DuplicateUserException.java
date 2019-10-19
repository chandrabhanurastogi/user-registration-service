package com.gamesys.user.registration.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String errorMessage) {
        super(errorMessage);
    }
}
