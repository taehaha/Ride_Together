package com.ridet.ridetogether.exception;

public class RideAlreadyMatchedException extends RuntimeException {
    public RideAlreadyMatchedException() {

    }
    public RideAlreadyMatchedException(String message) {
        super(message);
    }
}
