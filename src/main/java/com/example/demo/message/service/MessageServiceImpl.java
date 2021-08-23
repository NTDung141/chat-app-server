package com.example.demo.message.service;

import com.example.demo.message.model.Message;
import com.example.demo.message.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    private final MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessage() {
        return messageRepository.findAll() ;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessageByChatBoxId(String chatBoxId) {
        return messageRepository.getMessageByChatBoxId(chatBoxId);
    }
}
