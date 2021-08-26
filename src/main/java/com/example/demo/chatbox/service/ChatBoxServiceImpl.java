package com.example.demo.chatbox.service;

import com.example.demo.chatbox.model.ChatBox;
import com.example.demo.chatbox.repository.ChatBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List<ChatBox> chatBoxList = chatBoxRepository.findChatBoxByIdIn(chatBoxIdList);

        if(chatBoxList.size() > 1) {
            Comparator<ChatBox> chatBoxComparator = (ChatBox chatBox1, ChatBox chatBox2) ->
                    chatBox1.getLastTimeAccessUnix().compareTo(chatBox2.getLastTimeAccessUnix());

            List<ChatBox> sortedChatBoxList = chatBoxList.stream().sorted(chatBoxComparator.reversed()).collect(Collectors.toList());

            return sortedChatBoxList;
        }

        else {
            return chatBoxList;
        }
    }

    @Override
    public ChatBox getChatBoxById(String id) {
        return chatBoxRepository.findChatBoxById(id);
    }
}
