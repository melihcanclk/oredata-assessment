package com.cilek.cilekbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateAccountResponseDTO {

    private UUID id;
    private String accountNumber;
    private String accountName;
    private BigDecimal balance;
    private UUID userId;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ResponseStatus status;
}
