package com.example.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillResponse {

    private Long id;
    private Integer billNumber;
    private LocalDate billDate;
    private String customerName;
    private String mobileNumber;
    private List<ItemDetail> items;
    private Double totalAmount;

    @Data
    @AllArgsConstructor
    public static class ItemDetail {
        private String itemName;
        private Integer quantity;
        private Double rate;
        private Double amount;
    }

}
