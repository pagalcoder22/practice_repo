package com.chatroom.dummywhatsapp.service.impl;

import com.chatroom.dummywhatsapp.model.*;
import com.chatroom.dummywhatsapp.repository.MessageRepository;
import com.chatroom.dummywhatsapp.service.EmojiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmojiServiceImpl implements EmojiService {

    private final MessageRepository messageRepository;

    @Autowired
    public EmojiServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message sendEmojiMessage(User sender, ChatRoom chatroom, EmojiType emojiType) {
        String messageText = "Emoji: " + emojiType;
        Message message = Message
                .builder()
                .type(MessageType.EMOJI)
                .chatroom(chatroom)
                .sender(sender)
                .content(messageText)
                .emojiType(emojiType)
                .build();
        log.info("Storing the message {} in database", message);
        return messageRepository.save(message);
    }

    @Override
    public Message sendMessage(User sender, ChatRoom chatroom, String content) {
        return null;
    }
}
