package com.example.PandaCoffee.controller;
import com.example.PandaCoffee.dto.request.BillRequest;
import com.example.PandaCoffee.dto.response.BillResponse;
import com.example.PandaCoffee.mapper.BillMapper;
import com.example.PandaCoffee.model.Bill;
import com.example.PandaCoffee.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillMapper billMapper;

    @PostMapping("/add")
    public ResponseEntity<BillResponse> addBill(@RequestBody BillRequest billRequest) {
        // Xử lý việc tạo Bill và gắn danh sách Details vào Bill trong service
        BillResponse response = billService.addBillWithDetails(billRequest);

        // Trả về BillResponse với danh sách Details
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> getById (@PathVariable int id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(billService.findById(id));
    }

    @PutMapping("/bills/{billId}")
    public ResponseEntity<BillResponse> updateBill(
            @PathVariable int billId,
            @RequestBody BillRequest billRequest) {
        Bill updatedDetails = billMapper.toBill(billRequest);
        Bill updatedBill = billService.updateBill(billId, updatedDetails);
        BillResponse response = billMapper.toBillResponse(updatedBill);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<Void> deleteBill(@PathVariable int billId) {
        billService.deleteBill(billId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

