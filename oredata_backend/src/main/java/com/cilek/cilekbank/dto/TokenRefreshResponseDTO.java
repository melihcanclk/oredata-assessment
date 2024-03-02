package com.cilek.cilekbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRefreshResponseDTO {
    private String accessToken;
    private ResponseStatus responseStatus;
}
