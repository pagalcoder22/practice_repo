package com.chatroom.dummywhatsapp.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends Exception {

    private final HttpStatus httpStatus;

    public UserNotFoundException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }

}
