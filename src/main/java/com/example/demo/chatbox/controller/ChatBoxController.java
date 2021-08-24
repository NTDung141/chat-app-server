package com.example.demo.chatbox.controller;

import com.example.demo.chatbox.model.ChatBox;
import com.example.demo.chatbox.service.ChatBoxService;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;
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
    private final UserService userService;

    @PostMapping("/create-chat-box")
    public ResponseEntity<ChatBox> createChatBox(@RequestBody ChatBox chatBox) {
        ChatBox newChatBox = chatBoxService.saveChatBox(chatBox);

        List<User> userList = userService.findUserByIdIn(newChatBox.getUserIdList());

        for (User user : userList) {
            List<String> chatBoxIdList = user.getChatBoxIdList();
            chatBoxIdList.add(newChatBox.getId());
            user.setChatBoxIdList(chatBoxIdList);

            if(userList.size() == 2) {
                List<String> contactUserIdList = user.getContactUserIdList();
                for (String id : newChatBox.getUserIdList()) {
                    if(id != user.getId()) {
                        contactUserIdList.add(id);
                        user.setContactUserIdList(contactUserIdList);
                    }
                }
            }
        }

        userService.saveUserList(userList);

        return ResponseEntity.ok(newChatBox);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatBox> updateChatBox(@PathVariable String id, @RequestBody ChatBox chatBox) {
        ChatBox updatedChatBox = chatBoxService.saveChatBox(chatBox);
        return ResponseEntity.ok(updatedChatBox);
    }
}
