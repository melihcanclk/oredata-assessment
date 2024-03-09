package com.cilek.cilekbank.dto;

import com.cilek.cilekbank.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private UUID userId;

    private String username;

    private String email;

    private List<CreateAccountResponseDTO> accounts;
}
