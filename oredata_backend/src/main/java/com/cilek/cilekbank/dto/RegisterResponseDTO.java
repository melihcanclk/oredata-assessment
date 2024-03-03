package com.cilek.cilekbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {
    private UUID userId;
    private String username;
    private String email;
    private ResponseStatus responseStatus;
}
