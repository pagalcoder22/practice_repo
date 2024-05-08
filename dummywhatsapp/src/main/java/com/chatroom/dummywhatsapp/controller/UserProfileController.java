package com.chatroom.dummywhatsapp.controller;

import com.chatroom.dummywhatsapp.exception.UserProfileNotFoundException;
import com.chatroom.dummywhatsapp.model.UserProfile;
import com.chatroom.dummywhatsapp.service.impl.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user_profile")
public class UserProfileController {


    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long userId) throws UserProfileNotFoundException {
        UserProfile userProfile = userProfileService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Long userId, @RequestBody UserProfile userProfile) throws UserProfileNotFoundException {
        UserProfile updatedUserProfile = userProfileService.updateUserProfile(userId, userProfile);
        return ResponseEntity.ok(updatedUserProfile);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long userId) throws UserProfileNotFoundException {
        userProfileService.deleteUserProfile(userId);
        return ResponseEntity.noContent().build();
    }
}