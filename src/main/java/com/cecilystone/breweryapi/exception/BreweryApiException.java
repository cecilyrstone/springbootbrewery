package com.cecilystone.breweryapi.exception;

import org.springframework.http.HttpStatus;

public class BreweryApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public BreweryApiException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public BreweryApiException(HttpStatus status, String message, Throwable exception) {
        super(exception);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
