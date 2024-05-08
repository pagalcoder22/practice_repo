package com.chatroom.dummywhatsapp.service.impl;

import com.chatroom.dummywhatsapp.exception.UserNotFoundException;
import com.chatroom.dummywhatsapp.model.User;
import com.chatroom.dummywhatsapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long userId) throws UserNotFoundException {
        return userRepository
                .findById(userId)
                .orElseThrow(()->new UserNotFoundException("User Not Found With Id : " + userId,HttpStatus.NOT_FOUND));
    }

    public void deleteUser(Long userId) {
    }


    public User getCurrentUser() throws UserNotFoundException {
        // Example method to get current user, implement according to your authentication mechanism
        // You may use Spring Security's SecurityContextHolder to retrieve the authenticated user
        // For simplicity, let's assume it returns a hardcoded user for demonstration
        return userRepository.findById(1L).orElseThrow(() -> new UserNotFoundException("User not found with ID: 1", HttpStatus.NOT_FOUND));
    }
}
