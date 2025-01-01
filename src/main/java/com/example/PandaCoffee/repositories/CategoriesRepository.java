package com.example.PandaCoffee.repositories;
import com.example.PandaCoffee.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
}