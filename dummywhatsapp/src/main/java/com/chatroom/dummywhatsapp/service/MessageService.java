package com.chatroom.dummywhatsapp.service;

import com.chatroom.dummywhatsapp.model.ChatRoom;
import com.chatroom.dummywhatsapp.model.Message;
import com.chatroom.dummywhatsapp.model.User;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    Message sendMessage(User sender, ChatRoom chatroom, String content);
}
