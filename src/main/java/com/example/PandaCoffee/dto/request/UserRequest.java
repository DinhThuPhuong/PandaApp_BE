package com.example.PandaCoffee.dto.request;

import com.example.PandaCoffee.model.Images;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean status;
    private Images avatar ;
}