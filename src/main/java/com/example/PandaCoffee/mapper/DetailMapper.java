package com.example.PandaCoffee.mapper;

import com.example.PandaCoffee.dto.request.DetailRequest;
import com.example.PandaCoffee.dto.response.DetailResponse;
import com.example.PandaCoffee.model.Details;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DetailMapper {

    // Ánh xạ từ DetailRequest -> Details (model)
    @Mapping(target = "bill.billId", source = "billId")
    @Mapping(target = "product.productId", source = "productId")
    Details toDetails(DetailRequest detailRequest);

    // Ánh xạ từ Details (model) -> DetailResponse
    @Mapping(target = "billId", source = "details.bill.billId")
    @Mapping(target = "product", source = "product")
    DetailResponse toDetailResponse(Details details);

    // Cập nhật chi tiết hóa đơn từ DetailRequest -> Details (để cập nhật thông tin)
    @Mapping(target = "bill.billId", source = "billId")
    @Mapping(target = "product.productId", source = "productId")
    void updateDetailsFromRequest(DetailRequest detailRequest, @MappingTarget Details details);
}
