package com.example.demo.message.repository;

import com.example.demo.message.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> getMessageByChatBoxId(String chatBoxId);
}
