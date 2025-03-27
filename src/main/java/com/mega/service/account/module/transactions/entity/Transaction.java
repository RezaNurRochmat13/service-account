package com.mega.service.account.module.transactions.entity;

import com.mega.service.account.module.account.entity.Account;
import com.mega.service.account.utils.entity.Auditing;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
