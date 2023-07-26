package com.personManagment.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RestException {
    public BadRequestException(String code, String message) {
        super(HttpStatus.BAD_REQUEST, code, message);
    }

    public BadRequestException(String code, String message, Object data) {
        super(HttpStatus.BAD_REQUEST, code, message, data);
    }


}
