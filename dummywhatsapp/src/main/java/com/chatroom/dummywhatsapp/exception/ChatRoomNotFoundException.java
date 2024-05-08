package com.chatroom.dummywhatsapp.exception;

import org.springframework.http.HttpStatus;

public class ChatRoomNotFoundException extends Exception {

    private final HttpStatus httpStatus;
    public ChatRoomNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    private HttpStatus getHttpStatus(){
        return this.httpStatus;
    }
}
