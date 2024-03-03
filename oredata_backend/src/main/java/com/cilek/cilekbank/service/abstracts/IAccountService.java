package com.cilek.cilekbank.service.abstracts;

import com.cilek.cilekbank.dto.*;
import com.cilek.cilekbank.model.Account;

import java.util.List;

public interface IAccountService {

    CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO);

    Account getAccount(String accountNumber);

    UpdateAccountResponseDTO updateAccount(String id, UpdateAccountRequestDTO updateAccountRequestDTO);

    ResponseStatus deleteAccount(String id);

    List<Account> getAccounts(String accountNumber, String accountName);

}
