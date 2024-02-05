package com.ridet.ridetogether.exception;

public class RideNotFoundException extends RuntimeException {
    public RideNotFoundException() {

    }
    public RideNotFoundException(String message) {
        super(message);
    }
}
