package com.cilek.cilekbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateAccountRequestDTO {

    private String accountNumber;
    private String accountName;
    private UUID userId;
}
