package com.example.demo.chatbox.repository;

import com.example.demo.chatbox.model.ChatBox;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatBoxRepository extends MongoRepository<ChatBox, String> {
    List<ChatBox> findChatBoxByIdIn(List<String> chatBoxIdList);
    ChatBox findChatBoxById(String id);
}
