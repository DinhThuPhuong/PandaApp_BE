package com.example.PandaCoffee.service;
import com.example.PandaCoffee.dto.request.UserRequest;
import com.example.PandaCoffee.mapper.UserMapper;
import com.example.PandaCoffee.model.User;
import com.example.PandaCoffee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserRequest userRequest) {
        var user = userMapper.toUser(userRequest);
        return userRepository.save(user);
    }
}
