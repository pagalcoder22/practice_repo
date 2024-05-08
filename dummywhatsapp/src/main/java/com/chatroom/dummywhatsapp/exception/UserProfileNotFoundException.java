package com.chatroom.dummywhatsapp.exception;

import org.springframework.http.HttpStatus;

public class UserProfileNotFoundException extends Exception {

    private HttpStatus httpStatus;

    public UserProfileNotFoundException (String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


    public HttpStatus getHttpStatus(){
        return this.httpStatus;
    }

}
