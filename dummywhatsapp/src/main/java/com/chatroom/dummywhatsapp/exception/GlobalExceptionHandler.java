package com.chatroom.dummywhatsapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        HttpStatus status = ex.getHttpStatus();
        return handleExceptionInternal(ex, errorMessage, null, status, request);
    }

    @ExceptionHandler(UserProfileNotFoundException.class)
    public ResponseEntity<Object> handleUserProfileNotFoundException(UserProfileNotFoundException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        HttpStatus status = ex.getHttpStatus();
        return handleExceptionInternal(ex, errorMessage, null, status, request);
    }

}
