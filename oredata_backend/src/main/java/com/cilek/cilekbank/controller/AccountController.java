package com.cilek.cilekbank.controller;

import com.cilek.cilekbank.dto.CreateAccountRequestDTO;
import com.cilek.cilekbank.dto.CreateAccountResponseDTO;
import com.cilek.cilekbank.service.abstracts.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

    private final IAccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<CreateAccountResponseDTO> createAccount(@RequestBody CreateAccountRequestDTO createAccountRequestDTO) {
        return ResponseEntity.ok(accountService.createAccount(createAccountRequestDTO));
    }
}
