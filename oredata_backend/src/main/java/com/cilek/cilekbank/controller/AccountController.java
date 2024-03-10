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

    // TODO: reconstruct method such that it returns not only ok but also error response
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateAccountResponseDTO> createAccount(
            @RequestHeader(value = "Authorization") String bearerToken,
            @RequestBody CreateAccountRequestDTO createAccountRequestDTO
    ) {
        CreateAccountResponseDTO createAccountResponseDTO = accountService.createAccount(createAccountRequestDTO, bearerToken);
        if (createAccountResponseDTO.getStatus().getError() != null) {
            return ResponseEntity.badRequest().body(createAccountResponseDTO);
        } else {
            return ResponseEntity.ok(createAccountResponseDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAccountResponseDTO> getAccountByUUID(
            @RequestHeader(value = "Authorization") String bearerToken,
            @PathVariable(value = "id") String accountUUIDString
    ) {
        GetAccountResponseDTO getAccountResponseDTO = accountService.getAccountByUUIDString(accountUUIDString, bearerToken);
        if (getAccountResponseDTO.getStatus().getError() != null) {
            return ResponseEntity.badRequest().body(getAccountResponseDTO);
        } else {
            return ResponseEntity.ok(getAccountResponseDTO);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateAccountResponseDTO> updateAccount(
            @RequestHeader(value = "Authorization") String bearerToken,
            @PathVariable(value = "id") String id,
            @RequestBody UpdateAccountRequestDTO updateAccountRequestDTO
    ) {
        UpdateAccountResponseDTO updateAccountResponseDTO = accountService.updateAccount(id, updateAccountRequestDTO, bearerToken);
        if (updateAccountResponseDTO.getStatus().getError() != null) {
            return ResponseEntity.badRequest().body(updateAccountResponseDTO);
        } else {
            return ResponseEntity.ok(updateAccountResponseDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStatus> deleteAccount(
            @RequestHeader(value = "Authorization") String bearerToken,
            @PathVariable(value = "id") String id) {
        ResponseStatus responseStatus = accountService.deleteAccount(id, bearerToken);
        if (responseStatus.getError() != null) {
            return ResponseEntity.badRequest().body(responseStatus);
        } else {
            return ResponseEntity.ok(responseStatus);
        }
    }

    // get all accounts of a user
    @PostMapping
    public ResponseEntity<List<GetAccountResponseDTO>> getAccounts(
            @RequestHeader(value = "Authorization") String bearerToken,
            @RequestParam(value = "accountName", required = false) String accountName,
            @RequestParam(value = "accountNumber", required = false) String accountNumber

    ) {
        List<GetAccountResponseDTO> getAccountResponseDTOList = accountService.getAccounts(bearerToken, accountName, accountNumber);
        if (getAccountResponseDTOList.get(0).getStatus().getError() != null) {
            return ResponseEntity.badRequest().body(getAccountResponseDTOList);
        } else {
            return ResponseEntity.ok(getAccountResponseDTOList);
        }
    }
}
