package com.example.PandaCoffee.dto.response;
import com.example.PandaCoffee.model.Image;

import lombok.Data;

import java.util.List;

@Data
public class CategoriesResponse {
    private int categoryId;
    private String categoryName;
    private Image imageUrl; // URL của hình ảnh liên kết
    private List<ProductResponse> productList; // Danh sách sản phẩm thuộc danh mục
}