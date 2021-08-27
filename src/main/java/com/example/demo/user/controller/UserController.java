package com.example.demo.user.controller;

import com.example.demo.chatbox.service.ChatBoxService;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDTO;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@PreAuthorize("isAuthenticated()")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ChatBoxService chatBoxService;

    @PutMapping("/add-chat-box/{user-id}/{chat-box-id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(name = "user-id") String userId, @PathVariable(name = "chat-box-id") String chatBoxId) {
        User user = userService.findUserById(userId);
        List<String> updatedChatBoxIdList = user.getChatBoxIdList();
        updatedChatBoxIdList.add(chatBoxId);
        user.setChatBoxIdList(updatedChatBoxIdList);
        User updatedUser = userService.saveUser(user);
        UserDTO updatedUserDTO = new UserMapper(chatBoxService, userService).mapToUserDTO(updatedUser);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @GetMapping("/all-user")
    private ResponseEntity<List<UserDTO>> getAllUser() {
        List<User> allUserList = userService.getAllUser();
        List<UserDTO> allUserDTO = new ArrayList<UserDTO>();
        for (User user : allUserList) {
            UserDTO userDTO = new UserMapper(chatBoxService, userService).mapToUserDTO(user);
            allUserDTO.add(userDTO);
        }

        return ResponseEntity.ok(allUserDTO);
    }

    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getUserById(@PathVariable String id) {
        User foundUser = userService.findUserById(id);
        UserDTO foundUserDTO = new UserMapper(chatBoxService, userService).mapToUserDTO(foundUser);
        return ResponseEntity.ok(foundUserDTO);
    }
}
