package com.chatroom.dummywhatsapp.repository;

import com.chatroom.dummywhatsapp.model.ChatRoom;
import com.chatroom.dummywhatsapp.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findByChatroomOrderByCreatedAtDesc(ChatRoom chatroom, Pageable pageable);
}
