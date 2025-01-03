package com.example.PandaCoffee.repositories;
import com.example.PandaCoffee.model.Categories;
import com.example.PandaCoffee.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    Optional<Categories> findByCategoryName(String name);
}