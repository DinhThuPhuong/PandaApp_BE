package com.example.PandaCoffee.mapper;


import com.example.PandaCoffee.dto.request.BillRequest;
import com.example.PandaCoffee.dto.response.BillResponse;
import com.example.PandaCoffee.model.Bill;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BillMapper {



    Bill toBill(BillRequest BillRequest);


    BillResponse toBillResponse(Bill Bill);


    void updateBillFromRequest(BillRequest BillRequest, @MappingTarget Bill Bill);




}