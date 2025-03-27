package com.mega.service.account.module.transactions.dto;

import com.mega.service.account.utils.entity.Auditing;
import lombok.Data;

@Data
public class CreateTransactionDto extends Auditing {
    private Long accountId;
    private String transactionType;
    private Double amount;
}
