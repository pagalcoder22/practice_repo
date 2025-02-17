package com.chatroom.dummywhatsapp.repository;

import com.chatroom.dummywhatsapp.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository <ChatRoom, Long> {
}
