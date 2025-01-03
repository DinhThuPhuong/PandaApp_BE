package com.example.PandaCoffee.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private int productId;
    private String productName;
    private String description;
    private double sizeS;
    private double sizeM;
    private double sizeL;

    private String categoryName; // Thay vì trả về toàn bộ Categories, chỉ trả tên category
}