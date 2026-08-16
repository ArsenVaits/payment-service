package com.example.project5.entity;

import com.example.project5.enums.Currency;
import com.example.project5.enums.StatusAccount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class BankAccount extends BaseEntity {
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    private Currency currency;
    @Column(nullable = false)
    private StatusAccount statusAccount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
