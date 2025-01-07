package com.example.PandaCoffee.dto.request;

import lombok.Data;

@Data
public class DetailRequest {
    private int billId;      // ID của hóa đơn liên quan
    private int productId;   // ID của sản phẩm liên quan
    private int quantity;    // Số lượng sản phẩm
    private String size;
}