package com.ridet.ridetogether.exception;

public class MatchNotFoundException extends RuntimeException {
    public MatchNotFoundException() {

    }
    public MatchNotFoundException(String message) {
        super(message);
    }
}
