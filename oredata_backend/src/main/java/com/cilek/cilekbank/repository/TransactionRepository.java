package com.cilek.cilekbank.repository;

import com.cilek.cilekbank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    public List<Transaction> findByFromAccountIdOrToAccountId(String fromAccountId, String toAccountId);
}
