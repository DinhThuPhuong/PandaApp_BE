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


    @Mapping(target = "user.userId", source = "userId")
    @Mapping(target = "branch.branchId", source = "branchId")
    Bill toBill(BillRequest BillRequest);

    // Map Bill -> BillResponse (bỏ qua trường `user.bills` và `branch.bills` để tránh vòng lặp)
    @Mapping(target = "user.bills", ignore = true)
    @Mapping(target = "branch.bills", ignore = true)
    BillResponse toBillResponse(Bill Bill);


    void updateBillFromRequest(BillRequest BillRequest, @MappingTarget Bill Bill);




}