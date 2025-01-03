package com.example.PandaCoffee.dto.request;

import com.example.PandaCoffee.model.Image;
import lombok.Data;

@Data
public class BranchRequest {
    private String branchName;
    private String address;
    private String phoneNumber;
    private Image image; // ID của ảnh đại diện chi nhánh
}