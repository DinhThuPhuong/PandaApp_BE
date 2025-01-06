package com.example.PandaCoffee.service;

import com.example.PandaCoffee.model.User;
import com.example.PandaCoffee.repositories.ExpiredTokenRepository;
import com.example.PandaCoffee.repositories.UserRepository;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;



@Service
public class AuthService {
    private final String KEY ="Z3lodzNuNHZqa2I1ZGtnY29vYjVjZ3Qyc2Yybms5N2F1emF5eGZzNWpjNDN4MW15bmJ1M2sxZGppZHVjcGdtN2JtZnZna3dkdXVuaDNyY3hsajRraXZkd2Z6dm9iOTZlMnZuNGU2YWFiejVvcXY3ZGN1OThib2g4NW52ZnN0cnk=";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpiredTokenRepository expiredTokenRepository;

    public Boolean verifyToken(String token) throws Exception {
        JWSVerifier jwsVerifier = new MACVerifier(KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean verify = signedJWT.verify(jwsVerifier);

        if(!verify || !signedJWT.getJWTClaimsSet().getExpirationTime().after(new Date())){
            return false;
        }
        var expiredToken = expiredTokenRepository.findById(signedJWT.getJWTClaimsSet().getJWTID());
        if(expiredToken.isPresent()){
            return false;
        }
        return true;
    }

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