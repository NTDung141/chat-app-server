package com.example.demo.message.controller;

import com.example.demo.chatbox.model.ChatBox;
import com.example.demo.chatbox.service.ChatBoxService;
import com.example.demo.message.model.Message;
import com.example.demo.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatBoxService chatBoxService;

    @GetMapping("/message/{chat-box-id}")
    public ResponseEntity<List<Message>> getMessage(@PathVariable(name = "chat-box-id") String chatBoxId) {
        List<Message> messageList = messageService.getMessageByChatBoxId(chatBoxId);
        return ResponseEntity.ok(messageList);
    }

    @PostMapping("/message/create-message")
    public void createMessage(@RequestBody Message message) {
        messageService.saveMessage(message);

        ChatBox chatBox = chatBoxService.getChatBoxById(message.getChatBoxId());
        chatBox.setLastMessage(message);
        chatBoxService.saveChatBox(chatBox);

        String destination = "/topic/message/" + message.getReceiverId();
        messagingTemplate.convertAndSend(destination, message);
    }
}
