package com.example.billing.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;

    private String itemName;
    private Double rate;
    private Integer quantity;
    private Double amount;


}
