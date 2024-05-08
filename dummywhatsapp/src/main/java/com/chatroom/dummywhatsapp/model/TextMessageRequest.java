package com.chatroom.dummywhatsapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextMessageRequest {

    private Long chatroomId;
    private String content;

}
