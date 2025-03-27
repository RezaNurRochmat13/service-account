package com.mega.service.account.module.account.repository;

import com.mega.service.account.module.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.deletedAt IS NULL")
    List<Account> findAllActiveAccounts();

    Optional<Account> findByAccountNumber(String accountNumber);
}
