package com.cilek.cilekbank.service.abstracts;

import com.cilek.cilekbank.dto.TransactionTransferRequestDTO;
import com.cilek.cilekbank.dto.TransactionTransferResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

public interface ITransactionService {

    TransactionTransferResponseDTO transfer(String bearerToken, TransactionTransferRequestDTO transactionTransferRequestDTO);

    List<TransactionTransferResponseDTO> getTransactionsOfAccount(String bearerToken, String accountUUID);

}
