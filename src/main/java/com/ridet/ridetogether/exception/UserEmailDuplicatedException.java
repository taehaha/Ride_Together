package com.ridet.ridetogether.exception;

public class UserEmailDuplicatedException extends RuntimeException {
    public UserEmailDuplicatedException() {

    }
    public UserEmailDuplicatedException(String message) {
        super(message);
    }
}
