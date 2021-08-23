package com.example.demo.chatbox.controller;

import com.example.demo.chatbox.model.ChatBox;
import com.example.demo.chatbox.service.ChatBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/chatbox")
@RequiredArgsConstructor
public class ChatBoxController {
    private final ChatBoxService chatBoxService;

    @PostMapping("")
    public ResponseEntity<ChatBox> createChatBox(@RequestBody ChatBox chatBox) {
        ChatBox newChatBox = chatBoxService.saveChatBox(chatBox);
        return ResponseEntity.ok(newChatBox);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatBox> updateChatBox(@PathVariable String id, @RequestBody ChatBox chatBox) {
        ChatBox updatedChatBox = chatBoxService.saveChatBox(chatBox);
        return ResponseEntity.ok(updatedChatBox);
    }
}
