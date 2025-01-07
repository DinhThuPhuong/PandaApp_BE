package com.example.PandaCoffee.service;

import com.example.PandaCoffee.dto.request.LoginRequest;
import com.example.PandaCoffee.dto.request.TokenRequest;
import com.example.PandaCoffee.dto.response.LoginResponse;
import com.example.PandaCoffee.model.ExpiredToken;
import com.example.PandaCoffee.model.User;
import com.example.PandaCoffee.repositories.ExpiredTokenRepository;
import com.example.PandaCoffee.repositories.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;


@Service
public class AuthService {
    private final String KEY = "Z3lodzNuNHZqa2I1ZGtnY29vYjVjZ3Qyc2Yybms5N2F1emF5eGZzNWpjNDN4MW15bmJ1M2sxZGppZHVjcGdtN2JtZnZna3dkdXVuaDNyY3hsajRraXZkd2Z6dm9iOTZlMnZuNGU2YWFiejVvcXY3ZGN1OThib2g4NW52ZnN0cnk=";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpiredTokenRepository expiredTokenRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Boolean verifyToken(String token) throws Exception {
        JWSVerifier jwsVerifier = new MACVerifier(KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        boolean verify = signedJWT.verify(jwsVerifier);

        if (!verify || !signedJWT.getJWTClaimsSet().getExpirationTime().after(new Date())) {
            return false;
        }
        var expiredToken = expiredTokenRepository.findById(signedJWT.getJWTClaimsSet().getJWTID());
        if (expiredToken.isPresent()) {
            return false;
        }
        return true;
    }

    public String login(LoginRequest loginRequest) throws Exception {
        User account = userRepository.findUserByEmail(loginRequest.getEmail());
        if (account == null) {
            throw new Exception("Account exits");
        }


        var check = passwordEncoder.matches(loginRequest.getPassword(), account.getPassword());
        System.out.println(check);
        if (!check) {
            throw new Exception("Password wrong");
        }

        return generateToken(account);
    }

    public Boolean logout(TokenRequest tokenRequest) throws Exception {
        var verify = verifyToken(tokenRequest.getToken());
        if (!verify) {
            return false;
        }
        SignedJWT signedJWT = SignedJWT.parse(tokenRequest.getToken());
        ExpiredToken expiredToken = ExpiredToken.builder()
                .id(signedJWT.getJWTClaimsSet().getJWTID())
                .expired(signedJWT.getJWTClaimsSet().getExpirationTime())
                .build();
        expiredTokenRepository.save(expiredToken);
        return true;
    }

    private String generateToken(User user) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer("hoang")
                .subject(user.getEmail())
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(3, ChronoUnit.HOURS).toEpochMilli()
                        //                      Instant.now().plus(10, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("refreshToken", new Date(
                        Instant.now().plus(4, ChronoUnit.HOURS).toEpochMilli()
//                       Instant.now().plus(20, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        jwsObject.sign(new MACSigner(KEY.getBytes()));
        return jwsObject.serialize();
    }
    public LoginResponse loginWithDetails(LoginRequest loginRequest) throws Exception {
        // Kiểm tra tài khoản có tồn tại không
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new Exception("Account does not exist");
        }

        // Kiểm tra trạng thái của tài khoản
        if (!user.isStatus()) {
            throw new Exception("Account is not verified");
        }

        // Kiểm tra mật khẩu
        boolean checkPassword = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!checkPassword) {
            throw new Exception("Invalid password");
        }

        // Tạo JWT token
        String token = generateToken(user);

        // Trả về LoginResponse
        return LoginResponse.builder()
                .token(token)
                .accountId(user.getUserId())
                .email(user.getEmail())
                .build();
    }


    public String refreshToken(TokenRequest tokenRequest) throws Exception {
        JWSVerifier jwsVerifier = new MACVerifier(KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(tokenRequest.getToken());
        boolean verify = signedJWT.verify(jwsVerifier);



        if(!verify ){
            throw new Exception("Token wrong");
        }
        if(!signedJWT.getJWTClaimsSet().getExpirationTime().before(new Date())){
            throw new Exception("Token can con hieu luc");
        }

        var token = expiredTokenRepository.findById(signedJWT.getJWTClaimsSet().getJWTID());
        if(token.isPresent()){
            throw  new Exception("Token da logout");
        }

        ExpiredToken expiredToken = ExpiredToken.builder()
                .id(signedJWT.getJWTClaimsSet().getJWTID())
                .expired(signedJWT.getJWTClaimsSet().getExpirationTime())
                .build();
        expiredTokenRepository.save(expiredToken);

        var name = signedJWT.getJWTClaimsSet().getSubject();
        System.out.println();
        var account = userRepository.findUserByEmail(name);

        return   generateToken(account);
    }



}