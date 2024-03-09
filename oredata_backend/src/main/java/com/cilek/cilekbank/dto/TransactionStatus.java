package com.cilek.cilekbank.dto;

public interface TransactionStatus {
    String SUCCESS = "SUCCESS";
    String INSUFFICIENT_BALANCE = "INSUFFICIENT_BALANCE";
    String INVALID_ACCOUNT = "INVALID_ACCOUNT";
    String INVALID_AMOUNT = "INVALID_AMOUNT";
    String INVALID_TRANSACTION = "INVALID_TRANSACTION";

    String UNAUTHORIZED = "UNAUTHORIZED";
    String UNEXPECTED_ERROR = "UNEXPECTED_ERROR";

}
