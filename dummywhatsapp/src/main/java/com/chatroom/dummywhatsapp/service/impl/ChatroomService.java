package com.chatroom.dummywhatsapp.service.impl;

import com.chatroom.dummywhatsapp.exception.ChatRoomNotFoundException;
import com.chatroom.dummywhatsapp.exception.UserNotFoundException;
import com.chatroom.dummywhatsapp.model.ChatRoom;
import com.chatroom.dummywhatsapp.model.Message;
import com.chatroom.dummywhatsapp.model.User;
import com.chatroom.dummywhatsapp.repository.ChatRoomRepository;
import com.chatroom.dummywhatsapp.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ChatroomService {


    private final ChatRoomRepository chatroomRepository;

    private final UserService userService;


    private final MessageRepository messageRepository;

    @Autowired
    public ChatroomService(ChatRoomRepository chatroomRepository, UserService userService, MessageRepository messageRepository) {
        this.chatroomRepository = chatroomRepository;
        this.userService = userService;
        this.messageRepository = messageRepository;
    }

    @Transactional
    public ChatRoom createChatroom(ChatRoom chatroom) {
        return chatroomRepository.save(chatroom);
    }

    @Transactional
    public void joinChatroom(Long chatroomId) throws ChatRoomNotFoundException, UserNotFoundException {
        ChatRoom chatroom = getChatRoomById(chatroomId);
        User currentUser = userService.getCurrentUser(); // Example method to get current user
        chatroom.getParticipants().add(currentUser);
        chatroomRepository.save(chatroom);
    }

    @Transactional
    public void leaveChatroom(Long chatroomId) throws ChatRoomNotFoundException, UserNotFoundException {
        ChatRoom chatroom = getChatRoomById(chatroomId);
        User currentUser = userService.getCurrentUser(); // Example method to get current user
        chatroom.getParticipants().remove(currentUser);
        chatroomRepository.save(chatroom);
    }

    @Transactional
    public Message sendMessage(Long chatroomId, Message message) throws UserNotFoundException, ChatRoomNotFoundException {
        ChatRoom chatroom = getChatRoomById(chatroomId);
        User currentUser = userService.getCurrentUser(); // Example method to get current user
        message.setSender(currentUser);
        message.setChatroom(chatroom);
        return messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public Page<Message> getMessages(Long chatroomId, Pageable pageable) throws ChatRoomNotFoundException {
        ChatRoom chatroom = getChatRoomById(chatroomId);
        return messageRepository.findByChatroomOrderByCreatedAtDesc(chatroom, pageable);
    }

    @Transactional(readOnly = true)
    public List<User> getParticipants(Long chatroomId) throws ChatRoomNotFoundException {
        ChatRoom chatroom = getChatRoomById(chatroomId);
        return chatroom.getParticipants();
    }

    @Transactional
    public void deleteChatroom(Long chatroomId) {
        chatroomRepository.deleteById(chatroomId);
    }

    public ChatRoom getChatRoomById(Long chatroomId) throws ChatRoomNotFoundException {
        return chatroomRepository.findById(chatroomId)
                .orElseThrow(() -> new ChatRoomNotFoundException("Chatroom not found with ID: " + chatroomId, HttpStatus.NOT_FOUND));
    }



}

