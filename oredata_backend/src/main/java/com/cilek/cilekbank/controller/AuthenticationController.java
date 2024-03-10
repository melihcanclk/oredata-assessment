package com.cilek.cilekbank.controller;

import com.cilek.cilekbank.dto.*;
import com.cilek.cilekbank.service.abstracts.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class AuthenticationController {

    private final IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        RegisterResponseDTO registerResponseDTO = authService.register(registerRequestDTO);
        if (registerResponseDTO.getResponseStatus().getError() != null) {
            return ResponseEntity.badRequest().body(registerResponseDTO);
        } else {
            return ResponseEntity.ok(registerResponseDTO);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);
        if (loginResponseDTO.getResponseStatus().getError() != null) {
            return ResponseEntity.badRequest().body(loginResponseDTO);
        } else {
            return ResponseEntity.ok(loginResponseDTO);
        }
    }

}
