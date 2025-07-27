package com.example.billing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillRequest {

    private String customerName;
    private String mobileNumber;
    private List<BillItemRequest> items;

}
