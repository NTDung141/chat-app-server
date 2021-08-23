package com.example.demo.chatbox.service;

import com.example.demo.chatbox.model.ChatBox;
import com.example.demo.chatbox.repository.ChatBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatBoxServiceImpl implements ChatBoxService{
    private final ChatBoxRepository chatBoxRepository;

    @Override
    public ChatBox saveChatBox(ChatBox chatBox) {
        return chatBoxRepository.save(chatBox);
    }

    @Override
    public List<ChatBox> getChatBoxByIdIn(List<String> chatBoxIdList) {
        return chatBoxRepository.findChatBoxByIdIn(chatBoxIdList);
    }
}
