package com.example.PandaCoffee.dto.request;

import com.example.PandaCoffee.model.Image;
import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean status;
    private Image image;
}