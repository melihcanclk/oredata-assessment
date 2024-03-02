package com.cilek.cilekbank.service.abstracts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService extends UserDetailsService {

    UserDetails loadUserByUsername(String username);
}
