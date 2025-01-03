package com.example.PandaCoffee.dto.response;

import com.example.PandaCoffee.model.Images;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesResponse {
    private int categoryId;
    private String categoryName;
    private Images avatar; // URL của hình ảnh liên kết
    private List<ProductResponse> productList; // Danh sách sản phẩm thuộc danh mục
}