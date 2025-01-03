package com.example.PandaCoffee.dto.response;

import com.example.PandaCoffee.model.Image;
import lombok.Data;

import java.util.List;

@Data
public class BranchResponse {
    private int branchId;
    private String branchName;
    private String address;
    private String phoneNumber;
    private Image image;      // Thông tin ảnh đại diện
    private List<BillResponse> bills; // Danh sách hóa đơn liên quan
}