package com.sandav.pruebatecnica.api;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleWrongData(RuntimeException ex) {
        return new ResponseEntity<String>("Wrong data entered. Exception ERROR: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundElementData(RuntimeException ex) {
        return new ResponseEntity<String>("Element not found. Exception ERROR: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
