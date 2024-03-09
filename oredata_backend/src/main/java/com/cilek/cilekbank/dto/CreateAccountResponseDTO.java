package com.cilek.cilekbank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class CreateAccountResponseDTO {

    private UUID accountId;
    private UUID userId;
    private String accountNumber;
    private String accountName;
    private BigDecimal balance;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ResponseStatus status;
}
