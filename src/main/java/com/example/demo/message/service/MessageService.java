package com.example.demo.message.service;

import com.example.demo.message.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    public List<Message> getAllMessage();
    public Message saveMessage(Message message);
    public List<Message> getMessageByChatBoxId(String chatBoxId);
}
