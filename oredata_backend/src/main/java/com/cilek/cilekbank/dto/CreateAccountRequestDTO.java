package com.cilek.cilekbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountRequestDTO {

    private String accountNumber;
    private String accountName;
}
