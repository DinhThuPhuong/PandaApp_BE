package com.example.PandaCoffee.service;

import com.example.PandaCoffee.model.Bill;
import com.example.PandaCoffee.repositories.BillRepository;
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

    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }
}