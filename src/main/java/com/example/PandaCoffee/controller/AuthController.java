package com.example.PandaCoffee.controller;


import com.example.PandaCoffee.dto.request.LoginRequest;
import com.example.PandaCoffee.dto.request.TokenRequest;
import com.example.PandaCoffee.dto.response.LoginResponse;
import com.example.PandaCoffee.service.AuthService;
import com.example.PandaCoffee.service.CodeConfirmEmailService;
import com.mysql.cj.util.DnsSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*") // Cho phép tất cả nguồn

public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    CodeConfirmEmailService codeConfirmEmailService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Login request received: " + loginRequest);
            LoginResponse response = authService.loginWithDetails(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logOut(@RequestBody TokenRequest tokenRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( authService.logout(tokenRequest));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<String> refreshToken(@RequestBody TokenRequest tokenRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body( authService.refreshToken(tokenRequest));
    }



    @PostMapping("/verify")
    public ResponseEntity<Boolean> verify(@RequestBody TokenRequest tokenRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(authService.verifyToken(tokenRequest.getToken()));
    }

    @GetMapping("/confirmEmail")
    public ResponseEntity<Boolean> confirmEmail(@RequestParam("message") String message) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(codeConfirmEmailService.confirmEmail(message));
    }


}