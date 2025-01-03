package com.example.PandaCoffee.dto.request;

import com.example.PandaCoffee.model.Images;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String description;
    private double sizeS;
    private double sizeM;
    private double sizeL;
    private Images avatar;
    private int categoryId; // Thay vì đối tượng Categories, ta chỉ cần ID của category
}
