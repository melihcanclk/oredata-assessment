package com.cilek.cilekbank.service.concretes;

import com.cilek.cilekbank.dto.CreateAccountRequestDTO;
import com.cilek.cilekbank.dto.CreateAccountResponseDTO;
import com.cilek.cilekbank.dto.ResponseStatus;
import com.cilek.cilekbank.model.Account;
import com.cilek.cilekbank.model._User;
import com.cilek.cilekbank.repository.AccountRepository;
import com.cilek.cilekbank.repository.UserRepository;
import com.cilek.cilekbank.service.abstracts.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    @Override
    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO) {
        // check if the account number is already in use
        Account account = new Account();
        try {
            if (accountRepository.existsByNumber(createAccountRequestDTO.getAccountNumber())) {
                throw new RuntimeException("Account number is already in use");
            }

            // if account name is already in use
            if (accountRepository.existsByName(createAccountRequestDTO.getAccountName())) {
                throw new RuntimeException("Account name is already in use");
            }

            // check if user exists
            if (!userRepository.existsById(createAccountRequestDTO.getUserId())) {
                throw new RuntimeException("User does not exist");
            }

            _User user = userRepository.getById(createAccountRequestDTO.getUserId());

            account.setNumber(createAccountRequestDTO.getAccountNumber());
            account.setName(createAccountRequestDTO.getAccountName());
            account.setBalance(BigDecimal.valueOf(0));
            account.setUser(user);
            account.setCreatedAt(LocalDateTime.now());
            account.setUpdatedAt(LocalDateTime.now());
            accountRepository.save(account);

        } catch (RuntimeException e) {
            return new CreateAccountResponseDTO(null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    new ResponseStatus(
                            400,
                            null,
                            e.getMessage(),
                            null,
                            null
                    )
            );
        }



        return new CreateAccountResponseDTO(
                account.getId(),
                account.getNumber(),
                account.getName(),
                account.getBalance(),
                account.getUser().getId(),
                account.getUser().getUsername(),
                account.getCreatedAt(),
                account.getUpdatedAt(),
                new ResponseStatus(
                        201,
                        "Account created",
                        "Account created successfully",
                        null,
                        null
                )
        );
    }

    @Override
    public Account getAccount(String accountNumber) {
        return null;
    }

    @Override
    public Account updateAccount(String accountNumber, String accountName) {
        return null;
    }

    @Override
    public void deleteAccount(String accountNumber) {

    }

    @Override
    public List<Account> getAccounts(String accountNumber, String accountName) {
        return null;
    }
}
