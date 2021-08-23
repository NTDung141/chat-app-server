package com.example.demo.user.controller;

import com.example.demo.authentication.model.AuthenticationResponse;
import com.example.demo.chatbox.service.ChatBoxService;
import com.example.demo.security.jwt.JwtTokenService;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDTO;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final ChatBoxService chatBoxService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User user) {
        UserDTO loginUser = new UserMapper(chatBoxService, userService).mapToUserDTO(userService.login(user));
        String username = user.getUsername();
        String password = user.getPassword();
        String jwtToken = "";
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwtToken = jwtTokenService.createJwtToken(authentication, username);

        } catch (Exception e) {

        }
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(loginUser, jwtToken);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User newUser = userService.register(user);
        return ResponseEntity.ok(newUser);
    }

    // add new user to chat room
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
        List<User> allUser = userService.getAllUser();
        List<UserDTO> allUserDTO = new ArrayList<UserDTO>();
        for (User user : allUser) {
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
