package com.example.PandaCoffee.service;

import com.example.PandaCoffee.model.Bill;
import com.example.PandaCoffee.repositories.BillRepository;
import com.example.PandaCoffee.repositories.BranchRepository;
import com.example.PandaCoffee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    public Bill createBill(Bill bill) {
        // Lấy User từ userId
        if (bill.getUser() != null) {
            var user = userRepository.findById(bill.getUser().getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + bill.getUser().getUserId()));
            bill.setUser(user);
        }

        // Lấy Branch từ branchId
        if (bill.getBranch() != null) {
            var branch = branchRepository.findById(bill.getBranch().getBranchId())
                    .orElseThrow(() -> new IllegalArgumentException("Branch not found with ID: " + bill.getBranch().getBranchId()));
            bill.setBranch(branch);
        }

        return billRepository.save(bill);
    }

    public Bill updateBill(int billId, Bill billDetails) {
        Bill existingBill = billRepository.findById(billId)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found with ID: " + billId));
        existingBill.setTotalPrice(billDetails.getTotalPrice());
        existingBill.setUser(billDetails.getUser());
        existingBill.setBranch(billDetails.getBranch());
        return billRepository.save(existingBill);
    }

    public void deleteBill(int billId) {
        Bill existingBill = billRepository.findById(billId)
                .orElseThrow(() -> new IllegalArgumentException("Bill not found with ID: " + billId));
        billRepository.delete(existingBill);
    }
}