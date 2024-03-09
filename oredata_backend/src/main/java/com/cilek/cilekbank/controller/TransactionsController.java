package com.cilek.cilekbank.controller;

import com.cilek.cilekbank.dto.TransactionTransferRequestDTO;
import com.cilek.cilekbank.dto.TransactionTransferResponseDTO;
import com.cilek.cilekbank.service.abstracts.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(transactionService.transfer(bearerToken, transactionTransferRequestDTO));
    }
}
