package com.example.PandaCoffee.mapper;

import com.example.PandaCoffee.model.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import com.example.PandaCoffee.dto.request.CategoriesRequest;

import com.example.PandaCoffee.dto.response.CategoriesResponse;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoriesMapper {


    @Mapping(target = "avatar", ignore = true)
    Categories toCategories(CategoriesRequest categoriesRequest);

    CategoriesResponse toCategoriesResponse(Categories categories);

    Categories toCategories(CategoriesResponse categoriesResponse);

    @Mapping(target = "avatar", ignore = true)
    void updateCategoriesFromRequest(CategoriesRequest categoriesRequest, @MappingTarget Categories categories);




}