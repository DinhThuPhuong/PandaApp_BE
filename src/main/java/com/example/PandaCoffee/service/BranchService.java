package com.example.PandaCoffee.service;
import com.example.PandaCoffee.model.Branch;
import com.example.PandaCoffee.repositories.BranchRepository;
import com.example.PandaCoffee.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ImageRepository imageRepository; // Repository cho Images

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch createBranch(Branch branch) {
        if (branch.getAvatar() != null) {
            var avatar = imageRepository.findById(branch.getAvatar().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Image not found with ID: " + branch.getAvatar().getId()));
            branch.setAvatar(avatar);
        }
        return branchRepository.save(branch);
    }

    public Branch updateBranch(int branchId, Branch branchDetails) {
        Branch existingBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new IllegalArgumentException("Branch not found with ID: " + branchId));
        existingBranch.setBranchName(branchDetails.getBranchName());
        existingBranch.setAddress(branchDetails.getAddress());
        existingBranch.setPhoneNumber(branchDetails.getPhoneNumber());
        existingBranch.setAvatar(branchDetails.getAvatar());
        return branchRepository.save(existingBranch);
    }

    public void deleteBranch(int branchId) {
        Branch existingBranch = branchRepository.findById(branchId)
                .orElseThrow(() -> new IllegalArgumentException("Branch not found with ID: " + branchId));
        branchRepository.delete(existingBranch);
    }
}
