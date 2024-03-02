package com.cilek.cilekbank.service.abstracts;

import com.cilek.cilekbank.dto.*;

public interface IAuthService {
    RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
