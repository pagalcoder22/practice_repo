package com.chatroom.dummywhatsapp.controller;

import com.chatroom.dummywhatsapp.exception.ChatRoomNotFoundException;
import com.chatroom.dummywhatsapp.exception.UserNotFoundException;
import com.chatroom.dummywhatsapp.model.*;
import com.chatroom.dummywhatsapp.service.AttachmentService;
import com.chatroom.dummywhatsapp.service.EmojiService;
import com.chatroom.dummywhatsapp.service.impl.ChatroomService;
import com.chatroom.dummywhatsapp.service.TextMessageService;
import com.chatroom.dummywhatsapp.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/messages")
@Slf4j
public class MessageController {

    private final TextMessageService textMessageService;

    private final ChatroomService chatroomService;

    private final AttachmentService attachmentService;

    private final EmojiService emojiService;

    private final UserService userService;

    @Autowired
    public MessageController(TextMessageService textMessageService, ChatroomService chatroomService, AttachmentService attachmentService, EmojiService emojiService, UserService userService) {
        this.textMessageService = textMessageService;
        this.chatroomService = chatroomService;
        this.attachmentService = attachmentService;
        this.emojiService = emojiService;
        this.userService = userService;
    }

    @PostMapping("/text")
    public ResponseEntity<Message> sendTextMessage(@RequestBody TextMessageRequest request) throws ChatRoomNotFoundException, UserNotFoundException {
        if (request.getContent().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User sender = userService.getCurrentUser(); // Example method to get current user
        ChatRoom chatroom = chatroomService.getChatRoomById(request.getChatroomId()); // Example method to get chatroom
        Message message = textMessageService.sendTextMessage(sender, chatroom, request.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/attachment")
    public ResponseEntity<Message> sendAttachmentMessage(@RequestParam("file") MultipartFile file,
                                                         @RequestParam("chatroomId") Long chatroomId,
                                                         @RequestParam("type") MessageType type) throws UserNotFoundException, ChatRoomNotFoundException {
        if (file.isEmpty() || (type != MessageType.IMAGE && type != MessageType.VIDEO)) {
            return ResponseEntity.badRequest().build();
        }

        try {
            String fileName = file.getOriginalFilename();
            String filePath = type == MessageType.IMAGE ? "root/picture/" : "root/video/";
            File destFile = new File(filePath + fileName);
            file.transferTo(destFile);

            User sender = userService.getCurrentUser(); // Example method to get current user
            ChatRoom chatroom = chatroomService.getChatRoomById(chatroomId); // Example method to get chatroom
            Message message = attachmentService.sendAttachmentMessage(sender, chatroom, fileName, type);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (IOException e) {
            log.error("Attachment sending failed : {} ",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/emoji")
    public ResponseEntity<Message> sendEmojiMessage(@RequestBody EmojiMessageRequest request) throws UserNotFoundException, ChatRoomNotFoundException {
        if (request.getEmoji() == null) {
            return ResponseEntity.badRequest().build();
        }
        User sender = userService.getCurrentUser(); // Example method to get current user
        ChatRoom chatroom = chatroomService.getChatRoomById(request.getChatroomId()); // Example method to get chatroom
        Message message = emojiService.sendEmojiMessage(sender, chatroom, request.getEmoji());
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

}

