package com.chatroom.dummywhatsapp.service;

import com.chatroom.dummywhatsapp.model.ChatRoom;
import com.chatroom.dummywhatsapp.model.EmojiType;
import com.chatroom.dummywhatsapp.model.Message;
import com.chatroom.dummywhatsapp.model.User;
import org.springframework.stereotype.Service;

@Service
public interface EmojiService extends MessageService{
    Message sendEmojiMessage(User sender, ChatRoom chatroom, EmojiType emoji);
}
