package com.example.PandaCoffee.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BillResponse {
    private int billId;
    private double totalPrice;
    private UserResponse user;     // Thông tin người dùng liên quan
    private BranchResponse branch; // Thông tin chi nhánh liên quan
    private List<DetailResponse> details;
}