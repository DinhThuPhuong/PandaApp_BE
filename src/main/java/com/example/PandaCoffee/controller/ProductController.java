package com.example.PandaCoffee.controller;

import com.example.PandaCoffee.dto.request.CategoriesRequest;
import com.example.PandaCoffee.dto.request.ProductRequest;
import com.example.PandaCoffee.dto.response.ProductResponse;
import com.example.PandaCoffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@ModelAttribute ProductRequest productRequest,
                                                      @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productRequest, file));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@ModelAttribute ProductRequest productRequest,
                                                         @PathVariable("id") int productId,
                                                         @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(productId, productRequest, file));
    }

    //Cap nhat trang thai con hang
    @PutMapping("/in-stock/{id}")
    public ResponseEntity<String> updateProductStatusToInStock(@PathVariable("id") int productId) {
        boolean updated = productService.updateProductStatusToInStock(productId);
        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Product status updated to 'In Stock'.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is already 'In Stock' or does not exist.");
        }
    }

    //Truy van tat ca san pham
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    //Tim kiem san pham theo keyword

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(@RequestParam("keyword") String keyword) {
        List<ProductResponse> products = productService.findByProductName(keyword);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(products); // Trả về 404 nếu không tìm thấy
        }
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
    //Cap nhat trang thai het hang
    @PutMapping("/out-of-stock/{id}")
    public ResponseEntity<String> updateProductStatusToOutOfStock(@PathVariable("id") int productId) {
        boolean updated = productService.updateProductStatusToOutOfStock(productId);
        if (updated) {
            return ResponseEntity.status(HttpStatus.OK).body("Product status updated to 'Out of Stock'.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product is already 'Out of Stock' or does not exist.");
        }
    }

    //Truy van danh sach san pham theo the loai
    @GetMapping("getAllByCategoryId/{id}")
    public ResponseEntity<List<ProductResponse>> getAllByCategoryId(@PathVariable("id") int categoryId)
    {
        return  ResponseEntity.status(HttpStatus.OK).body(productService.findAllProductByCategoryId(categoryId));

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") int productId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(productId));
    }
}
