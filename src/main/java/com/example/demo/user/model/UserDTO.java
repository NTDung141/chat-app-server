package com.example.demo.user.model;

import com.example.demo.chatbox.model.ChatBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private List<ChatBox> chatBoxList;
    private List<UserBasicInfo> contactUserList;
}
