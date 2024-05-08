package com.chatroom.dummywhatsapp.service;

import com.chatroom.dummywhatsapp.model.ChatRoom;
import com.chatroom.dummywhatsapp.model.Message;
import com.chatroom.dummywhatsapp.model.MessageType;
import com.chatroom.dummywhatsapp.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AttachmentService extends MessageService {
    Message sendAttachmentMessage(User sender, ChatRoom chatroom, String fileName, MessageType type);
}
