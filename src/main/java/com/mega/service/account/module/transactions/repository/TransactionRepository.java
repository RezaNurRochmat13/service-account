package com.mega.service.account.module.transactions.repository;

import com.mega.service.account.module.transactions.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.deletedAt IS NULL")
    List<Transaction> findAllActiveTransactions();
}
