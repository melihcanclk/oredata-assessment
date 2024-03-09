package com.cilek.cilekbank.controller;

import com.cilek.cilekbank.dto.TransactionTransferRequestDTO;
import com.cilek.cilekbank.dto.TransactionTransferResponseDTO;
import com.cilek.cilekbank.service.abstracts.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionsController {

    private final ITransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionTransferResponseDTO> transfer(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody TransactionTransferRequestDTO transactionTransferRequestDTO
    ) {
        TransactionTransferResponseDTO transactionTransferResponseDTO = transactionService.transfer(bearerToken, transactionTransferRequestDTO);
        if (transactionTransferResponseDTO == null) {
            return ResponseEntity.badRequest().build();
        } else if (transactionTransferResponseDTO.getResponseStatus().getError() != null) {
            return ResponseEntity.status(transactionTransferResponseDTO.getResponseStatus().getStatusCode()).body(transactionTransferResponseDTO);
        } else {
            return ResponseEntity.ok(transactionTransferResponseDTO);
        }
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionTransferResponseDTO>> getTransactionsOfAccount(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable(value = "accountId", required = false) String accountUUID
    ) {
        List<TransactionTransferResponseDTO> transactions = transactionService.getTransactionsOfAccount(bearerToken, accountUUID);
        return transactions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(transactions);
    }
}
