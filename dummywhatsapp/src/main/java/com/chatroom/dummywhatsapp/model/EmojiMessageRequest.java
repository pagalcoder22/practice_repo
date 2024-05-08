package com.chatroom.dummywhatsapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmojiMessageRequest {
    private Long chatroomId;
    private EmojiType emoji;
}
