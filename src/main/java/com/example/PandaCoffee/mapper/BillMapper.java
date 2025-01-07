package com.example.PandaCoffee.mapper;

import com.example.PandaCoffee.dto.request.BillRequest;
import com.example.PandaCoffee.dto.response.BillResponse;
import com.example.PandaCoffee.dto.response.DetailResponse;
import com.example.PandaCoffee.model.Bill;
import com.example.PandaCoffee.model.Details;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BillMapper {

    @Mapping(target = "user.userId", source = "userId")
    @Mapping(target = "branch.branchId", source = "branchId")
    @Mapping(target = "details", ignore = true) // Không ánh xạ ở đây, xử lý riêng
    Bill toBill(BillRequest BillRequest);

    @Mapping(target = "user.bills", ignore = true)
    @Mapping(target = "branch.bills", ignore = true)
//    @Mapping(target = "detail", source = "details") // Ánh xạ danh sách details sang detailResponse
    BillResponse toBillResponse(Bill Bill);

    void updateBillFromRequest(BillRequest BillRequest, @MappingTarget Bill Bill);


}

