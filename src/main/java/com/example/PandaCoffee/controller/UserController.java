package com.example.PandaCoffee.controller;

import com.example.PandaCoffee.dto.request.UserRequest;
import com.example.PandaCoffee.dto.response.UserResponse;
import com.example.PandaCoffee.mapper.UserMapperImpl;
import com.example.PandaCoffee.model.User;
import com.example.PandaCoffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
        User createdUser = userService.createUser(userRequest);
        UserResponse response = new UserMapperImpl().toUserResponse(createdUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable int userId,
            @RequestBody UserRequest userRequest) {
        User updatedUser = userService.updateUser(userId, userRequest);
        UserResponse response = new UserMapperImpl().toUserResponse(updatedUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
