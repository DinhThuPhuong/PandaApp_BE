package com.example.PandaCoffee.dto.response;

import com.example.PandaCoffee.model.Images;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private int productId;
    private String productName;
    private String description;
    private double sizeS;
    private double sizeM;
    private double sizeL;
    private Images avatar;
    private int status;
    private String categoryName; // Thay vì trả về toàn bộ Categories, chỉ trả tên category
}