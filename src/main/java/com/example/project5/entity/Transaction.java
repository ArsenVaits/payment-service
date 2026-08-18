package com.example.project5.entity;

import com.example.project5.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Transaction extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "from_account_id", nullable = false)
    private BankAccount fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id", nullable = false)
    private BankAccount toAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
}
