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

@RestController
@RequestMapping("/api")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchMapper branchMapper;  // Inject BranchMapper

    @PostMapping("/branches")
    public ResponseEntity<BranchResponse> addBranch(@RequestBody BranchRequest branchRequest) {
        Branch branch = branchMapper.toBranch(branchRequest);  // Use the injected branchMapper
        Branch createdBranch = branchService.createBranch(branch);
        BranchResponse response = branchMapper.toBranchResponse(createdBranch);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/branches/{branchId}")
    public ResponseEntity<BranchResponse> updateBranch(
            @PathVariable int branchId,
            @RequestBody BranchRequest branchRequest) {
        Branch branch = branchMapper.toBranch(branchRequest);  // Use the injected branchMapper
        Branch updatedBranch = branchService.updateBranch(branchId, branch);
        BranchResponse response = branchMapper.toBranchResponse(updatedBranch);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/branches/{branchId}")
    public ResponseEntity<Void> deleteBranch(@PathVariable int branchId) {
        branchService.deleteBranch(branchId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

