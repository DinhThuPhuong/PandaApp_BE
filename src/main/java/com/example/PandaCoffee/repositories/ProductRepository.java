package com.example.PandaCoffee.repositories;

import com.example.PandaCoffee.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductName(String productName);  // Trả về Optional để xử lý khi không tìm

    //Truy van danh sach san pham thep categoryId
    List<Product> findByCategories_CategoryId(int categoryId);
}
