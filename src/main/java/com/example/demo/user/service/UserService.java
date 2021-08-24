package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    public User register(User user);
    public User login(User user);
    public User saveUser(User user);
    public void saveUserList(List<User> userList);
    public User findUserById(String userId);
    public List<User> findUserByIdIn(List<String> userIdList);
    public List<User> getAllUser();
}
