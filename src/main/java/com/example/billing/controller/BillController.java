package com.example.billing.controller;

import com.example.billing.dto.BillRequest;
import com.example.billing.dto.BillResponse;
import com.example.billing.dto.ItemResponse;
import com.example.billing.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {


    private final BillService billService;

    // GET next available bill number
    @GetMapping("/next-bill-number")
    public ResponseEntity<Integer> getNextBillNumber() {
        return ResponseEntity.ok(billService.getNextBillNumber());
    }

    // GET list of items
    @GetMapping("/items")
    public ResponseEntity<List<ItemResponse>> getItems() {
        return ResponseEntity.ok(billService.getAllItems());
    }

    // CREATE a new bill
    @PostMapping
    public ResponseEntity<BillResponse> createBill(@RequestBody BillRequest bill) {
        return ResponseEntity.ok(billService.createBill(bill));
    }

    // GET bill by ID
    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> getBillById(@PathVariable Long id) {
        return ResponseEntity.ok(billService.getBillById(id));
    }

    // (Optional) GET all bills
    @GetMapping
    public ResponseEntity<List<BillResponse>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }
}
