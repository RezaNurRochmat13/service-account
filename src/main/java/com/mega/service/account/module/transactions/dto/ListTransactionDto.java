package com.mega.service.account.module.transactions.dto;

import com.mega.service.account.module.account.dto.SingleAccountBalanceDto;
import com.mega.service.account.utils.entity.Auditing;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListTransactionDto extends Auditing {
    private Long id;
    private String transactionType;
    private String transactionStatus;
    private LocalDateTime transactionDate;
    private Double amount;
    private SingleAccountBalanceDto account;
}
