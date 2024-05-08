package com.chatroom.dummywhatsapp.controller;

import com.chatroom.dummywhatsapp.exception.ChatRoomNotFoundException;
import com.chatroom.dummywhatsapp.exception.UserNotFoundException;
import com.chatroom.dummywhatsapp.model.ChatRoom;
import com.chatroom.dummywhatsapp.model.Message;
import com.chatroom.dummywhatsapp.model.User;
import com.chatroom.dummywhatsapp.service.impl.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat_rooms")
public class ChatroomController {

    private final ChatroomService chatroomService;

    @Autowired
    public ChatroomController(ChatroomService chatroomService) {
        this.chatroomService = chatroomService;
    }


    @PostMapping
    public ResponseEntity<ChatRoom> createChatroom(@RequestBody ChatRoom chatroom) {
        ChatRoom createdChatroom = chatroomService.createChatroom(chatroom);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChatroom);
    }

    @PostMapping("/{chatroomId}/join")
    public ResponseEntity<Void> joinChatroom(@PathVariable Long chatroomId) throws UserNotFoundException, ChatRoomNotFoundException {
        chatroomService.joinChatroom(chatroomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{chatroomId}/leave")
    public ResponseEntity<Void> leaveChatroom(@PathVariable Long chatroomId) throws UserNotFoundException, ChatRoomNotFoundException {
        chatroomService.leaveChatroom(chatroomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{chatroomId}/messages")
    public ResponseEntity<Message> sendMessage(@PathVariable Long chatroomId, @RequestBody Message message) throws UserNotFoundException, ChatRoomNotFoundException {
        Message sentMessage = chatroomService.sendMessage(chatroomId, message);
        return ResponseEntity.status(HttpStatus.CREATED).body(sentMessage);
    }

    @GetMapping("/{chatroomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable Long chatroomId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) throws ChatRoomNotFoundException {
        Pageable pageable = PageRequest.of(page, size);
        Page<Message> messages = chatroomService.getMessages(chatroomId, pageable);
        return ResponseEntity.ok(messages.getContent());
    }

    @GetMapping("/{chatroomId}/participants")
    public ResponseEntity<List<User>> getChatroomParticipants(@PathVariable Long chatroomId) throws ChatRoomNotFoundException {
        List<User> participants = chatroomService.getParticipants(chatroomId);
        return ResponseEntity.ok(participants);
    }

    @DeleteMapping("/{chatroomId}")
    public ResponseEntity<Void> deleteChatroom(@PathVariable Long chatroomId) {
        chatroomService.deleteChatroom(chatroomId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
