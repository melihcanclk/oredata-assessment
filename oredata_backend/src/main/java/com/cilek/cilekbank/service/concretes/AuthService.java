package com.cilek.cilekbank.service.concretes;

import com.cilek.cilekbank.dto.*;
import com.cilek.cilekbank.model.Token;
import com.cilek.cilekbank.model._User;
import com.cilek.cilekbank.repository.TokenRepository;
import com.cilek.cilekbank.repository.UserRepository;
import com.cilek.cilekbank.utils.JWTUtils;
import com.cilek.cilekbank.service.abstracts.IAuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {

    private UserRepository userRepository;

    private JWTUtils jwtUtils;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private TokenRepository tokenRepository;

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO registerRequestDTO) {
        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();
        try {
            if (userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()) {
                registerResponseDTO.setResponseStatus(new ResponseStatus(
                        400,
                        "Username already exists",
                        null,
                        null,
                        null
                ));
                return registerResponseDTO;
            }

            if (userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent()) {
                registerResponseDTO.setResponseStatus(new ResponseStatus(
                        400,
                        "Email already exists",
                        null,
                        null,
                        null
                ));
                return registerResponseDTO;
            }

            _User user = new _User();
            user.setUsername(registerRequestDTO.getUsername());
            user.setEmail(registerRequestDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);

            registerResponseDTO.setUsername(user.getUsername());
            registerResponseDTO.setEmail(user.getEmail());
            registerResponseDTO.setUserId(user.getUserId());
            registerResponseDTO.setResponseStatus(new ResponseStatus(
                    200,
                    "User registered successfully",
                    null,
                    null,
                    null
            ));

        } catch (Exception e) {
            registerResponseDTO.setResponseStatus(new ResponseStatus(
                    400,
                    "Invalid username or password",
                    e.getMessage(),
                    null,
                    null
            ));
        }

        return registerResponseDTO;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

            _User user = userRepository.findByUsername(loginRequestDTO.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
            System.out.println("User found: " + user.getUsername());

            Token accessToken = setToken(user);

            loginResponseDTO.setUsername(user.getUsername());
            loginResponseDTO.setEmail(user.getEmail());
            loginResponseDTO.setUserId(user.getUserId());
            loginResponseDTO.setResponseStatus(new ResponseStatus(
                    200,
                    "Login successful",
                    null,
                    accessToken.getAccessToken(),
                    accessToken.getExpirationTime().toString()
            ));


        } catch (Exception e) {
            loginResponseDTO.setResponseStatus(new ResponseStatus(
                    400,
                    "Invalid username or password",
                    e.getMessage(),
                    null,
                    null
            ));
        }
        return loginResponseDTO;
    }

    private Token setToken(_User user) {
        HashMap<String, Object> customClaims = new HashMap<>();
        customClaims.put("userId", user.getUserId());
        String generatedToken = jwtUtils.generateAccessToken(customClaims, user);
        Token accessToken = new Token();

        // Set the generated token to the Token object
        accessToken.setAccessToken(generatedToken);

        System.out.println("Token generated: " + generatedToken);

        accessToken.setExpirationTime(LocalDateTime.now().plusHours(24));
        accessToken.setLoggedOut(false);
        accessToken.setUser(user);

        // if user has tokens, make them logged out
        if (user.getTokens() != null) {
            user.getTokens().forEach(t -> {
                t.setLoggedOut(true);
                tokenRepository.save(t);
            });
        }

        tokenRepository.save(accessToken);
        return accessToken;
    }

}
