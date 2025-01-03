package com.example.PandaCoffee.dto.response;

import com.example.PandaCoffee.model.Image;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private int userId;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean status;

    private Image image;
    private List<BillResponse> bills; // Danh sách hóa đơn của người dùng
}