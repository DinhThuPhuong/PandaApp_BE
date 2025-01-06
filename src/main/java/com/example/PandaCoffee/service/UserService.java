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

    public User updateUser(int userId, UserRequest userRequest) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        userMapper.updateUserFromRequest(userRequest, existingUser);
        return userRepository.save(existingUser);
    }

    public void deleteUser(int userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        userRepository.delete(existingUser);
    }
}

//package com.example.PandaCoffee.service;
//import com.example.PandaCoffee.dto.request.SendEmailRequest;
//import com.example.PandaCoffee.dto.request.UserRequest;
//import com.example.PandaCoffee.dto.response.To;
//import com.example.PandaCoffee.dto.response.UserResponse;
//import com.example.PandaCoffee.mapper.UserMapper;
//import com.example.PandaCoffee.model.User;
//import com.example.PandaCoffee.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.List;
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    CodeConfirmEmailService codeConfirmEmailService;
//
//    @Autowired
//    EmailService emailService;
//
//    @Autowired
//    private UserMapper userMapper;
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public User createUser(UserRequest userRequest) {
//        var user = userMapper.toUser(userRequest);
//        return userRepository.save(user);
//    }
//
//    public UserResponse addUser(UserRequest userRequest) throws IOException {
//        // Kiểm tra email đã tồn tại
//        if (userRepository.findUserByEmail(userRequest.getEmail()) != null) {
//            throw new IllegalArgumentException("Email đã tồn tại trong hệ thống.");
//        }
//
//        User user = userMapper.toUser(userRequest);
////        user.setStatus(0);
//
//        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//            emailService.sendEmail(SendEmailRequest.builder()
//                    .to(To.builder()
//                            .email(user.getEmail())
//                            .name("User")
//                            .build())
//                    .htmlContent("<p>Hello</p>" + user.getEmail())
//                    .subject("Welcome to PandaApp")
//                    .build());
//
//        var result = userRepository.save(user);
//        var confirm = codeConfirmEmailService.addCodeConfirmEmail(user);
//
//        To to = To.builder()
//                .email(userRequest.getEmail())
//                .name("New User")
//                .build();
//
//        emailService.sendEmailWhenAccountRegister(to, confirm.getMessage());
//
//        return userMapper.toUserResponse(result);
//    }
//
//    public UserResponse findUserById(int id){
//        User user = userRepository.findById(id).get();
//        return userMapper.toUserResponse(user);
//    }
//
//    public UserResponse findUserByUserName(String username){
//        User user = userRepository.findUserByEmail(username);
//        return userMapper.toUserResponse(user);
//    }
//
//    public UserResponse myInfo(){
//        var accountCurrent = SecurityContextHolder.getContext().getAuthentication();
//        return userMapper.toUserResponse(userRepository.findUserByEmail(accountCurrent.getName()));
//    }
//
//
//}
