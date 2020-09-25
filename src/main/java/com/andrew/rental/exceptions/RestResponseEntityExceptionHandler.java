package com.andrew.rental.exceptions;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException ex) {
        String responseBody = ex.getMessage();
        return new ResponseEntity<>(new ApiError(responseBody),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<ApiError> handleIllegalAccessException(IllegalAccessException ex) {
        String responseBody = ex.getMessage();
        return new ResponseEntity<>(new ApiError(responseBody),
        HttpStatus.FORBIDDEN);
    }

}
