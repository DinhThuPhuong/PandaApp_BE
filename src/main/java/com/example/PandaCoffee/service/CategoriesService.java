package com.example.PandaCoffee.service;
import com.example.PandaCoffee.model.Categories;
import com.example.PandaCoffee.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Categories createCategory(Categories category) {
        return categoriesRepository.save(category);
    }
}