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
@RequestMapping("/api")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private BillMapper billMapper;

    @PostMapping("/bills")
    public ResponseEntity<BillResponse> addBill(@RequestBody BillRequest billRequest) {
        Bill bill = billMapper.toBill(billRequest);
        Bill createdBill = billService.createBill(bill);
        BillResponse response = billMapper.toBillResponse(createdBill);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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

