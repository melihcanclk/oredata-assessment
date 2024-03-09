package com.cilek.cilekbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UpdateAccountRequestDTO {
    private String accountNumber;
    private String accountName;
    private BigDecimal balance;

    public UpdateAccountRequestDTO(String accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }

}
