package com.example.PandaCoffee.mapper;

import com.example.PandaCoffee.dto.request.UserRequest;
import com.example.PandaCoffee.dto.response.UserResponse;
import com.example.PandaCoffee.model.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {


    @Mapping(target = "image", ignore = true)
    User toUser(UserRequest userRequest);


    UserResponse toUserResponse(User user);


    @Mapping(target = "image", ignore = true)
    User updateUserFromRequest(UserRequest userRequest, @MappingTarget User user);




}