package com.ridet.ridetogether.exception;

public class RideAlreadyOpenedException extends RuntimeException {
    public RideAlreadyOpenedException() {

    }
    public RideAlreadyOpenedException(String message) {
        super(message);
    }
}
