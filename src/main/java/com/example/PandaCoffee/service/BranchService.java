package com.example.PandaCoffee.service;
import com.example.PandaCoffee.dto.request.BranchRequest;
import com.example.PandaCoffee.dto.response.BranchResponse;
import com.example.PandaCoffee.mapper.BranchMapper;
import com.example.PandaCoffee.model.Branch;
import com.example.PandaCoffee.repositories.BranchRepository;
import com.example.PandaCoffee.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ImageRepository imageRepository; // Repository cho Images

    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public BranchResponse createBranch(BranchRequest branchRequest,MultipartFile file) throws IOException {

        branchRepository.findByBranchName(branchRequest.getBranchName())
                .ifPresent(branch -> {
                    throw new RuntimeException("Branch '" + branch.getBranchName() + "' already exists.");
                });
        Branch br = branchMapper.toBranch(branchRequest);
        // Upload avatar nếu có file
        if (file != null && !file.isEmpty()) {
            br.setAvatar(cloudinaryService.uploadImage(file));
        } else {
            br.setAvatar(null);
        }
        branchRepository.save(br);
        return branchMapper.toBranchResponse(br);
    }

    public BranchResponse updateBranch(int branchId, BranchRequest branchRequest, MultipartFile file) throws IOException {

        // Tìm branch theo ID
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch with ID '" + branchId + "' not found."));

        // Kiểm tra nếu tên chi nhánh bị trùng (ngoại trừ chi nhánh hiện tại)
        branchRepository.findByBranchName(branchRequest.getBranchName())
                .ifPresent(existingBranch -> {
                    if (!Objects.equals(existingBranch.getBranchId(), branchId)) {
                        throw new RuntimeException("Branch '" + branchRequest.getBranchName() + "' already exists.");
                    }
                });
        // Cập nhật thông tin branch bằng mapper
        branchMapper.updateBranchFromRequest( branchRequest, branch);

        // Upload avatar mới nếu có file
        if (file != null && !file.isEmpty()) {
            branch.setAvatar(cloudinaryService.uploadImage(file));
        }
        // Lưu thông tin branch đã cập nhật
        branchRepository.save(branch);

        // Trả về response
        return branchMapper.toBranchResponse(branch);
    }


    public void deleteBranch(int branchId) {
        Branch existingBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new IllegalArgumentException("Branch not found with ID: " + branchId));
        branchRepository.delete(existingBranch);
    }
     public List<BranchResponse> getAllBranch()
     {
         List <Branch> list = branchRepository.findAll();
         return list.stream()
                 .map(branchMapper::toBranchResponse)
                 .collect(Collectors.toList());
     }

     //Tim kiem branch
     public List<BranchResponse> searchBranches(String keyword) {
         // Tìm kiếm chi nhánh theo tên hoặc địa chỉ (sử dụng Repository)
         List<Branch> branches = branchRepository.findByBranchNameContainingIgnoreCaseOrAddressContainingIgnoreCase(keyword, keyword);

         // Chuyển đổi danh sách Branch thành BranchResponse
         return branches.stream()
                 .map(branchMapper::toBranchResponse)
                 .collect(Collectors.toList());
     }

}
