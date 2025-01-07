package com.example.PandaCoffee.dto.response;

import com.example.PandaCoffee.dto.response.BillResponse;
import com.example.PandaCoffee.dto.response.ProductResponse;
import lombok.Data;

@Data
public class DetailResponse {
    private int detailId;         // ID của chi tiết hóa đơn
    private int billId;    // Thông tin hóa đơn liên quan
    private ProductResponse product; // Thông tin sản phẩm liên quan
    private int quantity;         // Số lượng sản phẩm
    private String size;
}
