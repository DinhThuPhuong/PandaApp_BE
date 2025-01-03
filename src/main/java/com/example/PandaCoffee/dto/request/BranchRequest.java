package com.example.PandaCoffee.dto.request;

import com.example.PandaCoffee.model.Images;
import lombok.Data;

@Data
public class BranchRequest {
    private String branchName;
    private String address;
    private String phoneNumber;
    private Images avatar; // ID của ảnh đại diện chi nhánh
}