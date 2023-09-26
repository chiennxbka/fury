package com.sds.fury.config.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> handlerUnauthorizedException(Exception exception) {
        return new ResponseEntity<>("Credential invalid!",new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
