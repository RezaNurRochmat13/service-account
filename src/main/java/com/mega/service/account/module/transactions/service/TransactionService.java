package com.mega.service.account.module.transactions.service;

import com.mega.service.account.module.transactions.dto.CreateTransactionDto;
import com.mega.service.account.module.transactions.dto.ListTransactionDto;

import java.util.List;

public interface TransactionService {
    List<ListTransactionDto> findAllActiveTransactions();
    CreateTransactionDto createNewTransaction(CreateTransactionDto createTransactionDto);
}
