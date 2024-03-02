package com.cilek.cilekbank.service.concretes;

import com.cilek.cilekbank.repository.UserRepository;
import com.cilek.cilekbank.service.abstracts.IUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserDetailsService {

    private final UserRepository userRepository;

    // TODO: add special exception handling
    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
