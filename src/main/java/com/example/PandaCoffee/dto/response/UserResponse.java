package com.example.PandaCoffee.dto.response;

import com.example.PandaCoffee.model.Images;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int userId;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean status;

    private Images avatar;
    private List<BillResponse> bills; // Danh sách hóa đơn của người dùng
}