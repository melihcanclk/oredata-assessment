package com.cilek.cilekbank.controller;

import com.cilek.cilekbank.dto.*;
import com.cilek.cilekbank.dto.ResponseStatus;
import com.cilek.cilekbank.service.abstracts.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateAccountResponseDTO> createAccount(
            @RequestHeader(value = "Authorization") String bearerToken,
            @RequestBody CreateAccountRequestDTO createAccountRequestDTO
    ) {
        return ResponseEntity.ok(accountService.createAccount(createAccountRequestDTO, bearerToken));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAccountResponseDTO> getAccount(
            @RequestHeader(value = "Authorization") String bearerToken,
            @PathVariable(value = "id") String id
    ) {
        return ResponseEntity.ok(accountService.getAccount(id, bearerToken));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateAccountResponseDTO> updateAccount(
            @RequestHeader(value = "Authorization") String bearerToken,
            @PathVariable(value = "id") String id,
            @RequestBody UpdateAccountRequestDTO updateAccountRequestDTO
    ) {
        return ResponseEntity.ok(accountService.updateAccount(id, updateAccountRequestDTO, bearerToken));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStatus> deleteAccount(
            @RequestHeader(value = "Authorization") String bearerToken,
            @PathVariable(value = "id") String id) {
        return ResponseEntity.ok(accountService.deleteAccount(id, bearerToken));
    }

    // get all accounts of a user
    @PostMapping
    public ResponseEntity<List<GetAccountResponseDTO>> getAccounts(
            @RequestHeader(value = "Authorization") String bearerToken
    ){
        return ResponseEntity.ok(accountService.getAccounts(bearerToken));
    }
}
