package com.cilek.cilekbank.service.concretes;

import com.cilek.cilekbank.dto.*;
import com.cilek.cilekbank.model.Account;
import com.cilek.cilekbank.model._User;
import com.cilek.cilekbank.repository.AccountRepository;
import com.cilek.cilekbank.repository.UserRepository;
import com.cilek.cilekbank.service.abstracts.IAccountService;
import com.cilek.cilekbank.utils.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final JWTUtils jwtUtils;

    @Override
    public CreateAccountResponseDTO createAccount(CreateAccountRequestDTO createAccountRequestDTO, String bearerToken) {
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

            UUID userUUID = jwtUtils.getUserIdFromBearerToken(bearerToken);
            // check if user exists
            if (!userRepository.existsById(userUUID)) {
                throw new RuntimeException("User does not exist");
            }

            _User user = userRepository.getReferenceById(userUUID);

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
                account.getAccount_id(),
                account.getUser().getUserId(),
                account.getNumber(),
                account.getName(),
                account.getBalance(),
                account.getUser().getUsername(),
                account.getCreatedAt(),
                account.getUpdatedAt(),
                new ResponseStatus(
                        200,
                        "Account created",
                        "Account created successfully",
                        null,
                        null
                )
        );
    }

    @Override
    public GetAccountResponseDTO getAccountByUUIDString(String accountUUIDString, String bearerToken) {
        Account account;
        try {
            // check if user logged in is the owner of the account
            UUID userId = jwtUtils.getUserIdFromBearerToken(bearerToken);
            if (!userRepository.existsById(userId)) {
                throw new RuntimeException("User is not authenticated to access this resource");
            }

            UUID accountUUID = UUID.fromString(accountUUIDString);
            account = accountRepository.findById(accountUUID).orElseThrow(() -> new RuntimeException("Account does not exist with number: " + accountUUIDString));

            if (!account.getUser().getUserId().equals(userId)) {
                throw new RuntimeException("User is not authenticated to access this resource");
            }
        } catch (Exception e) {
            return new GetAccountResponseDTO(
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

        return new GetAccountResponseDTO(
                account.getAccount_id(),
                account.getNumber(),
                account.getName(),
                account.getUser().getUserId().toString(),
                account.getBalance(),
                account.getCreatedAt().toString(),
                account.getUpdatedAt().toString(),
                new ResponseStatus(
                        200,
                        "Account retrieved",
                        "Account retrieved successfully",
                        null,
                        null
                )
        );
    }

    @Override
    public GetAccountResponseDTO getAccountByAccountNumber(String accountNumber, String bearerToken) {
        Account account;
        try {
            // check if user logged in is the owner of the account
            UUID userId = jwtUtils.getUserIdFromBearerToken(bearerToken);
            if (!userRepository.existsById(userId)) {
                throw new RuntimeException("User is not authenticated to access this resource");
            }

            account = accountRepository.findByNumber(accountNumber);
            if (account == null) {
                throw new RuntimeException("Account does not exist with number: " + accountNumber);
            }

            if (!account.getUser().getUserId().equals(userId)) {
                throw new RuntimeException("User is not authenticated to access this resource");
            }
        } catch (Exception e) {
            return new GetAccountResponseDTO(
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

        return new GetAccountResponseDTO(
                account.getAccount_id(),
                account.getNumber(),
                account.getName(),
                account.getUser().getUserId().toString(),
                account.getBalance(),
                account.getCreatedAt().toString(),
                account.getUpdatedAt().toString(),
                new ResponseStatus(
                        200,
                        "Account retrieved",
                        "Account retrieved successfully",
                        null,
                        null
                )
        );
    }

    @Override
    public UpdateAccountResponseDTO updateAccount(String id, UpdateAccountRequestDTO updateAccountRequestDTO, String bearerToken) {
        try {
            // id is account id
            UUID accountUUID = UUID.fromString(id);
            if (!accountRepository.existsById(accountUUID)) {
                throw new RuntimeException("Account does not exist");
            }

            UUID userUUID = jwtUtils.getUserIdFromBearerToken(bearerToken);

            if (!userRepository.existsById(userUUID))
                throw new RuntimeException("User does not exist");

            Account account = accountRepository.getReferenceById(accountUUID);
            _User user = userRepository.getReferenceById(userUUID);

            account.setUser(user);
            account.setNumber(updateAccountRequestDTO.getAccountNumber());
            account.setName(updateAccountRequestDTO.getAccountName());
            if(updateAccountRequestDTO.getBalance() != null) {
                account.setBalance(updateAccountRequestDTO.getBalance());
            } else {
                account.setBalance(account.getBalance());
            }

            account.setUpdatedAt(LocalDateTime.now());
            accountRepository.save(account);

        } catch (RuntimeException e) {
            return new UpdateAccountResponseDTO(
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

        return new UpdateAccountResponseDTO(
                id,
                updateAccountRequestDTO.getAccountNumber(),
                updateAccountRequestDTO.getAccountName(),
                new ResponseStatus(
                        200,
                        "Account updated",
                        "Account updated successfully",
                        null,
                        null
                )
        );

    }

    @Override
    public ResponseStatus deleteAccount(String id, String bearerToken) {
        try {

            UUID userId = jwtUtils.getUserIdFromBearerToken(bearerToken);
            if (!userRepository.existsById(userId)) {
                throw new RuntimeException("User does not exist: " + userId);
            }

            UUID accountUUID = UUID.fromString(id);
            if (!accountRepository.existsById(accountUUID)) {
                throw new RuntimeException("Account does not exist: " + id);
            }

            Account account = accountRepository.getReferenceById(accountUUID);
            if (!account.getUser().getUserId().equals(userId)) {
                throw new RuntimeException("Account does not belong to user");
            }

            accountRepository.delete(account);

        } catch (RuntimeException e) {
            return new ResponseStatus(
                    400,
                    null,
                    e.getMessage(),
                    null,
                    null
            );
        }
        return new ResponseStatus(
                200,
                "Account deleted",
                "Account deleted successfully",
                null,
                null
        );
    }

    @Override
    public List<GetAccountResponseDTO> getAccounts(String bearerToken, String accountName, String accountNumber) {

        UUID userUUID = jwtUtils.getUserIdFromBearerToken(bearerToken);
        if (!userRepository.existsById(userUUID)) {
            throw new RuntimeException("User does not exist: " + userUUID);
        }

        if (accountName == null && accountNumber == null) {
            return accountRepository.findAllByUserUserId(userUUID).stream().map(account -> new GetAccountResponseDTO(
                    account.getAccount_id(),
                    account.getNumber(),
                    account.getName(),
                    account.getUser().getUserId().toString(),
                    account.getBalance(),
                    account.getCreatedAt().toString(),
                    account.getUpdatedAt().toString(),
                    new ResponseStatus(
                            200,
                            "Account retrieved",
                            "Account retrieved successfully",
                            null,
                            null
                    )
            )).collect(Collectors.toList());
        } else if (accountName != null && accountNumber != null) {
            return accountRepository.findAllByUserUserIdAndNameContainingAndNumberContaining(userUUID, accountName, accountNumber).stream().map(account -> new GetAccountResponseDTO(
                    account.getAccount_id(),
                    account.getNumber(),
                    account.getName(),
                    account.getUser().getUserId().toString(),
                    account.getBalance(),
                    account.getCreatedAt().toString(),
                    account.getUpdatedAt().toString(),
                    new ResponseStatus(
                            200,
                            "Account retrieved",
                            "Account retrieved successfully",
                            null,
                            null
                    )
            )).collect(Collectors.toList());

        } else if (accountName != null) {
            return accountRepository.findAllByUserUserIdAndNameContaining(userUUID, accountName).stream().map(account -> new GetAccountResponseDTO(
                    account.getAccount_id(),
                    account.getNumber(),
                    account.getName(),
                    account.getUser().getUserId().toString(),
                    account.getBalance(),
                    account.getCreatedAt().toString(),
                    account.getUpdatedAt().toString(),
                    new ResponseStatus(
                            200,
                            "Account retrieved",
                            "Account retrieved successfully",
                            null,
                            null
                    )
            )).collect(Collectors.toList());
        } else {
            return accountRepository.findAllByUserUserIdAndNumberContaining(userUUID, accountNumber).stream().map(account -> new GetAccountResponseDTO(
                    account.getAccount_id(),
                    account.getNumber(),
                    account.getName(),
                    account.getUser().getUserId().toString(),
                    account.getBalance(),
                    account.getCreatedAt().toString(),
                    account.getUpdatedAt().toString(),
                    new ResponseStatus(
                            200,
                            "Account retrieved",
                            "Account retrieved successfully",
                            null,
                            null
                    )
            )).collect(Collectors.toList());

        }
    }
}