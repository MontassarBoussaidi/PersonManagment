package com.personManagment.rest;

import com.personManagment.exception.RestException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Rest Exception handler
 */
@ControllerAdvice
public class ResponseEntityExceptionHandlerController extends ResponseEntityExceptionHandler {
    /**
     * RestException handler: construct and return <code>ExceptionData</code> json response object.
     * @return ResponseEntity
     */
    @ExceptionHandler({ RestException.class })
    public ResponseEntity<Object> handleRestException(RestException ex, WebRequest request) {
        return new ResponseEntity<>(new DataException(ex.getHttpStatus(), ex.getCode(), ex.getMessage(), ex.getData()),
                new HttpHeaders(), ex.getHttpStatus());
    }

    /**
     * Json Object to return when exception is throwing
     */
    @Getter
    @Setter
    class DataException {

        private int status;
        private String code;
        private String message;
        private Object data;

        public DataException(HttpStatus httpStatus, String code, String message, Object data) {
            this.status = httpStatus.value();
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }
}
