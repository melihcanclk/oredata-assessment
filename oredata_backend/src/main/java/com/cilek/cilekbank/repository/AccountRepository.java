package com.cilek.cilekbank.repository;

import com.cilek.cilekbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    boolean existsByNumber(String accountNumber);

    boolean existsByName(String accountName);

}
