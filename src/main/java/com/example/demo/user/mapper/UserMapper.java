package com.example.demo.user.mapper;

import com.example.demo.chatbox.service.ChatBoxService;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserBasicInfo;
import com.example.demo.user.model.UserDTO;
import com.example.demo.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class UserMapper {
    private ChatBoxService chatBoxService;
    private UserService userService;

    public UserDTO mapToUserDTO(User user) {
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                chatBoxService.getChatBoxByIdIn(user.getChatBoxIdList()),
                mapToUserBasicInfoList(userService.findUserByIdIn(user.getContactUserIdList()))
        );
    }

    public List<UserBasicInfo> mapToUserBasicInfoList(List<User> userList) {
        List<UserBasicInfo> userBasicInfoList = new ArrayList<>();
        for (User user: userList) {
            userBasicInfoList.add(new UserBasicInfo(user.getId(), user.getFirstName(), user.getLastName()));
        }
        return userBasicInfoList;
    }
}
