package com.chatroom.dummywhatsapp.controller;

import com.chatroom.dummywhatsapp.exception.UserNotFoundException;
import com.chatroom.dummywhatsapp.model.User;
import com.chatroom.dummywhatsapp.service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) throws UserNotFoundException {
        log.info("Fetch user with id : {} ", userId);
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        log.info("Delete user with id : {} ", userId);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

}

