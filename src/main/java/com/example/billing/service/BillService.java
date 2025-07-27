package com.example.billing.service;

import com.example.billing.dto.BillItemRequest;
import com.example.billing.dto.BillRequest;
import com.example.billing.dto.BillResponse;
import com.example.billing.dto.ItemResponse;
import com.example.billing.model.Bill;
import com.example.billing.model.BillItem;
import com.example.billing.model.Item;
import com.example.billing.repository.BillRepository;
import com.example.billing.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository billRepo;
    private final ItemRepository itemRepo;

    // Get next bill number (max + 1)
    public int getNextBillNumber() {
        return billRepo.findTopByOrderByBillNumberDesc()
                .map(b -> b.getBillNumber() + 1)
                .orElse(1);
    }

    // Get all items
    public List<ItemResponse> getAllItems() {
        return itemRepo.findAll().stream()
                .map(i -> new ItemResponse(i.getId(), i.getName(), i.getRate()))
                .collect(Collectors.toList());
    }

    // Create a new bill
    public BillResponse createBill(BillRequest req) {
        Bill bill = new Bill();
        bill.setBillNumber(getNextBillNumber());
        bill.setBillDate(LocalDate.now());
        bill.setCustomerName(req.getCustomerName());
        bill.setMobileNumber(req.getMobileNumber());

        List<BillItem> billItems = new ArrayList<>();
        double total = 0;

        for (BillItemRequest itemReq : req.getItems()) {
            Item item = itemRepo.findById(itemReq.getItemId())
                    .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + itemReq.getItemId()));

            BillItem billItem = new BillItem();
            billItem.setItem(item);
            billItem.setItemName(item.getName()); // optional: useful if you have this field
            billItem.setQuantity(itemReq.getQuantity());
            billItem.setRate(item.getRate());
            double amount = item.getRate() * itemReq.getQuantity();
            billItem.setAmount(amount);
            billItem.setBill(bill); // set parent bill

            billItems.add(billItem);
            total += amount;
        }

        bill.setItems(billItems);
        bill.setTotalAmount(total);

        Bill savedBill = billRepo.save(bill);

        return mapToResponse(savedBill);
    }

    // Get bill by ID
    public BillResponse getBillById(Long id) {
        Bill bill = billRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bill not found with id: " + id));
        return mapToResponse(bill);
    }

    // (Optional) Get all bills
    public List<BillResponse> getAllBills() {
        return billRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Utility: map Bill to BillResponse
    private BillResponse mapToResponse(Bill bill) {
        List<BillResponse.ItemDetail> itemDetails = bill.getItems().stream()
                .map(bi -> new BillResponse.ItemDetail(
                        bi.getItemName() != null ? bi.getItemName() : bi.getItem().getName(),
                        bi.getQuantity(),
                        bi.getRate(),
                        bi.getAmount()
                ))
                .collect(Collectors.toList());

        return new BillResponse(
                bill.getId(),
                bill.getBillNumber(),
                bill.getBillDate(),
                bill.getCustomerName(),
                bill.getMobileNumber(),
                itemDetails,
                bill.getTotalAmount()
        );
    }
}
