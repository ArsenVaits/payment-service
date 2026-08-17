package com.example.project5.entity;

import com.example.project5.enums.Currency;
import com.example.project5.enums.StatusAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name="bank_accounts")
public class BankAccount extends BaseEntity {
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAccount statusAccount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
