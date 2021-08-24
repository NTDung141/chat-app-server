package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDTO;
import com.example.demo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User register(User user) {
        User existedUser = userRepository.findUserByUsername(user.getUsername());
        if(existedUser != null) {
            return null;
        }
        else {
            User createdUser = userRepository.save(user);
            return createdUser;
        }
    }

    @Override
    public User login(User user) {
        User foundUser = userRepository.findUserByUsername(user.getUsername());
        if(foundUser == null) {
            return null;
        }
        else {
            if(foundUser.getPassword().equals(user.getPassword())) {
                return foundUser;
            }
            else {
                return null;
            }
        }
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void saveUserList(List<User> userList) {
        userRepository.saveAll(userList);
    }

    @Override
    public User findUserById(String userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public List<User> findUserByIdIn(List<String> userIdList) {
        return userRepository.findUserByIdIn(userIdList);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User existedUser = userRepository.findUserByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                existedUser.getUsername(),
                existedUser.getPassword(),
                new ArrayList<>()
        );
    }
}
