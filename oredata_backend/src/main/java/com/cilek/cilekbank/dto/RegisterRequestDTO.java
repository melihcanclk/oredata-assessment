package com.cilek.cilekbank.dto;

import com.cilek.cilekbank.model._User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;

    public _User toUser(PasswordEncoder passwordEncoder) {
        _User user = new _User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }
}
