package com.example.demo.user.repository;

import com.example.demo.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
    User findUserById(String userId);
    List<User> findUserByIdIn(List<String> userIdList);
}
