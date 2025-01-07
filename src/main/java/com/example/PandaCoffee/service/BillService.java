package com.example.PandaCoffee.service;

import com.example.PandaCoffee.dto.request.BillRequest;
import com.example.PandaCoffee.dto.request.DetailRequest;
import com.example.PandaCoffee.dto.response.BillResponse;
import com.example.PandaCoffee.mapper.BillMapper;
import com.example.PandaCoffee.model.Bill;
import com.example.PandaCoffee.model.Details;
import com.example.PandaCoffee.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private BillMapper billMapper;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    @Autowired
    private BranchRepository branchRepository;

    public BillResponse addBill(BillRequest billRequest) {
        // Kiểm tra nếu User tồn tại theo userId
        var user = userRepository.findById(billRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + billRequest.getUserId()));

        // Kiểm tra nếu Branch tồn tại theo branchId
        var branch = branchRepository.findById(billRequest.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("Branch not found with ID: " + billRequest.getBranchId()));

        // Tạo mới Bill từ BillRequest
        Bill bill = billMapper.toBill(billRequest);
        bill.setUser(user);
        bill.setBranch(branch);

        // Lưu Bill vào cơ sở dữ liệu trước
        bill = billRepository.save(bill);

        // Lưu danh sách Details từ BillRequest
        if (billRequest.getDetails() != null && !billRequest.getDetails().isEmpty()) {
            for (DetailRequest detailRequest : billRequest.getDetails()) {
                // Tạo đối tượng Details từ DetailRequest
                Details detail = new Details();
                detail.setBill(bill); // Gắn Bill vào Detail
                detail.setQuantity(detailRequest.getQuantity());

                // Tìm Product liên quan
                var product = productRepository.findById(detailRequest.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + detailRequest.getProductId()));
                detail.setProduct(product);

                // Lưu Detail vào cơ sở dữ liệu
                detailsRepository.save(detail);
            }
        }

        // Trả về response dạng BillResponse (bao gồm cả danh sách Details)
        return billMapper.toBillResponse(bill);
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

    public BillResponse addBillWithDetails(BillRequest billRequest) {
        // Lấy thông tin User và Branch
        var user = userRepository.findById(billRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + billRequest.getUserId()));

        var branch = branchRepository.findById(billRequest.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("Branch not found with ID: " + billRequest.getBranchId()));

        // Tạo mới Bill
        Bill bill = billMapper.toBill(billRequest);
        bill.setUser(user);
        bill.setBranch(branch);

        // Lưu Bill trước (chỉ thực hiện 1 lần)
        bill = billRepository.save(bill);

        // Danh sách Details
        List<Details> detailsList = new ArrayList<>();

        // Lưu danh sách Details
        if (billRequest.getDetails() != null && !billRequest.getDetails().isEmpty()) {
            for (DetailRequest detailRequest : billRequest.getDetails()) {
                Details detail = new Details();
                detail.setBill(bill); // Gắn Bill vào Detail
                detail.setQuantity(detailRequest.getQuantity());

                var product = productRepository.findById(detailRequest.getProductId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + detailRequest.getProductId()));
                detail.setProduct(product);

                detailsList.add(detail); // Thêm vào danh sách Details
            }
            detailsRepository.saveAll(detailsList); // Lưu danh sách Details một lần
        }

        // Gán danh sách Details vào Bill
        bill.setDetails(detailsList);

        // Lưu Bill để cập nhật danh sách Details
        bill = billRepository.save(bill);

        // Trả về BillResponse
        return billMapper.toBillResponse(bill);
    }


    //Truy van bill theo id
    public BillResponse findById(int billId)
    {
        Bill ill = billRepository.findById(billId).get();
        return billMapper.toBillResponse(ill);
    }


}