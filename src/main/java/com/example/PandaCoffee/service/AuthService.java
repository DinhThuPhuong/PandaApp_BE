package com.example.PandaCoffee.service;

import com.example.PandaCoffee.model.User;
import com.example.PandaCoffee.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

//    public LoginResponse loginWithDetails(LoginRequest loginRequest) throws Exception {
//        User user = userRepository.findByEmail(loginRequest.getEmail());
//        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
//            // Generate token or any additional login logic here
//            return new LoginResponse("Login successful", user.getName(), "token-example");
//        } else {
//            throw new Exception("Invalid credentials");
//        }
//    }
//
//    public boolean logout(TokenRequest tokenRequest) {
//        // Logic for logout (e.g., invalidate token)
//        return true;
//    }
//
//    public String refreshToken(TokenRequest tokenRequest) {
//        // Logic for refreshing token
//        return "new-token-example";
//    }
//
//    public boolean verifyToken(String token) {
//        // Logic for verifying token
//        return true;
//    }
}