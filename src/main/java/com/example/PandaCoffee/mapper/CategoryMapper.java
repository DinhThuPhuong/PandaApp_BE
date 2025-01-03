package com.example.PandaCoffee.mapper;
import com.example.PandaCoffee.dto.request.CategoriesRequest;
import com.example.PandaCoffee.dto.response.CategoriesResponse;
import com.example.PandaCoffee.model.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {


        @Mapping(target = "image", ignore = true)
        Categories toCategories(CategoriesRequest CategoriesRequest);


        CategoriesResponse toCategoriesResponse(Categories Categories);


        @Mapping(target = "image", ignore = true)
        void updateCategoriesFromRequest(CategoriesRequest CategoriesRequest, @MappingTarget Categories Categories);




}
