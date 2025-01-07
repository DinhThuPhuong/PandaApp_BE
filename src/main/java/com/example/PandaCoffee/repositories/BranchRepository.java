package com.example.PandaCoffee.repositories;

import com.example.PandaCoffee.model.Branch;
import com.example.PandaCoffee.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Optional<Branch> findByBranchName(String name);
    List<Branch> findByBranchNameContainingIgnoreCaseOrAddressContainingIgnoreCase(String branchName, String address);
}
