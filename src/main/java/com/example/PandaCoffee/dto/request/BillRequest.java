package com.example.PandaCoffee.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class BillRequest {
    private double totalPrice;
    private int userId;         // ID của người dùng
    private int branchId;       // ID của chi nhánh
    private List<DetailRequest> details; // Danh sách chi tiết hóa đơn
}
