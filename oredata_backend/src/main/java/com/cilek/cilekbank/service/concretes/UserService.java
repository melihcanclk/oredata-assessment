package com.cilek.cilekbank.service.concretes;

import com.cilek.cilekbank.dto.CreateAccountResponseDTO;
import com.cilek.cilekbank.dto.ResponseStatus;
import com.cilek.cilekbank.dto.UserResponseDTO;
import com.cilek.cilekbank.model._User;
import com.cilek.cilekbank.repository.UserRepository;
import com.cilek.cilekbank.service.abstracts.IUserDetailsService;
import com.cilek.cilekbank.utils.JWTUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserDetailsService {

    private final UserRepository userRepository;

    private final JWTUtils jwtUtils;

    // TODO: add special exception handling
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponseDTO getUser(String bearerToken) {
        UUID userId = jwtUtils.getUserIdFromBearerToken(bearerToken);
        try {
            _User user = userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
            List<CreateAccountResponseDTO> accounts = user.getAccounts().stream().map(account -> CreateAccountResponseDTO.builder()
                    .accountId(account.getAccount_id())
                    .userId(userId)
                    .accountName(account.getName())
                    .accountNumber(account.getNumber())
                    .balance(account.getBalance())
                    .username(user.getUsername())
                    .createdAt(account.getCreatedAt())
                    .updatedAt(account.getUpdatedAt())
                    .status(new ResponseStatus(200, "Account found", null, null, null))
                    .build()).toList();

            return UserResponseDTO.builder()
                    .userId(user.getUserId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .accounts(accounts)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }
    }
}
