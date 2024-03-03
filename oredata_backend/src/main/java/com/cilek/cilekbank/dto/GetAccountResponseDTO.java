package com.cilek.cilekbank.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAccountResponseDTO {
    private String accountUUID;
    private String accountNumber;
    private String accountName;
    private String userUUID;
    private BigDecimal balance;
    private String createdAt;
    private String updatedAt;
    private ResponseStatus responseStatus;

}
