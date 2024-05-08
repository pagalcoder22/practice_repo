package com.chatroom.dummywhatsapp.service.impl;

import com.chatroom.dummywhatsapp.exception.UserProfileNotFoundException;
import com.chatroom.dummywhatsapp.model.UserProfile;
import com.chatroom.dummywhatsapp.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile getUserProfile(Long userId) throws UserProfileNotFoundException {
        return userProfileRepository.findById(userId)
                .orElseThrow(() -> new UserProfileNotFoundException("UserProfile not found for user id: " + userId, HttpStatus.NOT_FOUND));
    }

    public UserProfile updateUserProfile(Long userId, UserProfile userProfile) throws UserProfileNotFoundException {
        UserProfile existingProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new UserProfileNotFoundException("UserProfile not found for user id: " + userId,HttpStatus.NOT_FOUND));

        // Update existing profile fields
        existingProfile.setFirstName(userProfile.getFirstName());
        existingProfile.setLastName(userProfile.getLastName());
        // Update other fields as needed

        return userProfileRepository.save(existingProfile);
    }

    public void deleteUserProfile(Long userId) throws UserProfileNotFoundException {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new UserProfileNotFoundException("UserProfile not found for user id: " + userId, HttpStatus.NOT_FOUND));

        userProfileRepository.delete(userProfile);
    }

}
