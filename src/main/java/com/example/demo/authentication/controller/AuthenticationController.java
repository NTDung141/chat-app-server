package com.example.demo.authentication.controller;

import com.example.demo.authentication.model.AuthenticationLoginRequest;
import com.example.demo.authentication.model.AuthenticationResponse;
import com.example.demo.chatbox.service.ChatBoxService;
import com.example.demo.security.jwt.JwtTokenService;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDTO;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final ChatBoxService chatBoxService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationLoginRequest authReq) {
        UserDTO loginUser = new UserMapper(chatBoxService, userService)
                .mapToUserDTO(userService.login(authReq.getUsername(), authReq.getPassword()));
        String username = authReq.getUsername();
        String password = authReq.getPassword();
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
}
