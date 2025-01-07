package com.example.PandaCoffee.controller;

import com.example.PandaCoffee.dto.request.BranchRequest;
import com.example.PandaCoffee.dto.response.BranchResponse;
import com.example.PandaCoffee.mapper.BranchMapper;
import com.example.PandaCoffee.model.Branch;
import com.example.PandaCoffee.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchMapper branchMapper;  // Inject BranchMapper

    @PostMapping("/add")
    public ResponseEntity<BranchResponse> addBranch(@ModelAttribute BranchRequest branchRequest,
                                                    @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        // Gọi service để xử lý việc thêm chi nhánh
        BranchResponse branchResponse = branchService.createBranch(branchRequest, file);

        // Trả về kết quả với HTTP Status 201 (CREATED)
        return ResponseEntity.status(HttpStatus.CREATED).body(branchResponse);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<BranchResponse> updateBranch(@ModelAttribute BranchRequest branchRequest,
                                                       @RequestParam(value = "file", required = false) MultipartFile file,
                                                       @PathVariable("id") int branchId) throws Exception {
        // Gọi service để cập nhật thông tin chi nhánh
        BranchResponse branchResponse = branchService.updateBranch(branchId, branchRequest, file);

        // Trả về kết quả với HTTP Status 200 (OK)
        return ResponseEntity.status(HttpStatus.OK).body(branchResponse);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BranchResponse>> getAll ()
    {
        return ResponseEntity.status(HttpStatus.OK).body(branchService.getAllBranch());
    }

    @DeleteMapping("/branches/{branchId}")
    public ResponseEntity<Void> deleteBranch(@PathVariable int branchId) {
        branchService.deleteBranch(branchId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Tim kiem
    @GetMapping("/search")
    public ResponseEntity<List<BranchResponse>> searchBranches(@RequestParam("keyword") String keyword) {
        // Gọi service để tìm kiếm chi nhánh
        List<BranchResponse> branchResponses = branchService.searchBranches(keyword);

        // Trả về kết quả
        return ResponseEntity.status(HttpStatus.OK).body(branchResponses);
    }

}

