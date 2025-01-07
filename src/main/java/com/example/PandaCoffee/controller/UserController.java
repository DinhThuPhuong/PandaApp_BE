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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) throws Exception {
        System.out.println("Received UserRequest: " + userRequest); // Log dữ liệu để kiểm tra
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userRequest));
    }


    //Xac thuc ma
    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyUser(
            @RequestParam String email,
            @RequestParam String code) {
        boolean message = userService.verifyUser(email, code);
        return ResponseEntity.ok(message);
    }


    //Chinh sua profile
    @PutMapping("edit/{id}")
    public ResponseEntity<UserResponse> updateProfile(
            @PathVariable("id") int userId,
            @ModelAttribute UserRequest userRequest,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        UserResponse updatedUser = userService.updateProfile(userId, userRequest, file);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }


    //Truy van thong tin tai khoan
    @GetMapping("/getProfile")
    public ResponseEntity<UserResponse> getProfile()
    {
        System.out.println("Truy van thong tin tai khoan dang dang nhap");
        return ResponseEntity.status(HttpStatus.OK).body(userService.myInfo());
    }

    //Truy van thong tin tai khoan theo id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") int userId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findUserById(userId));
    }


//    @PutMapping("/users/{userId}")
//    public ResponseEntity<UserResponse> updateUser(
//            @PathVariable int userId,
//            @RequestBody UserRequest userRequest) {
//        User updatedUser = userService.updateUser(userId, userRequest);
//        UserResponse response = new UserMapperImpl().toUserResponse(updatedUser);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/users/{userId}")
//    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
//        userService.deleteUser(userId);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
