package com.mega.service.account.module.account.service;

import com.mega.service.account.module.account.dto.CreateAccountDto;
import com.mega.service.account.module.account.dto.ListAccountDto;
import com.mega.service.account.module.account.dto.SingleAccountBalanceDto;
import com.mega.service.account.module.account.entity.Account;

import java.util.List;

public interface AccountService {
    List<ListAccountDto> findAllActiveAccounts();
    SingleAccountBalanceDto checkAccountBalance(String accountNumber);
    Account createNewAccount(CreateAccountDto payload);
}
