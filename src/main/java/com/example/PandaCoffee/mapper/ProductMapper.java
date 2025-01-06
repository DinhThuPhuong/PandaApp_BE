package com.example.PandaCoffee.mapper;


import com.example.PandaCoffee.dto.request.ProductRequest;
import com.example.PandaCoffee.dto.response.ProductResponse;
import com.example.PandaCoffee.model.Product;
import com.example.PandaCoffee.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    @Mapping(target = "categories", ignore = true) // Bỏ qua thuộc tính categories
    @Mapping(target = "avatar", ignore = true)
    Product toProduct(ProductRequest productRequest);

    @Mapping(target = "categoryName", source = "categories.categoryName") // Ánh xạ từ categories sang categoryName
    ProductResponse toProductResponse(Product product);

    @Mapping(target = "categories", ignore = true) // Bỏ qua thuộc tính categories
    @Mapping(target = "avatar", ignore = true)
    void updateProductFromRequest(ProductRequest productRequest, @MappingTarget Product product);
}
