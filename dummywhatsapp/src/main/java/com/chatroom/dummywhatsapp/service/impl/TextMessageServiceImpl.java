package com.chatroom.dummywhatsapp.service.impl;

import com.chatroom.dummywhatsapp.model.ChatRoom;
import com.chatroom.dummywhatsapp.model.Message;
import com.chatroom.dummywhatsapp.model.MessageType;
import com.chatroom.dummywhatsapp.model.User;
import com.chatroom.dummywhatsapp.repository.MessageRepository;
import com.chatroom.dummywhatsapp.service.TextMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TextMessageServiceImpl implements TextMessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public TextMessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message sendMessage(User sender, ChatRoom chatroom, String content) {
        return null;
    }

    @Override
    public Message sendTextMessage(User sender, ChatRoom chatroom, String text) {
        Message message = Message
                .builder()
                .sender(sender)
                .chatroom(chatroom)
                .content(text)
                .type(MessageType.TEXT)
                .build();
        log.info("Storing the message {} in database", message);
        return messageRepository.save(message);
    }
}
