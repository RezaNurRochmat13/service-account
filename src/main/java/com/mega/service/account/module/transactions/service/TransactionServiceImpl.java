package com.mega.service.account.module.transactions.service;

import com.mega.service.account.configuration.ModelMapperConfiguration;
import com.mega.service.account.module.account.entity.Account;
import com.mega.service.account.module.account.repository.AccountRepository;
import com.mega.service.account.module.transactions.dto.CreateTransactionDto;
import com.mega.service.account.module.transactions.dto.ListTransactionDto;
import com.mega.service.account.module.transactions.entity.Transaction;
import com.mega.service.account.module.transactions.repository.TransactionRepository;
import com.mega.service.account.utils.exception.InsufficientBalance;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ModelMapperConfiguration modelMapperConfiguration;


    @Override
    public List<ListTransactionDto> findAllActiveTransactions() {
        List<Transaction> transactions = transactionRepository.findAllActiveTransactions();
        return mapperListTransactionDto(transactions);
    }

    @Override
    public CreateTransactionDto createNewTransaction(CreateTransactionDto createTransactionDto) {
        Account account = accountRepository.findById(createTransactionDto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + createTransactionDto.getAccountId()));
        Transaction transaction = new Transaction();

        if (account.getBalance() < createTransactionDto.getAmount()) {
            transaction.setTransactionStatus("FAILED");
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setAmount(createTransactionDto.getAmount());
            transaction.setAccount(account);
            throw new InsufficientBalance("Insufficient balance");
        }

        transaction.setTransactionType(createTransactionDto.getTransactionType());
        transaction.setTransactionStatus("SUCCESS");
        account.setBalance(account.getBalance() - createTransactionDto.getAmount());
        transaction.setAmount(createTransactionDto.getAmount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAccount(account);
        transaction = transactionRepository.save(transaction);
        return modelMapperConfiguration.modelMapper().map(transaction, CreateTransactionDto.class);
    }

    private List<ListTransactionDto> mapperListTransactionDto(List<Transaction> transactions) {
        List<ListTransactionDto> listTransactionDtos = new ArrayList<>();
        for (Transaction transaction : transactions) {
            ListTransactionDto listTransactionDto = modelMapperConfiguration
                    .modelMapper()
                    .map(transaction, ListTransactionDto.class);
            listTransactionDtos.add(listTransactionDto);
        }

        return listTransactionDtos;
    }
}
