package com.example.PandaCoffee.mapper;

import com.example.PandaCoffee.dto.request.BranchRequest;
import com.example.PandaCoffee.dto.response.BranchResponse;
import com.example.PandaCoffee.model.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BranchMapper {

    Branch toBranch(BranchRequest branchRequest);

    BranchResponse toBranchResponse(Branch branch);

    void updateBranchFromRequest(BranchRequest branchRequest, @MappingTarget Branch branch);
}
