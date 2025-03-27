package com.mega.service.account.module.transactions.presenter;

import com.mega.service.account.module.transactions.dto.CreateTransactionDto;
import com.mega.service.account.module.transactions.service.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionPresenter {
    private final TransactionServiceImpl transactionService;

    @GetMapping("/transactions")
    public Map<String, Object> findAllActiveTransactions() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", transactionService.findAllActiveTransactions());
        return response;
    }

    @PostMapping("/transactions")
    public Map<String, Object> createNewTransaction(@RequestBody CreateTransactionDto payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", transactionService.createNewTransaction(payload));
        return response;
    }
}
