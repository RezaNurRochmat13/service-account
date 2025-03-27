package com.mega.service.account.module.account.presenter;

import com.mega.service.account.module.account.dto.CreateAccountDto;
import com.mega.service.account.module.account.service.AccountServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountPresenter {
    private final AccountServiceImpl accountService;

    @GetMapping("/accounts")
    public Map<String, Object> findAllActiveAccounts() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", accountService.findAllActiveAccounts());
        return response;
    }

    @GetMapping("/accounts/balance/{accountNumber}")
    public Map<String, Object> checkAccountBalance(@PathVariable String accountNumber) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", accountService.checkAccountBalance(accountNumber));
        return response;
    }

    @PostMapping("/accounts")
    public Map<String, Object> createNewAccount(@RequestBody CreateAccountDto payload) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", accountService.createNewAccount(payload));
        return response;
    }
}
