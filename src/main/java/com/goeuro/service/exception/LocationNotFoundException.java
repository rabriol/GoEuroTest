package com.goeuro.service.exception;

/**
 * Created by brito on 10/8/16.
 */
public class LocationNotFoundException extends Exception {
    private int httpStatus;

    public LocationNotFoundException(String message, int httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
