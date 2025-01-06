package com.example.PandaCoffee.service;

import com.example.PandaCoffee.mapper.UserMapper;
import com.example.PandaCoffee.model.CodeConfirmEmail;
import com.example.PandaCoffee.model.User;
import com.example.PandaCoffee.repositories.CodeConfirmEmailRepository;
import com.example.PandaCoffee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CodeConfirmEmailService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    @Autowired
    CodeConfirmEmailRepository codeConfirmEmailRepository;

    public String genMessage(){
        return UUID.randomUUID().toString();
    }

    public CodeConfirmEmail addCodeConfirmEmail(User user){
        CodeConfirmEmail codeConfirmEmail = CodeConfirmEmail.builder()
                .message(genMessage())
                .user(user)
                .expiryDate(LocalDateTime.now().plusHours(1))
                .build();
        System.out.println(codeConfirmEmail);
        return codeConfirmEmailRepository.save(codeConfirmEmail);
    }

    public Boolean confirmEmail(String message){
        var confirm = codeConfirmEmailRepository.findACodeByToken(message);
        System.out.println(confirm);
        if(confirm == null || confirm.getExpiryDate().isBefore(LocalDateTime.now())){
            return false;
        }

        User user = confirm.getUser();
//        user.setStatus(1);
        userRepository.save(user);

        codeConfirmEmailRepository.delete(confirm);

        return true;
    }

}
