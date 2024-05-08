package com.chatroom.dummywhatsapp.service.impl;

import com.chatroom.dummywhatsapp.model.ChatRoom;
import com.chatroom.dummywhatsapp.model.Message;
import com.chatroom.dummywhatsapp.model.MessageType;
import com.chatroom.dummywhatsapp.model.User;
import com.chatroom.dummywhatsapp.repository.MessageRepository;
import com.chatroom.dummywhatsapp.service.AttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    private final MessageRepository messageRepository;

    @Autowired
    public AttachmentServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message sendAttachmentMessage(User sender, ChatRoom chatroom, String fileName, MessageType type) {
        String attachmentType = type == MessageType.IMAGE ? "Image" : "Video";
        String messageText = attachmentType + " attachment: " + fileName;
        log.info("Building the message to store in database");
        Message message = Message
                .builder()
                .sender(sender)
                .chatroom(chatroom)
                .type(type)
                .content(messageText)
                .fileName(fileName)
                .build();
        log.info("Storing the message {} in database", message);
        return messageRepository.save(message);
    }

    @Override
    public Message sendMessage(User sender, ChatRoom chatroom, String content) {
        return null;
    }
}
