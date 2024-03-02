package com.cilek.cilekbank.service.abstracts;

import com.cilek.cilekbank.dto.CreateAccountRequestDTO;
import com.cilek.cilekbank.dto.CreateAccountResponseDTO;
import com.cilek.cilekbank.model.Account;

import java.util.List;

public interface IAccountService {

    CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO);

    Account getAccount(String accountNumber);

    Account updateAccount(String accountNumber, String accountName);

    void deleteAccount(String accountNumber);

    List<Account> getAccounts(String accountNumber, String accountName);

}
