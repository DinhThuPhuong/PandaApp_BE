package com.example.PandaCoffee.dto.request;

import lombok.Data;

@Data
public class BillRequest {
    private double totalPrice;
    private int userId;   // ID của người dùng
    private int branchId; // ID của chi nhánh
}
