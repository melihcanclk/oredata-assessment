package com.cilek.cilekbank.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String username;
    private String email;
    private ResponseStatus responseStatus;
}
