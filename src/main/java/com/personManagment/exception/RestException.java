package com.personManagment.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Global Exception.
 */
@Getter
public class RestException extends RuntimeException {


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    private final transient Object data;

    public RestException(HttpStatus httpStatus, String code, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public RestException(HttpStatus httpStatus, String code, String message, Object data) {
        super(message);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}