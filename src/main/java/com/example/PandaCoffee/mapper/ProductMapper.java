package com.example.PandaCoffee.mapper;


import com.example.PandaCoffee.dto.request.ProductRequest;
import com.example.PandaCoffee.dto.response.ProductResponse;
import com.example.PandaCoffee.model.Product;
import com.example.PandaCoffee.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {


    @Mapping(target = "image", ignore = true)
    Product toProduct(ProductRequest productRequest);


    ProductResponse toProductResponse(Product Product);


    @Mapping(target = "image", ignore = true)
    void updateProductFromRequest(ProductRequest ProductRequest, @MappingTarget Product Product);




}