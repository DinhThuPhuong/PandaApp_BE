package com.example.PandaCoffee.service;
import com.example.PandaCoffee.model.Branch;
import com.example.PandaCoffee.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch createBranch(Branch branch) {
        return branchRepository.save(branch);
    }
}