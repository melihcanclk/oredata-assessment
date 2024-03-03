package com.cilek.cilekbank.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LoginResponseDTO {
    private UUID userId;
    private String username;
    private String email;
    private ResponseStatus responseStatus;
}
