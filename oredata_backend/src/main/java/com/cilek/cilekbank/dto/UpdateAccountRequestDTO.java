package com.cilek.cilekbank.dto;

import lombok.Data;

@Data
public class UpdateAccountRequestDTO {
    private String accountNumber;
    private String accountName;
    private String userId;
}
