package com.example.PandaCoffee.controller;


import com.example.PandaCoffee.dto.request.CategoriesRequest;
import com.example.PandaCoffee.dto.response.CategoriesResponse;
import com.example.PandaCoffee.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController

@RequestMapping("/api/category")

public class CategoryController {
    @Autowired
    CategoriesService categoriesService;

    @PostMapping("/add")
    public ResponseEntity<CategoriesResponse> addCategory(@ModelAttribute CategoriesRequest categoriesRequest, // Ràng buộc trực tiếp đối tượng CompanyRequest
                                                          @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        CategoriesResponse categoriesResponse = categoriesService.addCategory(categoriesRequest,file);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriesResponse);

    }
    @PutMapping("/update/id")
    public ResponseEntity<CategoriesResponse> updateCategory (@ModelAttribute CategoriesRequest categoriesRequest,
                                                              @RequestParam(value = "file", required = false) MultipartFile file,
                                                              @PathVariable("id") int categoryId) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(categoriesService.updateCategory(categoryId,categoriesRequest,file));

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoriesResponse>> getAllCategories ()
    {
        return ResponseEntity.status(HttpStatus.OK).body(categoriesService.getAllCategories());
    }


}
