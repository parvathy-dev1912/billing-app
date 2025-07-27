package com.example.billing.repository;

import com.example.billing.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Long> {
    Optional<Bill> findTopByOrderByBillNumberDesc();
}
