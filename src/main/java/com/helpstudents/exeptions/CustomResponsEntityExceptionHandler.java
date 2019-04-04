package com.helpstudents.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponsEntityExceptionHandler {
    @ExceptionHandler(ExistsExceptions.class)
    public ResponseEntity<?> handleDroidExistException (Exception e, WebRequest req){
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage(),
                req.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }
}
