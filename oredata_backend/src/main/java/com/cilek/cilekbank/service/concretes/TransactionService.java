package com.cilek.cilekbank.service.concretes;

import com.cilek.cilekbank.dto.*;
import com.cilek.cilekbank.model.Transaction;
import com.cilek.cilekbank.repository.TransactionRepository;
import com.cilek.cilekbank.service.abstracts.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TransactionService implements ITransactionService {

    private final AccountService accountService;

    private final TransactionRepository transactionRepository;

    // Isolation level: READ_COMMITTED means that a transaction can only read data that has been committed.
    // Propagation: REQUIRED means that the transaction will be run in the context of the calling transaction.
    // RollbackFor: Exception means that the transaction will be rolled back if an exception is thrown.
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, isolation= Isolation.READ_COMMITTED)
    public TransactionTransferResponseDTO transfer(String bearerToken, TransactionTransferRequestDTO transactionTransferRequestDTO) {
        // check if the fromAccountNumber and toAccountNumber are valid
        Transaction transaction = new Transaction();
        try {

            String fromAccountNumber = transactionTransferRequestDTO.getFromAccountNumber();
            String toAccountNumber = transactionTransferRequestDTO.getToAccountNumber();

            if (fromAccountNumber == null || toAccountNumber == null) {

                transaction.setStatus(TransactionStatus.INVALID_ACCOUNT);
                transaction.setFromAccountId(fromAccountNumber);
                transaction.setToAccount(toAccountNumber);
                transaction.setAmount(transactionTransferRequestDTO.getAmount());
                transaction.setDescription(transactionTransferRequestDTO.getDescription());
                transactionRepository.save(transaction);

                return new TransactionTransferResponseDTO(
                        transaction.getId(),
                        transactionTransferRequestDTO.getFromAccountNumber(),
                        transactionTransferRequestDTO.getToAccountNumber(),
                        transactionTransferRequestDTO.getAmount(),
                        transactionTransferRequestDTO.getDescription(),
                        LocalDateTime.now().toString(),
                        TransactionStatus.INVALID_ACCOUNT,
                        new ResponseStatus(
                                404,
                                null,
                                "Invalid fromAccountNumber or toAccountNumber",
                                null,
                                null
                        ));
            }

            GetAccountResponseDTO fromAccount = accountService.getAccountByAccountNumber(fromAccountNumber, bearerToken);
            GetAccountResponseDTO toAccount = accountService.getAccountByAccountNumber(toAccountNumber, bearerToken);

            if (transactionTransferRequestDTO.getAmount().compareTo(BigDecimal.valueOf(0)) <= 0) {

                transaction.setStatus(TransactionStatus.INVALID_AMOUNT);
                transaction.setFromAccountId(fromAccountNumber);
                transaction.setToAccount(toAccountNumber);
                transaction.setAmount(transactionTransferRequestDTO.getAmount());
                transaction.setDescription(transactionTransferRequestDTO.getDescription());
                transactionRepository.save(transaction);

                return new TransactionTransferResponseDTO(
                        transaction.getId(),
                        transactionTransferRequestDTO.getFromAccountNumber(),
                        transactionTransferRequestDTO.getToAccountNumber(),
                        transactionTransferRequestDTO.getAmount(),
                        transactionTransferRequestDTO.getDescription(),
                        LocalDateTime.now().toString(),
                        TransactionStatus.INVALID_AMOUNT,
                        new ResponseStatus(
                                400,
                                null,
                                "Invalid amount",
                                null,
                                null
                        ));
            }

            if (fromAccount.getBalance().compareTo(transactionTransferRequestDTO.getAmount()) < 0) {

                transaction.setStatus(TransactionStatus.INSUFFICIENT_BALANCE);
                transaction.setFromAccountId(fromAccountNumber);
                transaction.setToAccount(toAccountNumber);
                transaction.setAmount(transactionTransferRequestDTO.getAmount());
                transaction.setDescription(transactionTransferRequestDTO.getDescription());
                transaction.setTransactionDate(LocalDateTime.now());
                transactionRepository.save(transaction);

                return new TransactionTransferResponseDTO(
                        transaction.getId(),
                        transactionTransferRequestDTO.getFromAccountNumber(),
                        transactionTransferRequestDTO.getToAccountNumber(),
                        transactionTransferRequestDTO.getAmount(),
                        transactionTransferRequestDTO.getDescription(),
                        LocalDateTime.now().toString(),
                        TransactionStatus.INSUFFICIENT_BALANCE,
                        new ResponseStatus(
                                400,
                                null,
                                "Insufficient balance",
                                null,
                                null
                        ));
            }

            transaction.setFromAccountId(fromAccountNumber);
            transaction.setToAccount(toAccountNumber);
            transaction.setAmount(transactionTransferRequestDTO.getAmount());
            transaction.setDescription(transactionTransferRequestDTO.getDescription());
            transaction.setStatus(TransactionStatus.SUCCESS);
            transaction.setTransactionDate(LocalDateTime.now());

            fromAccount.setBalance(fromAccount.getBalance().subtract(transactionTransferRequestDTO.getAmount()));
            toAccount.setBalance(toAccount.getBalance().add(transactionTransferRequestDTO.getAmount()));

            //TODO: update the account balances using service

            UpdateAccountRequestDTO updateFromAccountRequestDTO = new UpdateAccountRequestDTO(
                    fromAccount.getAccountNumber(),
                    fromAccount.getAccountName(),
                    fromAccount.getBalance()
            );
            accountService.updateAccount(fromAccount.getAccountUUID().toString(), updateFromAccountRequestDTO, bearerToken);
            UpdateAccountRequestDTO updateToAccountRequestDTO = new UpdateAccountRequestDTO(
                    toAccount.getAccountNumber(),
                    toAccount.getAccountName(),
                    toAccount.getBalance()
            );
            accountService.updateAccount(toAccount.getAccountUUID().toString(), updateToAccountRequestDTO, bearerToken);

            transactionRepository.save(transaction);

        } catch (RuntimeException e) {
            handleException(transaction, e);
            return new TransactionTransferResponseDTO(
                    transaction.getId(),
                    transactionTransferRequestDTO.getFromAccountNumber(),
                    transactionTransferRequestDTO.getToAccountNumber(),
                    transactionTransferRequestDTO.getAmount(),
                    transactionTransferRequestDTO.getDescription(),
                    LocalDateTime.now().toString(),
                    TransactionStatus.UNEXPECTED_ERROR,
                    new ResponseStatus(
                            500,
                            null,
                            "Unexpected error occurred",
                            null,
                            null
                    ));
        }

        return new TransactionTransferResponseDTO(
                transaction.getId(),
                transactionTransferRequestDTO.getFromAccountNumber(),
                transactionTransferRequestDTO.getToAccountNumber(),
                transactionTransferRequestDTO.getAmount(),
                transactionTransferRequestDTO.getDescription(),
                transaction.getTransactionDate().toString(),
                TransactionStatus.SUCCESS,
                new ResponseStatus(
                        200,
                        null,
                        "Transaction successful",
                        null,
                        null
                ));
    }

    private void handleException(Transaction transaction, RuntimeException e) {
        transaction.setStatus(TransactionStatus.INVALID_TRANSACTION);
        transactionRepository.save(transaction);
        throw e;
    }

    @Override
    public List<TransactionTransferResponseDTO> getTransactions(String bearerToken, String fromAccountNumber, String toAccountNumber) {
        // get transactions of the account
        return null;
    }
}
