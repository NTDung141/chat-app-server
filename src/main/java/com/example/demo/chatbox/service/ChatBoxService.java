package com.example.demo.chatbox.service;

import com.example.demo.chatbox.model.ChatBox;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatBoxService {
    public ChatBox saveChatBox(ChatBox chatBox);
    public List<ChatBox> getChatBoxByIdIn(List<String> chatBoxIdList);
    public ChatBox getChatBoxById(String id);
}
