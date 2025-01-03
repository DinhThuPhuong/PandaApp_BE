package com.example.PandaCoffee.dto.request;
import com.example.PandaCoffee.model.Image;

import lombok.Data;



@Data
public class ProductRequest {
    private String productName;
    private String description;
    private double sizeS;
    private double sizeM;
    private double sizeL;
    private Image avatar;
    private int categoryId; // Thay vì đối tượng Categories, ta chỉ cần ID của category
}
