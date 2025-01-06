package com.example.PandaCoffee.dto.request;

import com.example.PandaCoffee.model.Images;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesRequest {
    private String categoryName;
    private Images avatar;

}
