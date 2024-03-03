package com.cilek.cilekbank.controller;

import com.cilek.cilekbank.dto.*;
import com.cilek.cilekbank.dto.ResponseStatus;
import com.cilek.cilekbank.service.abstracts.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

    private final IAccountService accountService;
    @PostMapping
    public ResponseEntity<CreateAccountResponseDTO> createAccount(@RequestBody CreateAccountRequestDTO createAccountRequestDTO) {
        return ResponseEntity.ok(accountService.createAccount(createAccountRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateAccountResponseDTO> updateAccount(@PathVariable(value = "id") String id, @RequestBody UpdateAccountRequestDTO updateAccountRequestDTO) {
        return ResponseEntity.ok(accountService.updateAccount(id, updateAccountRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStatus> deleteAccount(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }
}
