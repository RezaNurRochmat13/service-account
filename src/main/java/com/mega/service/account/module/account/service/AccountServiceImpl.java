package com.mega.service.account.module.account.service;

import com.mega.service.account.configuration.ModelMapperConfiguration;
import com.mega.service.account.module.account.dto.CreateAccountDto;
import com.mega.service.account.module.account.dto.ListAccountDto;
import com.mega.service.account.module.account.dto.SingleAccountBalanceDto;
import com.mega.service.account.module.account.entity.Account;
import com.mega.service.account.module.account.repository.AccountRepository;
import com.mega.service.account.module.users.entity.User;
import com.mega.service.account.module.users.repository.UserRepository;
import com.mega.service.account.utils.generatorAccountNumber.GeneratorAccountNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final GeneratorAccountNumber generatorAccountNumber;
    private final ModelMapperConfiguration modelMapperConfiguration;

    @Override
    public List<ListAccountDto> findAllActiveAccounts() {
        List<Account> accounts = accountRepository.findAllActiveAccounts();
        return mapperAccountListToDto(accounts);
    }

    @Override
    public SingleAccountBalanceDto checkAccountBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account number not found with account number: " + accountNumber));
        return mapperAccountToDto(account);
    }

    @Override
    public Account createNewAccount(CreateAccountDto payload) {
        Account account = new Account();
        User user = userRepository.findById(payload.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + payload.getUserId()));
        account.setAccountNumber(generatorAccountNumber.generateAccountNumber());
        account.setBalance(500000.00);
        account.setUser(user);
        return accountRepository.save(account);
    }

    private List<ListAccountDto> mapperAccountListToDto(List<Account> accounts) {
        List<ListAccountDto> listAccountDtos = new ArrayList<>();

        for (Account account : accounts) {
            ListAccountDto listAccountDto = modelMapperConfiguration
                    .modelMapper()
                    .map(account, ListAccountDto.class);
            listAccountDtos.add(listAccountDto);
        }

        return listAccountDtos;
    }

    private SingleAccountBalanceDto mapperAccountToDto(Account account) {
        return modelMapperConfiguration
                .modelMapper()
                .map(account, SingleAccountBalanceDto.class);
    }
}
