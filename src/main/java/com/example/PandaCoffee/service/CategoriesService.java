package com.example.PandaCoffee.service;
import com.example.PandaCoffee.dto.request.CategoriesRequest;
import com.example.PandaCoffee.dto.response.CategoriesResponse;
import com.example.PandaCoffee.mapper.CategoryMapper;
import com.example.PandaCoffee.model.Categories;
import com.example.PandaCoffee.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private CategoryMapper categoryMapper;


    @Autowired
    CloudinaryService cloudinaryService;


    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Categories createCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    //Them category
    public CategoriesResponse addCategory(CategoriesRequest categoriesRequest, MultipartFile file) throws IOException {

        System.out.println(categoriesRequest);
        var categories  = categoriesRepository.findByCategoryName(categoriesRequest.getCategoryName());
        if(categories!=null)
        {
            throw new RuntimeException("Category '" + categories.get().getCategoryName() + "' already exists.");
        }
        Categories cate = categoryMapper.toCategories(categoriesRequest);
        if(file != null && !file.isEmpty()){
            cate.setAvatar(cloudinaryService.uploadImage(file));
        }else {
            cate.setAvatar(null);
        }
        categoriesRepository.save(cate);
        return categoryMapper.toCategoriesResponse(cate);
    }
    //Chinh sua category
    public CategoriesResponse updateCategory( int cateId,CategoriesRequest categoriesRequest, MultipartFile file) throws IOException
    {
        //Kiem tra ton tai
        var category = categoriesRepository.findById(cateId).orElseThrow(()-> new IllegalArgumentException("Category not found"));

        System.out.println(categoriesRequest);

        //Kiem tra trung
        var categories  = categoriesRepository.findByCategoryName(categoriesRequest.getCategoryName());
        if(categories!=null)
        {
            throw new RuntimeException("Category '" + categories.get().getCategoryName() + "' already exists.");
        }

        categoryMapper.updateCategoriesFromRequest(categoriesRequest,category);

        //Kiem tra hiinh anh
        if(file != null && !file.isEmpty()){
            category.setAvatar(cloudinaryService.uploadImage(file));
        }else {
            category.setAvatar(null);
        }

        //Luu thong tin
        categoriesRepository.save(category);

        //tra ve thong tin category sau khi thuc hien cap nhat thong tin
        return  categoryMapper.toCategoriesResponse(category);

    }

}